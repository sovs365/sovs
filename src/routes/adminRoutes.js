import express from 'express';
import { v4 as uuidv4 } from 'uuid';
import { authenticateToken, requireRole } from '../auth.js';
import { query, getClient } from '../db.js';

const router = express.Router();

// Get all users (admin only)
router.get('/admin/users', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const result = await query('SELECT * FROM users ORDER BY created_at DESC');
    const users = result.rows.map(u => formatUserResponse(u));
    res.json(users);
  } catch (error) {
    console.error('Error fetching users:', error);
    res.status(500).json({ error: 'Failed to fetch users' });
  }
});

// Get users by role
router.get('/admin/users/role/:role', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const result = await query(
      'SELECT * FROM users WHERE role = $1 ORDER BY created_at DESC',
      [req.params.role]
    );
    const users = result.rows.map(u => formatUserResponse(u));
    res.json(users);
  } catch (error) {
    console.error('Error fetching users:', error);
    res.status(500).json({ error: 'Failed to fetch users' });
  }
});

// Update user details (admin only)
router.put('/admin/users/:id', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const {
      fullName,
      email,
      phoneNumber,
      regNo,
      faculty,
      course,
      subjectCombination,
      levelOfStudy,
      yearOfStudy,
      gender,
      disability,
      manifesto,
      role
    } = req.body;

    const result = await query(
      `UPDATE users
       SET full_name = COALESCE($1, full_name),
           email = COALESCE($2, email),
           phone_number = COALESCE($3, phone_number),
           reg_no = COALESCE($4, reg_no),
           faculty = COALESCE($5, faculty),
           course = COALESCE($6, course),
           subject_combination = COALESCE($7, subject_combination),
           level_of_study = COALESCE($8, level_of_study),
           year_of_study = COALESCE($9, year_of_study),
           gender = COALESCE($10, gender),
           disability = COALESCE($11, disability),
           manifesto = COALESCE($12, manifesto),
           role = COALESCE($13, role),
           updated_at = $14
       WHERE user_id = $15
       RETURNING *`,
      [
        fullName || null,
        email ? email.toLowerCase() : null,
        phoneNumber || null,
        regNo || null,
        faculty || null,
        course || null,
        subjectCombination || null,
        levelOfStudy || null,
        yearOfStudy || null,
        gender || null,
        disability || null,
        manifesto || null,
        role || null,
        Date.now(),
        req.params.id
      ]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'User not found' });
    }

    res.json({ message: 'User updated successfully' });
  } catch (error) {
    console.error('Error updating user:', error);
    res.status(500).json({ error: 'Failed to update user' });
  }
});

async function verifyUserHandler(req, res) {
  try {
    const { verified } = req.body;

    await query(
      'UPDATE users SET is_verified = $1, updated_at = $2 WHERE user_id = $3',
      [verified, Date.now(), req.params.id]
    );

    const result = await query(
      'SELECT * FROM users WHERE user_id = $1',
      [req.params.id]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'User not found' });
    }

    res.json({ message: 'User verification updated successfully' });
  } catch (error) {
    console.error('Error updating user:', error);
    res.status(500).json({ error: 'Failed to update user' });
  }
}

// Verify/unverify user
router.patch('/admin/users/:id/verify', authenticateToken, requireRole('admin'), verifyUserHandler);
router.put('/admin/users/:id/verify', authenticateToken, requireRole('admin'), verifyUserHandler);

// Lock/unlock user account
router.patch('/admin/users/:id/lock', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { locked } = req.body;

    await query(
      'UPDATE users SET is_locked = $1, updated_at = $2 WHERE user_id = $3',
      [locked, Date.now(), req.params.id]
    );

    const result = await query(
      'SELECT * FROM users WHERE user_id = $1',
      [req.params.id]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'User not found' });
    }

    res.json(formatUserResponse(result.rows[0]));
  } catch (error) {
    console.error('Error updating user:', error);
    res.status(500).json({ error: 'Failed to update user' });
  }
});

