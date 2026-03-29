import express from 'express';
import bcryptjs from 'bcryptjs';
import { v4 as uuidv4 } from 'uuid';
import { generateToken, authenticateToken, getUserById } from '../auth.js';
import { query, seedAdminUser } from '../db.js';
import emailService from '../utils/emailService.js';
import verificationCodeManager from '../utils/verificationCodeManager.js';

const router = express.Router();
const EMAIL_PATTERN = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

function normalizeEmail(email) {
  return (email || '').trim().toLowerCase();
}

function isValidEmail(email) {
  return EMAIL_PATTERN.test(normalizeEmail(email));
}

// Send registration verification code (email)
router.post('/send-registration-code', async (req, res) => {
  try {
    const { email } = req.body;
    const normalizedEmail = normalizeEmail(email);

    if (!normalizedEmail) {
      return res.status(400).json({ error: 'Email is required' });
    }

    if (!isValidEmail(normalizedEmail)) {
      return res.status(400).json({ error: 'Invalid email format' });
    }

    // Check if email already exists
    const existingUser = await query(
      'SELECT user_id FROM users WHERE LOWER(email) = $1',
      [normalizedEmail]
    );

    if (existingUser.rows.length > 0) {
      return res.status(400).json({ error: 'Email already registered' });
    }

    // Generate verification code
    const code = Math.floor(100000 + Math.random() * 900000).toString();

    // Store code in memory manager
    const codeStored = verificationCodeManager.storeCode(normalizedEmail, code, 'registration');
    if (!codeStored) {
      return res.status(500).json({ error: 'Could not prepare verification code' });
    }

    // Send email
    const emailSent = await emailService.sendVerificationCode(normalizedEmail, code, 'registration');

    if (!emailSent) {
      return res.status(500).json({ error: 'Failed to send verification code. Please check email configuration.' });
    }

    res.json({ 
      message: 'Verification code sent to email',
      email: normalizedEmail
    });

  } catch (error) {
    console.error('Send registration code error:', error);
    res.status(500).json({ error: 'Failed to send verification code' });
  }
});

// Verify registration code
router.post('/verify-registration-code', async (req, res) => {
  try {
    const { email, code } = req.body;
    const normalizedEmail = normalizeEmail(email);
    const normalizedCode = (code || '').toString().trim();

    if (!normalizedEmail || !normalizedCode) {
      return res.status(400).json({ error: 'Email and code are required' });
    }

    // Verify the code
    const isValid = verificationCodeManager.verifyCode(normalizedEmail, normalizedCode, 'registration');

    if (!isValid) {
      return res.status(400).json({ error: 'Invalid or expired verification code' });
    }

    // Code is valid - delete it so it can't be reused
    verificationCodeManager.deleteCode(normalizedEmail, 'registration');
    verificationCodeManager.markRegistrationVerified(normalizedEmail);

    res.json({ 
      message: 'Email verified successfully',
      email: normalizedEmail
    });

  } catch (error) {
    console.error('Verify registration code error:', error);
    res.status(500).json({ error: 'Code verification failed' });
  }
});

// Register endpoint (after email verification)
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
    const normalizedEmail = normalizeEmail(email);
    const normalizedRole = (role || 'voter').toLowerCase();
    const requiresEmailVerification = normalizedRole !== 'admin';

    // Validation
    if (!username || !password || !fullName || !normalizedEmail) {
      return res.status(400).json({ error: 'Username, password, full name, and email are required' });
    }

    if (!isValidEmail(normalizedEmail)) {
      return res.status(400).json({ error: 'Invalid email format' });
    }

    if (requiresEmailVerification && !verificationCodeManager.isRegistrationVerified(normalizedEmail)) {
      return res.status(403).json({ error: 'Email verification required before registration' });
    }

    // Check if username exists
    const existingUser = await query(
      'SELECT user_id FROM users WHERE username = $1',
      [username]
    );

    if (existingUser.rows.length > 0) {
      return res.status(400).json({ error: 'Username already exists' });
    }

    // Check if email exists
    const existingEmail = await query(
      'SELECT user_id FROM users WHERE LOWER(email) = $1',
      [normalizedEmail]
    );

    if (existingEmail.rows.length > 0) {
      return res.status(400).json({ error: 'Email already registered' });
    }

    const userId = uuidv4();
    const passwordHash = await bcryptjs.hash(password, 12);
    const now = Date.now();

    // Auto-verify voters with complete info, always verify admins
    const shouldVerifyAutomatically = (normalizedRole === 'voter' || normalizedRole === 'admin') &&
      regNo && faculty && course && phoneNumber;

    // Create user
    await query(
      `INSERT INTO users (
        user_id, username, password_hash, full_name, email, phone_number, role,
        reg_no, faculty, course, subject_combination, level_of_study, year_of_study,
        gender, disability, manifesto, is_verified, created_at, updated_at
      ) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14, $15, $16, $17, $18, $19)`,
      [
        userId, username, passwordHash, fullName, normalizedEmail, phoneNumber, normalizedRole,
        regNo, faculty, course, subjectCombination, levelOfStudy, yearOfStudy,
        gender, disability, manifesto,
        normalizedRole === 'admin' ? true : shouldVerifyAutomatically,
        now, now
      ]
    );

    // Create candidate record if registering as candidate
    if (normalizedRole === 'candidate' && positionId) {
      await query(
        `INSERT INTO candidates (candidate_id, position_id, user_id, manifesto, created_at)
         VALUES ($1, $2, $3, $4, $5)`,
        [uuidv4(), positionId, userId, manifesto || '', now]
      );
    }

    verificationCodeManager.clearRegistrationVerified(normalizedEmail);

    // Generate token
    const token = generateToken(userId, normalizedRole);

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

