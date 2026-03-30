import express from 'express';
import crypto from 'crypto';
import { v4 as uuidv4 } from 'uuid';
import { authenticateToken, requireRole, getUserById } from '../auth.js';
import { query, getClient } from '../db.js';

const router = express.Router();

function normalizeElectionStatus(status) {
  return String(status || '').trim().toLowerCase();
}

// Get all candidates
router.get('/candidates', async (req, res) => {
  try {
    const result = await query(`
      SELECT c.*, u.full_name, u.reg_no, u.faculty, u.course, u.profile_photo_path, p.name as position_name
      FROM candidates c
      LEFT JOIN users u ON c.user_id = u.user_id
      LEFT JOIN positions p ON c.position_id = p.position_id
      ORDER BY c.created_at DESC
    `);

    const candidates = result.rows.map(c => formatCandidateResponse(c));
    res.json(candidates);
  } catch (error) {
    console.error('Error fetching candidates:', error);
    res.status(500).json({ error: 'Failed to fetch candidates' });
  }
});

// Get candidates for a position
router.get('/candidates/position/:positionId', async (req, res) => {
  try {
    const result = await query(`
      SELECT c.*, u.full_name, u.reg_no, u.faculty, u.course, u.profile_photo_path, p.name as position_name
      FROM candidates c
      LEFT JOIN users u ON c.user_id = u.user_id
      LEFT JOIN positions p ON c.position_id = p.position_id
      WHERE c.position_id = $1
      ORDER BY c.created_at DESC
    `, [req.params.positionId]);

    const candidates = result.rows.map(c => formatCandidateResponse(c));
    res.json(candidates);
  } catch (error) {
    console.error('Error fetching candidates:', error);
    res.status(500).json({ error: 'Failed to fetch candidates' });
  }
});

// Get candidate by ID
router.get('/candidates/:id', async (req, res) => {
  try {
    const result = await query(`
      SELECT c.*, u.full_name, u.reg_no, u.faculty, u.course, u.profile_photo_path, p.name as position_name
      FROM candidates c
      LEFT JOIN users u ON c.user_id = u.user_id
      LEFT JOIN positions p ON c.position_id = p.position_id
      WHERE c.candidate_id = $1
    `, [req.params.id]);

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'Candidate not found' });
    }

    res.json(formatCandidateResponse(result.rows[0]));
  } catch (error) {
    console.error('Error fetching candidate:', error);
    res.status(500).json({ error: 'Failed to fetch candidate' });
  }
});

// Register as candidate (for authenticated users)
router.post('/candidates', authenticateToken, async (req, res) => {
  try {
    const { positionId } = req.body;

    if (!positionId) {
      return res.status(400).json({ error: 'Position ID is required' });
    }

    // Check if already a candidate for this position
    const existing = await query(
      'SELECT candidate_id FROM candidates WHERE user_id = $1 AND position_id = $2',
      [req.userId, positionId]
    );

    if (existing.rows.length > 0) {
      return res.status(400).json({ error: 'Already registered as candidate for this position' });
    }

    const candidateId = uuidv4();
    const now = Date.now();

    // Get user's manifesto from their profile
    const user = await getUserById(req.userId);

    await query(
      `INSERT INTO candidates (candidate_id, position_id, user_id, manifesto, created_at)
       VALUES ($1, $2, $3, $4, $5)`,
      [candidateId, positionId, req.userId, user.manifesto || '', now]
    );

    const result = await query(`
      SELECT c.*, u.full_name, u.reg_no, u.faculty, u.course, u.profile_photo_path, p.name as position_name
      FROM candidates c
      LEFT JOIN users u ON c.user_id = u.user_id
      LEFT JOIN positions p ON c.position_id = p.position_id
      WHERE c.candidate_id = $1
    `, [candidateId]);

    res.status(201).json(formatCandidateResponse(result.rows[0]));
  } catch (error) {
    console.error('Error creating candidate:', error);
    res.status(500).json({ error: 'Failed to register as candidate' });
  }
});

async function verifyCandidateHandler(req, res) {
  try {
    const { verified } = req.body;

    await query(
      'UPDATE candidates SET is_verified = $1 WHERE candidate_id = $2',
      [verified, req.params.id]
    );

    const result = await query(`
      SELECT c.*, u.full_name, u.reg_no, u.faculty, u.course, u.profile_photo_path, p.name as position_name
      FROM candidates c
      LEFT JOIN users u ON c.user_id = u.user_id
      LEFT JOIN positions p ON c.position_id = p.position_id
      WHERE c.candidate_id = $1
    `, [req.params.id]);

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'Candidate not found' });
    }

    res.json({ message: 'Candidate verification updated successfully' });
  } catch (error) {
    console.error('Error verifying candidate:', error);
    res.status(500).json({ error: 'Failed to verify candidate' });
  }
}