// Unlock user account (admin only)
router.put('/admin/users/:id/unlock', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const result = await query(
      'UPDATE users SET is_locked = false, updated_at = $1 WHERE user_id = $2 RETURNING *',
      [Date.now(), req.params.id]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'User not found' });
    }

    res.json({ message: 'User account unlocked' });
  } catch (error) {
    console.error('Error unlocking user:', error);
    res.status(500).json({ error: 'Failed to unlock user' });
  }
});

// Delete user (admin only)
router.delete('/admin/users/:id', authenticateToken, requireRole('admin'), async (req, res) => {
  let client;
  try {
    const targetUserId = req.params.id;
    client = await getClient();
    await client.query('BEGIN');

    const userResult = await client.query(
      'SELECT user_id, email FROM users WHERE user_id = $1 FOR UPDATE',
      [targetUserId]
    );

    if (userResult.rows.length === 0) {
      await client.query('ROLLBACK');
      return res.status(404).json({ error: 'User not found' });
    }

    const normalizedEmail = (userResult.rows[0].email || '').trim().toLowerCase();

    // Remove votes cast by this user and votes that reference the user's candidate entries.
    await client.query(
      `DELETE FROM votes
       WHERE voter_id = $1
          OR candidate_id IN (
            SELECT candidate_id
            FROM candidates
            WHERE user_id = $1
          )`,
      [targetUserId]
    );

    await client.query(
      'DELETE FROM candidates WHERE user_id = $1',
      [targetUserId]
    );

    await client.query(
      'DELETE FROM admin_logs WHERE admin_id = $1',
      [targetUserId]
    );

    if (normalizedEmail) {
      await client.query(
        'DELETE FROM verification_codes WHERE LOWER(email) = $1',
        [normalizedEmail]
      );
      await client.query(
        'DELETE FROM verified_registrations WHERE LOWER(email) = $1',
        [normalizedEmail]
      );
    }

    const deleteResult = await client.query(
      'DELETE FROM users WHERE user_id = $1 RETURNING user_id',
      [targetUserId]
    );

    if (deleteResult.rows.length === 0) {
      await client.query('ROLLBACK');
      return res.status(404).json({ error: 'User not found' });
    }

    await client.query('COMMIT');
    res.json({ message: 'User deleted successfully' });
  } catch (error) {
    if (client) {
      try {
        await client.query('ROLLBACK');
      } catch (rollbackError) {
        console.error('Error rolling back user delete transaction:', rollbackError);
      }
    }

    if (error?.code === '23503') {
      return res.status(409).json({ error: 'User cannot be deleted because dependent records still exist' });
    }

    console.error('Error deleting user:', error);
    res.status(500).json({ error: 'Failed to delete user' });
  } finally {
    if (client) {
      client.release();
    }
  }
});

// Create faculty (admin only)
router.post('/admin/faculties', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { name } = req.body;

    if (!name) {
      return res.status(400).json({ error: 'Faculty name is required' });
    }

    const facultyId = uuidv4();

    await query(
      'INSERT INTO faculties (faculty_id, name, created_at) VALUES ($1, $2, $3)',
      [facultyId, name, Date.now()]
    );

    res.status(201).json({
      facultyId,
      name
    });
  } catch (error) {
    if (error.code === '23505') { // Unique violation
      return res.status(400).json({ error: 'Faculty already exists' });
    }
    console.error('Error creating faculty:', error);
    res.status(500).json({ error: 'Failed to create faculty' });
  }
});

