import express from 'express';
import { v4 as uuidv4 } from 'uuid';
import { authenticateToken, requireRole } from '../auth.js';
import { query } from '../db.js';

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

// Verify/unverify user
router.patch('/admin/users/:id/verify', authenticateToken, requireRole('admin'), async (req, res) => {
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

    res.json(formatUserResponse(result.rows[0]));
  } catch (error) {
    console.error('Error updating user:', error);
    res.status(500).json({ error: 'Failed to update user' });
  }
});

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

// Get reports/statistics (admin only)
router.get('/admin/reports', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    // Count users by role
    const userCountResult = await query(`
      SELECT role, COUNT(*) as count FROM users GROUP BY role
    `);

    const userCounts = {};
    userCountResult.rows.forEach(row => {
      userCounts[row.role] = row.count;
    });

    // Count elections
    const electionCountResult = await query('SELECT COUNT(*) FROM elections');
    const totalElections = electionCountResult.rows[0].count;

    // Count total votes
    const voteCountResult = await query('SELECT COUNT(*) FROM votes');
    const totalVotes = voteCountResult.rows[0].count;

    // Get elections with vote counts
    const electionsResult = await query(`
      SELECT e.election_id, e.title, COUNT(v.vote_id) as vote_count
      FROM elections e
      LEFT JOIN votes v ON e.election_id = v.election_id
      GROUP BY e.election_id, e.title
      ORDER BY e.created_at DESC
    `);

    res.json({
      totalVoters: userCounts.voter || 0,
      totalCandidates: userCounts.candidate || 0,
      totalAdmins: userCounts.admin || 0,
      totalElections,
      totalVotes,
      elections: electionsResult.rows.map(e => ({
        electionId: e.election_id,
        title: e.title,
        voteCount: e.vote_count
      }))
    });
  } catch (error) {
    console.error('Error fetching reports:', error);
    res.status(500).json({ error: 'Failed to fetch reports' });
  }
});

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
      SELECT c.candidate_id, u.full_name, COUNT(v.vote_id) as votes
      FROM candidates c
      LEFT JOIN users u ON c.user_id = u.user_id
      LEFT JOIN votes v ON c.candidate_id = v.candidate_id AND v.election_id = $1
      WHERE c.position_id = $2
      GROUP BY c.candidate_id, u.full_name
      ORDER BY votes DESC
    `, [req.params.id, election.position_id]);

    // Get voter participation
    const participationResult = await query(`
      SELECT COUNT(DISTINCT voter_id) as participated_voters
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
        votes: row.votes
      })),
      participatedVoters: participationResult.rows[0].participated_voters
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