// Verify candidate (admin only)
router.patch('/candidates/:id/verify', authenticateToken, requireRole('admin'), verifyCandidateHandler);
router.put('/candidates/:id/verify', authenticateToken, requireRole('admin'), verifyCandidateHandler);

// Delete candidate (admin only)
router.delete('/candidates/:id', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const result = await query(
      'DELETE FROM candidates WHERE candidate_id = $1 RETURNING candidate_id',
      [req.params.id]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'Candidate not found' });
    }

    res.json({ message: 'Candidate deleted successfully' });
  } catch (error) {
    console.error('Error deleting candidate:', error);
    res.status(500).json({ error: 'Failed to delete candidate' });
  }
});

// Cast vote
router.post('/votes', authenticateToken, async (req, res) => {
  try {
    const { electionId, votes } = req.body;

    if (!electionId || !votes || votes.length === 0) {
      return res.status(400).json({ error: 'Election ID and votes are required' });
    }

    // Check election status
    const electionResult = await query(
      'SELECT * FROM elections WHERE election_id = $1',
      [electionId]
    );

    if (electionResult.rows.length === 0) {
      return res.status(404).json({ error: 'Election not found' });
    }

    const election = electionResult.rows[0];
    const now = Date.now();
    const electionStatus = normalizeElectionStatus(election.status);

    if (!['open', 'draft'].includes(electionStatus)) {
      return res.status(400).json({ error: 'Election is not open' });
    }

    if (now < election.start_date || now > election.end_date) {
      return res.status(400).json({ error: 'Voting is not active at this time' });
    }

    // Check if user has already voted in this election
    const existingVote = await query(
      'SELECT vote_id FROM votes WHERE election_id = $1 AND voter_id = $2 LIMIT 1',
      [electionId, req.userId]
    );

    if (existingVote.rows.length > 0) {
      return res.status(400).json({ error: 'You have already voted in this election' });
    }

    const positionsResult = await query(
      'SELECT DISTINCT position_id FROM elections WHERE election_id = $1',
      [electionId]
    );

    const requiredPositionIds = positionsResult.rows
      .map((row) => row.position_id)
      .filter(Boolean);

    if (requiredPositionIds.length === 0) {
      return res.status(400).json({ error: 'No positions are configured for this election' });
    }

    const requestedPositionIds = votes
      .map((vote) => vote?.positionId)
      .filter(Boolean);

    if (new Set(requestedPositionIds).size !== requestedPositionIds.length) {
      return res.status(400).json({ error: 'Duplicate position votes are not allowed' });
    }

    if (requestedPositionIds.length !== requiredPositionIds.length) {
      return res.status(400).json({ error: 'You must vote for all positions in this election' });
    }

    const unexpectedPosition = requestedPositionIds.find((positionId) => !requiredPositionIds.includes(positionId));
    if (unexpectedPosition) {
      return res.status(400).json({ error: 'One or more selected positions are invalid for this election' });
    }

    const missingPosition = requiredPositionIds.find((positionId) => !requestedPositionIds.includes(positionId));
    if (missingPosition) {
      return res.status(400).json({ error: 'You must vote for all positions in this election' });
    }

    const candidateMeta = new Map();
    for (const vote of votes) {
      const { positionId, candidateId } = vote || {};
      if (!positionId || !candidateId) {
        return res.status(400).json({ error: 'Each vote must include positionId and candidateId' });
      }

      const candidateExists = await query(
        `SELECT c.candidate_id, u.full_name, p.name AS position_name
         FROM candidates c
         LEFT JOIN users u ON c.user_id = u.user_id
         LEFT JOIN positions p ON c.position_id = p.position_id
         WHERE c.candidate_id = $1 AND c.position_id = $2 AND c.is_verified = TRUE
         LIMIT 1`,
        [candidateId, positionId]
      );

      if (candidateExists.rows.length === 0) {
        return res.status(400).json({ error: 'Invalid candidate selected for one or more positions' });
      }

      candidateMeta.set(candidateId, candidateExists.rows[0]);
    }

    const voteReceipts = [];
    const client = await getClient();
    try {
      await client.query('BEGIN');

      // Record each vote atomically
      for (const vote of votes) {
        const { positionId, candidateId } = vote;

        const voteId = uuidv4();
        const voteHash = crypto
          .createHash('sha256')
          .update(`${voteId}${candidateId}${electionId}${Date.now()}`)
          .digest('hex');

        await client.query(
          `INSERT INTO votes (vote_id, election_id, voter_id, position_id, candidate_id, vote_hash, created_at)
           VALUES ($1, $2, $3, $4, $5, $6, $7)`,
          [voteId, electionId, req.userId, positionId, candidateId, voteHash, now]
        );

        const meta = candidateMeta.get(candidateId);
        voteReceipts.push({
          voteId,
          electionTitle: election.title,
          positionName: meta?.position_name || null,
          candidateName: meta?.full_name || null,
          timestamp: now,
          hash: voteHash
        });
      }

      await client.query('COMMIT');
    } catch (transactionError) {
      await client.query('ROLLBACK');
      if (transactionError?.code === '23505') {
        return res.status(400).json({ error: 'You have already voted in this election' });
      }
      throw transactionError;
    } finally {
      client.release();
    }

    res.status(201).json({
      message: 'Vote cast successfully',
      receipts: voteReceipts
    });

  } catch (error) {
    console.error('Error casting vote:', error);
    res.status(500).json({ error: 'Failed to cast vote' });
  }
});

