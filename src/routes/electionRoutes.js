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

// Get positions associated with a specific election
router.get('/positions/election/:electionId', async (req, res) => {
  try {
    const result = await query(`
      SELECT p.*
      FROM elections e
      LEFT JOIN positions p ON e.position_id = p.position_id
      WHERE e.election_id = $1
    `, [req.params.electionId]);

    const positions = result.rows
      .filter(p => p && p.position_id)
      .map(p => formatPositionResponse(p));

    res.json(positions);
  } catch (error) {
    console.error('Error fetching positions for election:', error);
    res.status(500).json({ error: 'Failed to fetch positions for election' });
  }
});

// Get open elections
router.get('/elections/open', async (req, res) => {
  try {
    const now = Date.now();
    const result = await query(`
      SELECT e.*, p.name as position_name
      FROM elections e
      LEFT JOIN positions p ON e.position_id = p.position_id
      WHERE e.status = 'open' AND e.start_date <= $1 AND e.end_date >= $1
      ORDER BY e.end_date ASC
    `, [now]);

    const elections = result.rows.map(e => formatElectionResponse(e));
    res.json(elections);
  } catch (error) {
    console.error('Error fetching open elections:', error);
    res.status(500).json({ error: 'Failed to fetch open elections' });
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

    res.status(201).json({
      message: 'Election created successfully',
      election: formatElectionResponse(result.rows[0])
    });
  } catch (error) {
    console.error('Error creating election:', error);
    res.status(500).json({ error: 'Failed to create election' });
  }
});

async function updateElectionStatusHandler(req, res) {
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

    res.json({
      message: 'Election status updated successfully',
      election: formatElectionResponse(result.rows[0])
    });
  } catch (error) {
    console.error('Error updating election:', error);
    res.status(500).json({ error: 'Failed to update election' });
  }
}

// Update election status (admin only)
router.patch('/elections/:id/status', authenticateToken, requireRole('admin'), updateElectionStatusHandler);
router.put('/elections/:id/status', authenticateToken, requireRole('admin'), updateElectionStatusHandler);

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

// Get courses by faculty ID (path-style alias)
router.get('/data/courses/faculty/:facultyId', async (req, res) => {
  try {
    const result = await query(
      'SELECT * FROM courses WHERE faculty_id = $1 ORDER BY name',
      [req.params.facultyId]
    );

    const courses = result.rows.map(c => ({
      courseId: c.course_id,
      facultyId: c.faculty_id,
      name: c.name
    }));
    res.json(courses);
  } catch (error) {
    console.error('Error fetching courses by faculty:', error);
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

// Subject combinations aliases expected by Android client
router.get('/data/subjects', async (req, res) => {
  try {
    const result = await query('SELECT * FROM subject_combinations ORDER BY name');
    const combinations = result.rows.map(c => ({
      id: c.id,
      courseId: c.course_id,
      name: c.name
    }));
    res.json(combinations);
  } catch (error) {
    console.error('Error fetching subjects:', error);
    res.status(500).json({ error: 'Failed to fetch subjects' });
  }
});

router.get('/data/subjects/course/:courseId', async (req, res) => {
  try {
    const result = await query(
      'SELECT * FROM subject_combinations WHERE course_id = $1 ORDER BY name',
      [req.params.courseId]
    );
    const combinations = result.rows.map(c => ({
      id: c.id,
      courseId: c.course_id,
      name: c.name
    }));
    res.json(combinations);
  } catch (error) {
    console.error('Error fetching subjects by course:', error);
    res.status(500).json({ error: 'Failed to fetch subjects' });
  }
});

// Create faculty (admin only)
router.post('/data/faculties', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { name } = req.body;
    if (!name || !name.trim()) {
      return res.status(400).json({ error: 'Faculty name is required' });
    }

    const facultyId = uuidv4();
    await query(
      'INSERT INTO faculties (faculty_id, name, created_at) VALUES ($1, $2, $3)',
      [facultyId, name.trim(), Date.now()]
    );

    res.status(201).json({ facultyId, name: name.trim() });
  } catch (error) {
    if (error.code === '23505') {
      return res.status(400).json({ error: 'Faculty already exists' });
    }
    console.error('Error creating faculty:', error);
    res.status(500).json({ error: 'Failed to create faculty' });
  }
});

// Update faculty (admin only)
router.put('/data/faculties/:id', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { name } = req.body;
    if (!name || !name.trim()) {
      return res.status(400).json({ error: 'Faculty name is required' });
    }

    const result = await query(
      'UPDATE faculties SET name = $1 WHERE faculty_id = $2 RETURNING faculty_id',
      [name.trim(), req.params.id]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'Faculty not found' });
    }

    res.json({ message: 'Faculty updated successfully' });
  } catch (error) {
    if (error.code === '23505') {
      return res.status(400).json({ error: 'Faculty already exists' });
    }
    console.error('Error updating faculty:', error);
    res.status(500).json({ error: 'Failed to update faculty' });
  }
});

