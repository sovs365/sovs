import express from 'express';
import { v4 as uuidv4 } from 'uuid';
import { authenticateToken, requireRole } from '../auth.js';
import { query } from '../db.js';

const router = express.Router();

// Get all positions
router.get('/positions', async (req, res) => {
  try {
    const result = await query('SELECT * FROM positions ORDER BY created_at DESC');
    const positions = result.rows.map(p => formatPositionResponse(p));
    res.json(positions);
  } catch (error) {
    console.error('Error fetching positions:', error);
    res.status(500).json({ error: 'Failed to fetch positions' });
  }
});

// Get position by ID
router.get('/positions/:id', async (req, res) => {
  try {
    const result = await query(
      'SELECT * FROM positions WHERE position_id = $1',
      [req.params.id]
    );
    
    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'Position not found' });
    }

    res.json(formatPositionResponse(result.rows[0]));
  } catch (error) {
    console.error('Error fetching position:', error);
    res.status(500).json({ error: 'Failed to fetch position' });
  }
});

// Create position (admin only)
router.post('/positions', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const {
      name,
      type = 'general',
      facultyId,
      courseId,
      subjectCombinationId,
      yearOfStudy
    } = req.body;

    if (!name) {
      return res.status(400).json({ error: 'Position name is required' });
    }

    const positionId = uuidv4();
    const now = Date.now();

    await query(
      `INSERT INTO positions (position_id, name, type, faculty_id, course_id, subject_combination_id, year_of_study, created_at)
       VALUES ($1, $2, $3, $4, $5, $6, $7, $8)`,
      [positionId, name, type, facultyId, courseId, subjectCombinationId, yearOfStudy, now]
    );

    const result = await query(
      'SELECT * FROM positions WHERE position_id = $1',
      [positionId]
    );

    res.status(201).json(formatPositionResponse(result.rows[0]));
  } catch (error) {
    console.error('Error creating position:', error);
    res.status(500).json({ error: 'Failed to create position' });
  }
});

// Get all elections
router.get('/elections', async (req, res) => {
  try {
    const result = await query(`
      SELECT e.*, p.name as position_name
      FROM elections e
      LEFT JOIN positions p ON e.position_id = p.position_id
      ORDER BY e.created_at DESC
    `);

    const elections = result.rows.map(e => formatElectionResponse(e));
    res.json(elections);
  } catch (error) {
    console.error('Error fetching elections:', error);
    res.status(500).json({ error: 'Failed to fetch elections' });
  }
});

// Get election by ID
router.get('/elections/:id', async (req, res) => {
  try {
    const result = await query(
      `SELECT e.*, p.name as position_name
       FROM elections e
       LEFT JOIN positions p ON e.position_id = p.position_id
       WHERE e.election_id = $1`,
      [req.params.id]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'Election not found' });
    }

    res.json(formatElectionResponse(result.rows[0]));
  } catch (error) {
    console.error('Error fetching election:', error);
    res.status(500).json({ error: 'Failed to fetch election' });
  }
});

// Create election (admin only)
router.post('/elections', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { title, description, positionId, startDate, endDate } = req.body;

    if (!title || !positionId || !startDate || !endDate) {
      return res.status(400).json({ error: 'Missing required fields' });
    }

    const electionId = uuidv4();
    const now = Date.now();

    await query(
      `INSERT INTO elections (election_id, position_id, title, description, start_date, end_date, status, created_at, updated_at)
       VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)`,
      [electionId, positionId, title, description, startDate, endDate, 'draft', now, now]
    );

    const result = await query(
      `SELECT e.*, p.name as position_name
       FROM elections e
       LEFT JOIN positions p ON e.position_id = p.position_id
       WHERE e.election_id = $1`,
      [electionId]
    );

    res.status(201).json(formatElectionResponse(result.rows[0]));
  } catch (error) {
    console.error('Error creating election:', error);
    res.status(500).json({ error: 'Failed to create election' });
  }
});

// Update election status (admin only)
router.patch('/elections/:id/status', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { status } = req.body;
    const validStatuses = ['draft', 'open', 'closed'];

    if (!validStatuses.includes(status)) {
      return res.status(400).json({ error: 'Invalid status' });
    }

    await query(
      'UPDATE elections SET status = $1, updated_at = $2 WHERE election_id = $3',
      [status, Date.now(), req.params.id]
    );

    const result = await query(
      `SELECT e.*, p.name as position_name
       FROM elections e
       LEFT JOIN positions p ON e.position_id = p.position_id
       WHERE e.election_id = $1`,
      [req.params.id]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'Election not found' });
    }

    res.json(formatElectionResponse(result.rows[0]));
  } catch (error) {
    console.error('Error updating election:', error);
    res.status(500).json({ error: 'Failed to update election' });
  }
});

// Get faculties
router.get('/data/faculties', async (req, res) => {
  try {
    const result = await query('SELECT * FROM faculties ORDER BY name');
    const faculties = result.rows.map(f => ({
      facultyId: f.faculty_id,
      name: f.name
    }));
    res.json(faculties);
  } catch (error) {
    console.error('Error fetching faculties:', error);
    res.status(500).json({ error: 'Failed to fetch faculties' });
  }
});

// Get courses for a faculty
router.get('/data/courses', async (req, res) => {
  try {
    const { facultyId } = req.query;
    let queryStr = 'SELECT * FROM courses ORDER BY name';
    let params = [];

    if (facultyId) {
      queryStr = 'SELECT * FROM courses WHERE faculty_id = $1 ORDER BY name';
      params = [facultyId];
    }

    const result = await query(queryStr, params);
    const courses = result.rows.map(c => ({
      courseId: c.course_id,
      facultyId: c.faculty_id,
      name: c.name
    }));
    res.json(courses);
  } catch (error) {
    console.error('Error fetching courses:', error);
    res.status(500).json({ error: 'Failed to fetch courses' });
  }
});

// Get subject combinations for a course
router.get('/data/subject-combinations', async (req, res) => {
  try {
    const { courseId } = req.query;
    let queryStr = 'SELECT * FROM subject_combinations ORDER BY name';
    let params = [];

    if (courseId) {
      queryStr = 'SELECT * FROM subject_combinations WHERE course_id = $1 ORDER BY name';
      params = [courseId];
    }

    const result = await query(queryStr, params);
    const combinations = result.rows.map(c => ({
      id: c.id,
      courseId: c.course_id,
      name: c.name
    }));
    res.json(combinations);
  } catch (error) {
    console.error('Error fetching subject combinations:', error);
    res.status(500).json({ error: 'Failed to fetch subject combinations' });
  }
});

function formatPositionResponse(position) {
  return {
    positionId: position.position_id,
    name: position.name,
    type: position.type,
    facultyId: position.faculty_id,
    courseId: position.course_id,
    subjectCombinationId: position.subject_combination_id,
    yearOfStudy: position.year_of_study
  };
}

function formatElectionResponse(election) {
  return {
    electionId: election.election_id,
    positionId: election.position_id,
    title: election.title,
    description: election.description,
    startDate: election.start_date,
    endDate: election.end_date,
    status: election.status,
    positionName: election.position_name
  };
}

export default router;
