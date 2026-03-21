import express from 'express';
import bcryptjs from 'bcryptjs';
import { v4 as uuidv4 } from 'uuid';
import { generateToken, authenticateToken, getUserById } from '../auth.js';
import { query } from '../db.js';

const router = express.Router();

// Register endpoint
router.post('/register', async (req, res) => {
  try {
    const {
      username,
      password,
      fullName,
      email,
      phoneNumber,
      role,
      regNo,
      faculty,
      course,
      subjectCombination,
      levelOfStudy,
      yearOfStudy,
      gender,
      disability,
      manifesto,
      positionId
    } = req.body;

    // Validation
    if (!username || !password || !fullName) {
      return res.status(400).json({ error: 'Username, password, and full name are required' });
    }

    // Check if username exists
    const existingUser = await query(
      'SELECT user_id FROM users WHERE username = $1',
      [username]
    );

    if (existingUser.rows.length > 0) {
      return res.status(400).json({ error: 'Username already exists' });
    }

    const userId = uuidv4();
    const passwordHash = await bcryptjs.hash(password, 12);
    const now = Date.now();

    // Auto-verify voters with complete info, always verify admins
    const shouldVerifyAutomatically = (role === 'voter' || role === 'admin') &&
      regNo && faculty && course && phoneNumber;

    // Create user
    await query(
      `INSERT INTO users (
        user_id, username, password_hash, full_name, email, phone_number, role,
        reg_no, faculty, course, subject_combination, level_of_study, year_of_study,
        gender, disability, manifesto, is_verified, created_at, updated_at
      ) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14, $15, $16, $17, $18, $19)`,
      [
        userId, username, passwordHash, fullName, email, phoneNumber, role || 'voter',
        regNo, faculty, course, subjectCombination, levelOfStudy, yearOfStudy,
        gender, disability, manifesto,
        role === 'admin' ? true : shouldVerifyAutomatically,
        now, now
      ]
    );

    // Create candidate record if registering as candidate
    if (role === 'candidate' && positionId) {
      await query(
        `INSERT INTO candidates (candidate_id, position_id, user_id, manifesto, created_at)
         VALUES ($1, $2, $3, $4, $5)`,
        [uuidv4(), positionId, userId, manifesto || '', now]
      );
    }

    // Generate token
    const token = generateToken(userId, role || 'voter');

    // Fetch user data
    const user = await getUserById(userId);

    res.status(201).json({
      token,
      user: formatUserResponse(user)
    });

  } catch (error) {
    console.error('Registration error:', error);
    res.status(500).json({ error: 'Registration failed' });
  }
});

// Login endpoint
router.post('/login', async (req, res) => {
  try {
    const { username, password } = req.body;

    if (!username || !password) {
      return res.status(400).json({ error: 'Username and password are required' });
    }

    const result = await query(
      'SELECT * FROM users WHERE username = $1',
      [username]
    );

    const user = result.rows[0];
    if (!user) {
      return res.status(401).json({ error: 'Invalid credentials' });
    }

    const passwordValid = await bcryptjs.compare(password, user.password_hash);
    if (!passwordValid) {
      return res.status(401).json({ error: 'Invalid credentials' });
    }

    if (user.is_locked) {
      return res.status(403).json({ error: 'Account is locked' });
    }

    const token = generateToken(user.user_id, user.role);

    res.json({
      token,
      user: formatUserResponse(user)
    });

  } catch (error) {
    console.error('Login error:', error);
    res.status(500).json({ error: 'Login failed' });
  }
});

// Get current user info
router.get('/me', authenticateToken, async (req, res) => {
  try {
    const user = await getUserById(req.userId);
    if (!user) {
      return res.status(404).json({ error: 'User not found' });
    }
    res.json({ user: formatUserResponse(user) });
  } catch (error) {
    console.error('Error fetching user:', error);
    res.status(500).json({ error: 'Failed to fetch user' });
  }
});

