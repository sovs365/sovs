import jwt from 'jsonwebtoken';
import { query } from './db.js';

const JWT_SECRET = process.env.JWT_SECRET || 'insecure-default-secret-for-development-only';
const JWT_ISSUER = process.env.JWT_ISSUER || 'university-voting-app';
const JWT_AUDIENCE = process.env.JWT_AUDIENCE || 'university-voting-users';
const JWT_EXPIRATION_MS = parseInt(process.env.JWT_EXPIRATION_MS || '86400000'); // 24 hours

export function generateToken(userId, role) {
  return jwt.sign(
    {
      sub: userId,
      role: role,
    },
    JWT_SECRET,
    {
      issuer: JWT_ISSUER,
      audience: JWT_AUDIENCE,
      expiresIn: Math.floor(JWT_EXPIRATION_MS / 1000),
      algorithm: 'HS256'
    }
  );
}

export function verifyToken(token) {
  try {
    return jwt.verify(token, JWT_SECRET, {
      issuer: JWT_ISSUER,
      audience: JWT_AUDIENCE,
      algorithms: ['HS256']
    });
  } catch (error) {
    return null;
  }
}

export function authenticateToken(req, res, next) {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1]; // Bearer TOKEN

  if (!token) {
    return res.status(401).json({ error: 'Access token required' });
  }

  const decoded = verifyToken(token);
  if (!decoded) {
    return res.status(403).json({ error: 'Invalid or expired token' });
  }

  req.userId = decoded.sub;
  req.userRole = decoded.role;
  next();
}

export function requireRole(...roles) {
  return (req, res, next) => {
    if (!roles.includes(req.userRole)) {
      return res.status(403).json({ error: 'Insufficient permissions' });
    }
    next();
  };
}

export async function getUserById(userId) {
  try {
    const result = await query(
      'SELECT * FROM users WHERE user_id = $1',
      [userId]
    );
    return result.rows[0] || null;
  } catch (error) {
    console.error('Error fetching user:', error);
    return null;
  }
}

export async function getUserByUsername(username) {
  try {
    const result = await query(
      'SELECT * FROM users WHERE username = $1',
      [username]
    );
    return result.rows[0] || null;
  } catch (error) {
    console.error('Error fetching user:', error);
    return null;
  }
}