// Get vote receipts for current user
router.get('/votes/receipts', authenticateToken, async (req, res) => {
  try {
    const result = await query(`
      SELECT
        v.vote_id,
        e.title AS election_title,
        p.name AS position_name,
        u.full_name AS candidate_name,
        v.created_at,
        v.vote_hash
      FROM votes v
      LEFT JOIN elections e ON v.election_id = e.election_id
      LEFT JOIN positions p ON v.position_id = p.position_id
      LEFT JOIN candidates c ON v.candidate_id = c.candidate_id
      LEFT JOIN users u ON c.user_id = u.user_id
      WHERE v.voter_id = $1
      ORDER BY v.created_at DESC
    `, [req.userId]);

    const receipts = result.rows.map(row => ({
      voteId: row.vote_id,
      electionTitle: row.election_title,
      positionName: row.position_name,
      candidateName: row.candidate_name,
      timestamp: row.created_at,
      hash: row.vote_hash
    }));

    res.json(receipts);
  } catch (error) {
    console.error('Error fetching vote receipts:', error);
    res.status(500).json({ error: 'Failed to fetch vote receipts' });
  }
});

// Check whether current user has voted in an election
router.get('/votes/check/:electionId', authenticateToken, async (req, res) => {
  try {
    const result = await query(
      'SELECT COUNT(*)::int AS count FROM votes WHERE election_id = $1 AND voter_id = $2',
      [req.params.electionId, req.userId]
    );

    res.json({ hasVoted: (result.rows[0]?.count || 0) > 0 });
  } catch (error) {
    console.error('Error checking vote status:', error);
    res.status(500).json({ error: 'Failed to check vote status' });
  }
});

async function getElectionResultsHandler(req, res) {
  try {
    const electionResult = await query(
      'SELECT * FROM elections WHERE election_id = $1',
      [req.params.electionId]
    );

    if (electionResult.rows.length === 0) {
      return res.status(404).json({ error: 'Election not found' });
    }

    const election = electionResult.rows[0];

    // Get all positions and votes for this election
    const positionsResult = await query(
      'SELECT DISTINCT position_id FROM elections WHERE election_id = $1',
      [req.params.electionId]
    );

    const positions = [];

    for (const pos of positionsResult.rows) {
      const positionData = await query(
        'SELECT * FROM positions WHERE position_id = $1',
        [pos.position_id]
      );

      const position = positionData.rows[0];

      // Get vote counts for each candidate
      const votesResult = await query(`
        SELECT c.candidate_id, u.full_name, COUNT(*) as count
        FROM votes v
        LEFT JOIN candidates c ON v.candidate_id = c.candidate_id
        LEFT JOIN users u ON c.user_id = u.user_id
        WHERE v.election_id = $1 AND v.position_id = $2
        GROUP BY c.candidate_id, u.full_name
        ORDER BY count DESC
      `, [req.params.electionId, pos.position_id]);

      const totalVotes = votesResult.rows.reduce((sum, row) => sum + row.count, 0);

      const candidates = votesResult.rows.map(row => ({
        candidateId: row.candidate_id,
        candidateName: row.full_name,
        votes: row.count,
        percentage: totalVotes > 0 ? Number((row.count / totalVotes * 100).toFixed(2)) : 0
      }));

      positions.push({
        positionId: position.position_id,
        positionName: position.name,
        candidates,
        totalVotes
      });
    }

    res.json({
      electionId: req.params.electionId,
      electionTitle: election.title,
      positions
    });

  } catch (error) {
    console.error('Error fetching results:', error);
    res.status(500).json({ error: 'Failed to fetch results' });
  }
}

// Get vote results for election
router.get('/votes/results/:electionId', getElectionResultsHandler);
router.get('/elections/:electionId/results', getElectionResultsHandler);

function formatCandidateResponse(candidate) {
  return {
    candidateId: candidate.candidate_id,
    positionId: candidate.position_id,
    userId: candidate.user_id,
    manifesto: candidate.manifesto,
    fullName: candidate.full_name,
    regNo: candidate.reg_no,
    faculty: candidate.faculty,
    course: candidate.course,
    profilePhotoPath: candidate.profile_photo_path,
    isVerified: candidate.is_verified,
    positionName: candidate.position_name
  };
}

export default router;