// Delete faculty (admin only)
router.delete('/data/faculties/:id', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const result = await query(
      'DELETE FROM faculties WHERE faculty_id = $1 RETURNING faculty_id',
      [req.params.id]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'Faculty not found' });
    }

    res.json({ message: 'Faculty deleted successfully' });
  } catch (error) {
    console.error('Error deleting faculty:', error);
    res.status(500).json({ error: 'Failed to delete faculty' });
  }
});

// Create course (admin only)
router.post('/data/courses', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { facultyId, name } = req.body;
    if (!facultyId || !name || !name.trim()) {
      return res.status(400).json({ error: 'Faculty ID and course name are required' });
    }

    const courseId = uuidv4();
    await query(
      'INSERT INTO courses (course_id, faculty_id, name, created_at) VALUES ($1, $2, $3, $4)',
      [courseId, facultyId, name.trim(), Date.now()]
    );

    res.status(201).json({ courseId, facultyId, name: name.trim() });
  } catch (error) {
    if (error.code === '23505') {
      return res.status(400).json({ error: 'Course already exists' });
    }
    console.error('Error creating course:', error);
    res.status(500).json({ error: 'Failed to create course' });
  }
});

// Update course (admin only)
router.put('/data/courses/:id', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { facultyId, name } = req.body;
    if (!facultyId || !name || !name.trim()) {
      return res.status(400).json({ error: 'Faculty ID and course name are required' });
    }

    const result = await query(
      'UPDATE courses SET faculty_id = $1, name = $2 WHERE course_id = $3 RETURNING course_id',
      [facultyId, name.trim(), req.params.id]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'Course not found' });
    }

    res.json({ message: 'Course updated successfully' });
  } catch (error) {
    if (error.code === '23505') {
      return res.status(400).json({ error: 'Course already exists' });
    }
    console.error('Error updating course:', error);
    res.status(500).json({ error: 'Failed to update course' });
  }
});

// Delete course (admin only)
router.delete('/data/courses/:id', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const result = await query(
      'DELETE FROM courses WHERE course_id = $1 RETURNING course_id',
      [req.params.id]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'Course not found' });
    }

    res.json({ message: 'Course deleted successfully' });
  } catch (error) {
    console.error('Error deleting course:', error);
    res.status(500).json({ error: 'Failed to delete course' });
  }
});

// Create subject (admin only)
router.post('/data/subjects', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { courseId, name } = req.body;
    if (!courseId || !name || !name.trim()) {
      return res.status(400).json({ error: 'Course ID and subject name are required' });
    }

    const id = uuidv4();
    await query(
      'INSERT INTO subject_combinations (id, course_id, name, created_at) VALUES ($1, $2, $3, $4)',
      [id, courseId, name.trim(), Date.now()]
    );

    res.status(201).json({ id, courseId, name: name.trim() });
  } catch (error) {
    if (error.code === '23505') {
      return res.status(400).json({ error: 'Subject combination already exists' });
    }
    console.error('Error creating subject:', error);
    res.status(500).json({ error: 'Failed to create subject' });
  }
});

// Update subject (admin only)
router.put('/data/subjects/:id', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const { courseId, name } = req.body;
    if (!courseId || !name || !name.trim()) {
      return res.status(400).json({ error: 'Course ID and subject name are required' });
    }

    const result = await query(
      'UPDATE subject_combinations SET course_id = $1, name = $2 WHERE id = $3 RETURNING id',
      [courseId, name.trim(), req.params.id]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'Subject not found' });
    }

    res.json({ message: 'Subject updated successfully' });
  } catch (error) {
    if (error.code === '23505') {
      return res.status(400).json({ error: 'Subject combination already exists' });
    }
    console.error('Error updating subject:', error);
    res.status(500).json({ error: 'Failed to update subject' });
  }
});

// Delete subject (admin only)
router.delete('/data/subjects/:id', authenticateToken, requireRole('admin'), async (req, res) => {
  try {
    const result = await query(
      'DELETE FROM subject_combinations WHERE id = $1 RETURNING id',
      [req.params.id]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'Subject not found' });
    }

    res.json({ message: 'Subject deleted successfully' });
  } catch (error) {
    console.error('Error deleting subject:', error);
    res.status(500).json({ error: 'Failed to delete subject' });
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