// Login endpoint (sends verification code to email)
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

    const userEmail = normalizeEmail(user.email);
    if (!userEmail) {
      return res.status(400).json({ error: 'No email is configured for this account. Contact administrator.' });
    }

    // Generate verification code
    const verificationCode = Math.floor(100000 + Math.random() * 900000).toString();

    // Store code in memory manager
    const codeStored = verificationCodeManager.storeCode(userEmail, verificationCode, 'login');
    if (!codeStored) {
      return res.status(500).json({ error: 'Could not prepare login verification code' });
    }

    // Send verification code to email
    const emailSent = await emailService.sendVerificationCode(userEmail, verificationCode, 'login');

    if (!emailSent) {
      return res.status(500).json({ error: 'Failed to send login verification code. Please check email configuration.' });
    }

    // Return user info and userId for verification step
    res.json({
      message: 'Login credentials verified. Verification code sent to email.',
      userId: user.user_id,
      email: userEmail,
      user: formatUserResponse(user)
    });

  } catch (error) {
    console.error('Login error:', error);
    res.status(500).json({ error: 'Login failed' });
  }
});

// Verify login code
router.post('/verify-login-code', async (req, res) => {
  try {
    const { email, code } = req.body;
    const normalizedEmail = normalizeEmail(email);
    const normalizedCode = (code || '').toString().trim();

    if (!normalizedEmail || !normalizedCode) {
      return res.status(400).json({ error: 'Email and code are required' });
    }

    // Verify the code
    const isValid = verificationCodeManager.verifyCode(normalizedEmail, normalizedCode, 'login');

    if (!isValid) {
      return res.status(400).json({ error: 'Invalid or expired verification code' });
    }

    // Code is valid - delete it so it can't be reused
    verificationCodeManager.deleteCode(normalizedEmail, 'login');

    // Get user info
    const userResult = await query(
      'SELECT * FROM users WHERE LOWER(email) = $1',
      [normalizedEmail]
    );

    if (userResult.rows.length === 0) {
      return res.status(404).json({ error: 'User not found' });
    }

    const user = userResult.rows[0];

    // Generate auth token
    const token = generateToken(user.user_id, user.role);

    res.json({
      message: 'Login successful',
      token,
      user: formatUserResponse(user)
    });

  } catch (error) {
    console.error('Verify login code error:', error);
    res.status(500).json({ error: 'Code verification failed' });
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

// Update current user profile
router.put('/me', authenticateToken, async (req, res) => {
  try {
    const {
      fullName,
      email,
      phoneNumber,
      manifesto
    } = req.body;

    const normalizedEmail = email ? normalizeEmail(email) : null;
    if (normalizedEmail && !isValidEmail(normalizedEmail)) {
      return res.status(400).json({ error: 'Invalid email format' });
    }

    const result = await query(
      `UPDATE users
       SET full_name = COALESCE($1, full_name),
           email = COALESCE($2, email),
           phone_number = COALESCE($3, phone_number),
           manifesto = COALESCE($4, manifesto),
           updated_at = $5
       WHERE user_id = $6
       RETURNING *`,
      [
        fullName || null,
        normalizedEmail || null,
        phoneNumber || null,
        manifesto || null,
        Date.now(),
        req.userId
      ]
    );

    if (result.rows.length === 0) {
      return res.status(404).json({ error: 'User not found' });
    }

    res.json({
      message: 'Profile updated successfully',
      user: formatUserResponse(result.rows[0])
    });
  } catch (error) {
    console.error('Error updating profile:', error);
    res.status(500).json({ error: 'Failed to update profile' });
  }
});

// Send password reset code (email)
router.post('/send-password-reset-code', async (req, res) => {
  try {
    const { email } = req.body;
    const normalizedEmail = normalizeEmail(email);

    if (!normalizedEmail) {
      return res.status(400).json({ error: 'Email is required' });
    }

    // Check if user exists with this email
    const userResult = await query(
      'SELECT user_id, email FROM users WHERE LOWER(email) = $1',
      [normalizedEmail]
    );

    if (userResult.rows.length === 0) {
      // Don't reveal if email exists for security
      return res.status(400).json({ error: 'Email not found' });
    }

    // Generate verification code
    const code = Math.floor(100000 + Math.random() * 900000).toString();

    // Store code in memory manager
    const codeStored = verificationCodeManager.storeCode(normalizedEmail, code, 'password_reset');
    if (!codeStored) {
      return res.status(500).json({ error: 'Could not prepare reset code' });
    }

    // Send email
    const emailSent = await emailService.sendVerificationCode(normalizedEmail, code, 'password_reset');

    if (!emailSent) {
      return res.status(500).json({ error: 'Failed to send reset code. Please check email configuration.' });
    }

    res.json({ 
      message: 'Password reset code sent to email',
      email: normalizedEmail
    });

  } catch (error) {
    console.error('Send password reset code error:', error);
    res.status(500).json({ error: 'Failed to send reset code' });
  }
});

// Verify password reset code
router.post('/verify-password-reset-code', async (req, res) => {
  try {
    const { email, code } = req.body;
    const normalizedEmail = normalizeEmail(email);
    const normalizedCode = (code || '').toString().trim();

    if (!normalizedEmail || !normalizedCode) {
      return res.status(400).json({ error: 'Email and code are required' });
    }

    // Verify the code
    const isValid = verificationCodeManager.verifyCode(normalizedEmail, normalizedCode, 'password_reset');

    if (!isValid) {
      return res.status(400).json({ error: 'Invalid or expired verification code' });
    }

    // Code is valid - do NOT delete it here, it will be deleted after password reset
    // This ensures the code isn't reused between verification and password reset
    res.json({ 
      message: 'Verification code is valid. You can now reset your password.',
      email: normalizedEmail,
      verified: true
    });

  } catch (error) {
    console.error('Verify password reset code error:', error);
    res.status(500).json({ error: 'Code verification failed' });
  }
});

// Reset password (after verification)
router.post('/reset-password', async (req, res) => {
  try {
    const { email, code, newPassword, confirmPassword } = req.body;
    const normalizedEmail = normalizeEmail(email);
    const normalizedCode = (code || '').toString().trim();

    if (!normalizedEmail || !normalizedCode || !newPassword || !confirmPassword) {
      return res.status(400).json({ error: 'All fields are required' });
    }

    if (newPassword !== confirmPassword) {
      return res.status(400).json({ error: 'Passwords do not match' });
    }

    // Verify code one more time
    const isValid = verificationCodeManager.verifyCode(normalizedEmail, normalizedCode, 'password_reset');

    if (!isValid) {
      return res.status(400).json({ error: 'Invalid or expired verification code' });
    }

    // Find user by email
    const userResult = await query(
      'SELECT user_id FROM users WHERE LOWER(email) = $1',
      [normalizedEmail]
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
    verificationCodeManager.deleteCode(normalizedEmail, 'password_reset');

    res.json({ message: 'Password reset successfully' });

  } catch (error) {
    console.error('Password reset error:', error);
    res.status(500).json({ error: 'Password reset failed' });
  }
});

// Send password reset code (DEPRECATED - use /send-password-reset-code instead)
router.post('/send-reset-code', async (req, res) => {
  try {
    console.warn('⚠️  DEPRECATED ENDPOINT: /send-reset-code - Use /send-password-reset-code instead');
    const { phoneNumber } = req.body;

    if (!phoneNumber) {
      return res.status(400).json({ error: 'Phone number is required' });
    }

    // Find user by phone
    const userResult = await query(
      'SELECT email FROM users WHERE phone_number = $1',
      [phoneNumber]
    );

    if (userResult.rows.length === 0) {
      return res.status(400).json({ error: 'Phone number not found' });
    }

    const email = userResult.rows[0].email;

    // Generate code  
    const code = Math.floor(100000 + Math.random() * 900000).toString();
    
    // Store code
    verificationCodeManager.storeCode(email, code, 'password_reset');

    // Send email
    const emailSent = await emailService.sendVerificationCode(email, code, 'password_reset');

    if (!emailSent) {
      return res.status(500).json({ error: 'Failed to send reset code' });
    }

    res.json({ 
      message: 'Reset code sent to email associated with phone number',
      deprecated: 'This endpoint is deprecated. Please use /api/auth/send-password-reset-code'
    });

  } catch (error) {
    console.error('Reset code error:', error);
    res.status(500).json({ error: 'Failed to send reset code' });
  }
});

// Verify reset code (DEPRECATED - use /verify-password-reset-code instead)
router.post('/verify-reset-code', async (req, res) => {
  try {
    console.warn('⚠️  DEPRECATED ENDPOINT: /verify-reset-code - Use /verify-password-reset-code instead');
    const { phoneNumber, code } = req.body;

    if (!phoneNumber || !code) {
      return res.status(400).json({ error: 'Phone number and code are required' });
    }

    // Find user by phone
    const userResult = await query(
      'SELECT email FROM users WHERE phone_number = $1',
      [phoneNumber]
    );

    if (userResult.rows.length === 0) {
      return res.status(400).json({ error: 'Phone number not found' });
    }

    const email = userResult.rows[0].email;

    // Verify code
    const isValid = verificationCodeManager.verifyCode(email, code, 'password_reset');

    if (!isValid) {
      return res.status(400).json({ error: 'Invalid or expired code' });
    }

    res.json({ 
      message: 'Code verified',
      deprecated: 'This endpoint is deprecated. Please use /api/auth/verify-password-reset-code'
    });

  } catch (error) {
    console.error('Verify code error:', error);
    res.status(500).json({ error: 'Code verification failed' });
  }
});

// Reset password with code (DEPRECATED - use /reset-password instead)
router.post('/reset-password-deprecated', async (req, res) => {
  try {
    console.warn('⚠️  DEPRECATED ENDPOINT: /reset-password-deprecated - Use /reset-password instead');
    const { phoneNumber, code, newPassword, confirmPassword } = req.body;

    if (!phoneNumber || !code || !newPassword || !confirmPassword) {
      return res.status(400).json({ error: 'All fields are required' });
    }

    if (newPassword !== confirmPassword) {
      return res.status(400).json({ error: 'Passwords do not match' });
    }

    // Find user by phone
    const userResult = await query(
      'SELECT user_id, email FROM users WHERE phone_number = $1',
      [phoneNumber]
    );

    if (userResult.rows.length === 0) {
      return res.status(404).json({ error: 'User not found' });
    }

    const user = userResult.rows[0];
    const passwordHash = await bcryptjs.hash(newPassword, 12);

    // Update password
    await query(
      'UPDATE users SET password_hash = $1, updated_at = $2 WHERE user_id = $3',
      [passwordHash, Date.now(), user.user_id]
    );

    // Delete used reset code
    verificationCodeManager.deleteCode(user.email, 'password_reset');

    res.json({ 
      message: 'Password reset successfully',
      deprecated: 'This endpoint is deprecated. Please use /api/auth/reset-password'
    });

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

// Diagnostic endpoint for admin user seeding (FOR TESTING ONLY)
router.post('/diagnostic/seed-admin', async (req, res) => {
  console.log('\n🔐 ADMIN SEEDING DIAGNOSTIC ENDPOINT CALLED');
  try {
    // Manually call seedAdminUser
    await seedAdminUser();
    
    // Check if admin user exists now
    const adminCheck = await query(
      'SELECT user_id, username, email, role, is_verified FROM users WHERE username = $1',
      ['admin']
    );

    if (adminCheck.rows.length > 0) {
      const admin = adminCheck.rows[0];
      res.json({
        status: 'success',
        message: 'Admin user seeding completed',
        adminUser: {
          user_id: admin.user_id,
          username: admin.username,
          email: admin.email,
          role: admin.role,
          is_verified: admin.is_verified
        }
      });
    } else {
      res.json({
        status: 'failed',
        message: 'Admin seeding ran but user not found in database',
        adminUser: null
      });
    }
  } catch (error) {
    res.status(500).json({
      status: 'error',
      message: 'Admin seeding failed',
      error: error.message
    });
  }
});

// Admin user status check endpoint
router.get('/diagnostic/admin-status', async (req, res) => {
  try {
    const result = await query(
      'SELECT user_id, username, email, role, is_verified, password_hash FROM users WHERE username = $1',
      ['admin']
    );

    if (result.rows.length === 0) {
      return res.json({
        status: 'not_found',
        message: 'Admin user does not exist in database',
        adminExists: false
      });
    }

    const admin = result.rows[0];
    res.json({
      status: 'found',
      adminExists: true,
      user: {
        user_id: admin.user_id,
        username: admin.username,
        email: admin.email,
        role: admin.role,
        is_verified: admin.is_verified,
        passwordHashPrefix: admin.password_hash.substring(0, 30) + '...'
      }
    });
  } catch (error) {
    res.status(500).json({
      status: 'error',
      message: 'Error checking admin status',
      error: error.message
    });
  }
});

export default router;