// Create course (admin only)
router.post('/admin/courses', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { facultyId, name } = req.body;

    if (!facultyId || !name) {
      return res.status(400).json({ error: 'Faculty ID and course name are required' });
    }

    const courseId = uuidv4();

    await query(
      'INSERT INTO courses (course_id, faculty_id, name, created_at) VALUES ($1, $2, $3, $4)',
      [courseId, facultyId, name, Date.now()]
    );

    res.status(201).json({
      courseId,
      facultyId,
      name
    });
  } catch (error) {
    if (error.code === '23505') {
      return res.status(400).json({ error: 'Course already exists' });
    }
    console.error('Error creating course:', error);
    res.status(500).json({ error: 'Failed to create course' });
  }
});

// Create subject combination (admin only)
router.post('/admin/subject-combinations', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { courseId, name } = req.body;

    if (!courseId || !name) {
      return res.status(400).json({ error: 'Course ID and combination name are required' });
    }

    const combinationId = uuidv4();

    await query(
      'INSERT INTO subject_combinations (id, course_id, name, created_at) VALUES ($1, $2, $3, $4)',
      [combinationId, courseId, name, Date.now()]
    );

    res.status(201).json({
      id: combinationId,
      courseId,
      name
    });
  } catch (error) {
    if (error.code === '23505') {
      return res.status(400).json({ error: 'Subject combination already exists' });
    }
    console.error('Error creating subject combination:', error);
    res.status(500).json({ error: 'Failed to create subject combination' });
  }
});

async function getReportsHandler(req, res) {
  try {
    // Count users by role
    const userCountResult = await query(`
      SELECT role, COUNT(*) as count FROM users GROUP BY role
    `);

    const userCounts = {};
    userCountResult.rows.forEach(row => {
      userCounts[row.role] = Number(row.count || 0);
    });

    // Count elections
    const electionCountResult = await query('SELECT COUNT(*) FROM elections');
    const totalElections = Number(electionCountResult.rows[0].count || 0);

    // Count active elections
    const activeElectionCountResult = await query(
      "SELECT COUNT(*) FROM elections WHERE status = 'open'"
    );
    const activeElections = Number(activeElectionCountResult.rows[0].count || 0);

    // Count total votes
    const voteCountResult = await query('SELECT COUNT(*) FROM votes');
    const totalVotesCast = Number(voteCountResult.rows[0].count || 0);

    // Gender breakdown (all users)
    const genderResult = await query(`
      SELECT
        COALESCE(SUM(CASE WHEN LOWER(gender) = 'male' THEN 1 ELSE 0 END), 0) AS male,
        COALESCE(SUM(CASE WHEN LOWER(gender) = 'female' THEN 1 ELSE 0 END), 0) AS female
      FROM users
    `);

    // Candidate gender breakdown
    const candidateGenderResult = await query(`
      SELECT
        COALESCE(SUM(CASE WHEN LOWER(gender) = 'male' THEN 1 ELSE 0 END), 0) AS male,
        COALESCE(SUM(CASE WHEN LOWER(gender) = 'female' THEN 1 ELSE 0 END), 0) AS female
      FROM users
      WHERE role = 'candidate'
    `);

    // Users with disability declared
    const disabilityResult = await query(`
      SELECT COUNT(*) AS count
      FROM users
      WHERE disability IS NOT NULL AND TRIM(disability) <> ''
    `);

    // Voter turnout per election
    const turnoutResult = await query(`
      SELECT
        e.election_id,
        e.title AS election_title,
        COUNT(DISTINCT v.voter_id) AS total_voted
      FROM elections e
      LEFT JOIN votes v ON e.election_id = v.election_id
      GROUP BY e.election_id, e.title
      ORDER BY e.created_at DESC
    `);

    const totalEligibleVoters = Number(userCounts.voter || 0);
    const voterTurnoutPerElection = turnoutResult.rows.map(row => {
      const totalVoted = Number(row.total_voted || 0);
      const turnoutPercentage = totalEligibleVoters > 0
        ? Number(((totalVoted / totalEligibleVoters) * 100).toFixed(2))
        : 0;

      return {
        electionId: row.election_id,
        electionTitle: row.election_title,
        totalEligible: totalEligibleVoters,
        totalVoted: totalVoted,
        turnoutPercentage: turnoutPercentage
      };
    });

    res.json({
      totalVoters: Number(userCounts.voter || 0),
      totalCandidates: Number(userCounts.candidate || 0),
      totalAdmins: Number(userCounts.admin || 0),
      totalElections: Number(totalElections || 0),
      activeElections,
      totalVotesCast,
      genderBreakdown: {
        male: Number(genderResult.rows[0]?.male || 0),
        female: Number(genderResult.rows[0]?.female || 0)
      },
      candidateGenderBreakdown: {
        male: Number(candidateGenderResult.rows[0]?.male || 0),
        female: Number(candidateGenderResult.rows[0]?.female || 0)
      },
      disabilityCount: Number(disabilityResult.rows[0]?.count || 0),
      voterTurnoutPerElection
    });
  } catch (error) {
    console.error('Error fetching reports:', error);
    res.status(500).json({ error: 'Failed to fetch reports' });
  }
}