// Send password reset code
router.post('/send-reset-code', async (req, res) => {
  try {
    const { phoneNumber } = req.body;

    if (!phoneNumber) {
      return res.status(400).json({ error: 'Phone number is required' });
    }

    // In production, send SMS via service like Twilio
    // For now, generate and store in DB
    const resetCode = Math.random().toString().substring(2, 8);
    const resetId = uuidv4();
    const expiresAt = Date.now() + 10 * 60 * 1000; // 10 minutes

    await query(
      `INSERT INTO password_reset_codes (id, phone_number, code, expires_at, created_at)
       VALUES ($1, $2, $3, $4, $5)`,
      [resetId, phoneNumber, resetCode, expiresAt, Date.now()]
    );

    // TODO: Send SMS to phoneNumber with resetCode
    console.log(`Reset code for ${phoneNumber}: ${resetCode}`);

    res.json({ message: 'Reset code sent' });

  } catch (error) {
    console.error('Reset code error:', error);
    res.status(500).json({ error: 'Failed to send reset code' });
  }
});

// Verify reset code
router.post('/verify-reset-code', async (req, res) => {
  try {
    const { phoneNumber, code } = req.body;

    if (!phoneNumber || !code) {
      return res.status(400).json({ error: 'Phone number and code are required' });
    }

    const result = await query(
      `SELECT * FROM password_reset_codes 
       WHERE phone_number = $1 AND code = $2 AND expires_at > $3
       ORDER BY created_at DESC LIMIT 1`,
      [phoneNumber, code, Date.now()]
    );

    if (result.rows.length === 0) {
      return res.status(400).json({ error: 'Invalid or expired code' });
    }

    res.json({ message: 'Code verified' });

  } catch (error) {
    console.error('Verify code error:', error);
    res.status(500).json({ error: 'Code verification failed' });
  }
});

// Reset password with code
router.post('/reset-password', async (req, res) => {
  try {
    const { phoneNumber, code, newPassword, confirmPassword } = req.body;

    if (!phoneNumber || !code || !newPassword || !confirmPassword) {
      return res.status(400).json({ error: 'All fields are required' });
    }

    if (newPassword !== confirmPassword) {
      return res.status(400).json({ error: 'Passwords do not match' });
    }

    // Verify code
    const codeResult = await query(
      `SELECT * FROM password_reset_codes 
       WHERE phone_number = $1 AND code = $2 AND expires_at > $3`,
      [phoneNumber, code, Date.now()]
    );

    if (codeResult.rows.length === 0) {
      return res.status(400).json({ error: 'Invalid or expired code' });
    }

    // Find user by phone
    const userResult = await query(
      'SELECT user_id FROM users WHERE phone_number = $1',
      [phoneNumber]
    );

    if (userResult.rows.length === 0) {
      return res.status(404).json({ error: 'User not found' });
    }

    const userId = userResult.rows[0].user_id;
    const passwordHash = await bcryptjs.hash(newPassword, 12);

    // Update password
    await query(
      'UPDATE users SET password_hash = $1, updated_at = $2 WHERE user_id = $3',
      [passwordHash, Date.now(), userId]
    );

    // Delete used reset code
    await query(
      'DELETE FROM password_reset_codes WHERE phone_number = $1 AND code = $2',
      [phoneNumber, code]
    );

    res.json({ message: 'Password reset successfully' });

  } catch (error) {
    console.error('Password reset error:', error);
    res.status(500).json({ error: 'Password reset failed' });
  }
});

function formatUserResponse(user) {
  return {
    userId: user.user_id,
    username: user.username,
    fullName: user.full_name,
    email: user.email || null,
    role: user.role,
    regNo: user.reg_no,
    faculty: user.faculty,
    course: user.course,
    subjectCombination: user.subject_combination,
    levelOfStudy: user.level_of_study,
    yearOfStudy: user.year_of_study,
    gender: user.gender,
    disability: user.disability,
    phoneNumber: user.phone_number || '',
    manifesto: user.manifesto,
    isVerified: user.is_verified,
    isLocked: user.is_locked,
    profilePhotoPath: user.profile_photo_path,
    createdAt: user.created_at
  };
}

export default router;