// Get reports/statistics (admin only)
router.get('/admin/reports', authenticateToken, requireRole('admin'), getReportsHandler);
router.get('/reports', authenticateToken, requireRole('admin'), getReportsHandler);

// Get election details with vote breakdown (admin only)
router.get('/admin/elections/:id/details', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const electionResult = await query(
      'SELECT * FROM elections WHERE election_id = $1',
      [req.params.id]
    );

    if (electionResult.rows.length === 0) {
      return res.status(404).json({ error: 'Election not found' });
    }

    const election = electionResult.rows[0];

    // Get vote breakdown
    const voteBreakdownResult = await query(`
      SELECT c.candidate_id, u.full_name, COUNT(v.vote_id)::int AS votes
      FROM candidates c
      LEFT JOIN users u ON c.user_id = u.user_id
      LEFT JOIN votes v ON c.candidate_id = v.candidate_id AND v.election_id = $1
      WHERE c.position_id = $2
      GROUP BY c.candidate_id, u.full_name
      ORDER BY votes DESC
    `, [req.params.id, election.position_id]);

    // Get voter participation
    const participationResult = await query(`
      SELECT COUNT(DISTINCT voter_id)::int AS participated_voters
      FROM votes
      WHERE election_id = $1
    `, [req.params.id]);

    res.json({
      electionId: election.election_id,
      title: election.title,
      description: election.description,
      status: election.status,
      startDate: election.start_date,
      endDate: election.end_date,
      voteBreakdown: voteBreakdownResult.rows.map(row => ({
        candidateId: row.candidate_id,
        candidateName: row.full_name,
        votes: Number(row.votes || 0)
      })),
      participatedVoters: Number(participationResult.rows[0].participated_voters || 0)
    });
  } catch (error) {
    console.error('Error fetching election details:', error);
    res.status(500).json({ error: 'Failed to fetch election details' });
  }
});

// Log admin action
router.post('/admin/logs', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { action, details } = req.body;

    if (!action) {
      return res.status(400).json({ error: 'Action is required' });
    }

    const logId = uuidv4();

    await query(
      `INSERT INTO admin_logs (log_id, admin_id, action, details, created_at)
       VALUES ($1, $2, $3, $4, $5)`,
      [logId, req.userId, action, details || null, Date.now()]
    );

    res.status(201).json({
      logId,
      action,
      details,
      createdAt: Date.now()
    });
  } catch (error) {
    console.error('Error creating log:', error);
    res.status(500).json({ error: 'Failed to create log' });
  }
});

function formatUserResponse(user) {
  return {
    userId: user.user_id,
    username: user.username,
    fullName: user.full_name,
    email: user.email,
    phoneNumber: user.phone_number,
    role: user.role,
    regNo: user.reg_no,
    faculty: user.faculty,
    course: user.course,
    isVerified: user.is_verified,
    isLocked: user.is_locked,
    createdAt: user.created_at
  };
}

export default router;
