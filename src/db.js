import pkg from 'pg';
const { Pool } = pkg;
import dotenv from 'dotenv';
import crypto from 'crypto';
import bcryptjs from 'bcryptjs';
import { v4 as uuidv4 } from 'uuid';

dotenv.config();

let pool = null;
let dbConnected = false;

// Initialize pool with connection error handling and retry logic
const initializePool = () => {
  try {
    pool = new Pool({
      connectionString: process.env.DATABASE_URL,
      ssl: process.env.DATABASE_URL?.includes('localhost') 
        ? false 
        : { rejectUnauthorized: false },
      connectionTimeoutMillis: 5000,
      idleTimeoutMillis: 30000,
      max: 10
    });

    pool.on('error', (err) => {
      console.error('❌ Unexpected error on idle client:', err.message);
      dbConnected = false;
      // Attempt to reconnect after 5 seconds
      setTimeout(() => {
        console.log('🔄 Attempting to reconnect to database...');
        testDatabaseConnection();
      }, 5000);
    });

    pool.on('connect', () => {
      console.log('✅ New client connected to pool');
    });

    return pool;
  } catch (error) {
    console.error('❌ Failed to create connection pool:', error.message);
    dbConnected = false;
    return null;
  }
};

// Test database connection with retry logic
async function testDatabaseConnection(retries = 3) {
  for (let attempt = 1; attempt <= retries; attempt++) {
    try {
      if (!pool) {
        pool = initializePool();
      }
      
      const client = await pool.connect();
      await client.query('SELECT NOW()');
      client.release();
      
      dbConnected = true;
      console.log('✅ Database connection test successful');
      return true;
    } catch (error) {
      console.warn(`⚠️  Database connection attempt ${attempt}/${retries} failed: ${error.message}`);
      
      if (attempt < retries) {
        const delayMs = 1000 * attempt;
        console.log(`⏳ Retrying in ${delayMs}ms...`);
        await new Promise(resolve => setTimeout(resolve, delayMs));
      }
    }
  }
  
  dbConnected = false;
  console.error('❌ Failed to connect to database after all retries');
  return false;
}

// Initialize database schema
export async function initializeDatabase() {
  if (!pool) {
    pool = initializePool();
  }

  if (!pool) {
    console.warn('⚠️  Database pool not initialized - running in mock mode');
    console.warn('   The app will start but use mock data for testing');
    dbConnected = false;
    return false;
  }

  // Test connection first
  const isConnected = await testDatabaseConnection(3);
  if (!isConnected) {
    console.warn('⚠️  Could not establish database connection');
    return false;
  }

  try {
    // Create tables
    await pool.query(`
      CREATE TABLE IF NOT EXISTS users (
        user_id VARCHAR(36) PRIMARY KEY,
        username VARCHAR(255) UNIQUE NOT NULL,
        password_hash VARCHAR(255) NOT NULL,
        full_name VARCHAR(255) NOT NULL,
        email VARCHAR(255),
        phone_number VARCHAR(20),
        role VARCHAR(50) NOT NULL DEFAULT 'voter',
        reg_no VARCHAR(100),
        faculty VARCHAR(255),
        course VARCHAR(255),
        subject_combination VARCHAR(255),
        level_of_study VARCHAR(100),
        year_of_study VARCHAR(50),
        gender VARCHAR(50),
        disability VARCHAR(255),
        manifesto TEXT,
        is_verified BOOLEAN DEFAULT FALSE,
        is_locked BOOLEAN DEFAULT FALSE,
        profile_photo_path VARCHAR(255),
        created_at BIGINT NOT NULL,
        updated_at BIGINT NOT NULL
      );
      
      CREATE TABLE IF NOT EXISTS faculties (
        faculty_id VARCHAR(36) PRIMARY KEY,
        name VARCHAR(255) NOT NULL UNIQUE,
        created_at BIGINT NOT NULL
      );
      
      CREATE TABLE IF NOT EXISTS courses (
        course_id VARCHAR(36) PRIMARY KEY,
        faculty_id VARCHAR(36) NOT NULL REFERENCES faculties(faculty_id),
        name VARCHAR(255) NOT NULL,
        created_at BIGINT NOT NULL,
        UNIQUE(faculty_id, name)
      );
      
      CREATE TABLE IF NOT EXISTS subject_combinations (
        id VARCHAR(36) PRIMARY KEY,
        course_id VARCHAR(36) NOT NULL REFERENCES courses(course_id),
        name VARCHAR(255) NOT NULL,
        created_at BIGINT NOT NULL,
        UNIQUE(course_id, name)
      );
      
      CREATE TABLE IF NOT EXISTS positions (
        position_id VARCHAR(36) PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        type VARCHAR(50) NOT NULL DEFAULT 'general',
        faculty_id VARCHAR(36) REFERENCES faculties(faculty_id),
        course_id VARCHAR(36) REFERENCES courses(course_id),
        subject_combination_id VARCHAR(36) REFERENCES subject_combinations(id),
        year_of_study VARCHAR(50),
        created_at BIGINT NOT NULL
      );
      
      CREATE TABLE IF NOT EXISTS elections (
        election_id VARCHAR(36) PRIMARY KEY,
        position_id VARCHAR(36) NOT NULL REFERENCES positions(position_id),
        title VARCHAR(255) NOT NULL,
        description TEXT,
        start_date BIGINT NOT NULL,
        end_date BIGINT NOT NULL,
        status VARCHAR(50) NOT NULL DEFAULT 'draft',
        created_at BIGINT NOT NULL,
        updated_at BIGINT NOT NULL
      );
      
      CREATE TABLE IF NOT EXISTS candidates (
        candidate_id VARCHAR(36) PRIMARY KEY,
        position_id VARCHAR(36) NOT NULL REFERENCES positions(position_id),
        user_id VARCHAR(36) NOT NULL REFERENCES users(user_id),
        manifesto TEXT,
        is_verified BOOLEAN DEFAULT FALSE,
        created_at BIGINT NOT NULL
      );
      
      CREATE TABLE IF NOT EXISTS votes (
        vote_id VARCHAR(36) PRIMARY KEY,
        election_id VARCHAR(36) NOT NULL REFERENCES elections(election_id),
        voter_id VARCHAR(36) NOT NULL REFERENCES users(user_id),
        position_id VARCHAR(36) NOT NULL REFERENCES positions(position_id),
        candidate_id VARCHAR(36) NOT NULL REFERENCES candidates(candidate_id),
        vote_hash VARCHAR(255),
        created_at BIGINT NOT NULL,
        UNIQUE(election_id, voter_id, position_id)
      );
      
      CREATE TABLE IF NOT EXISTS password_reset_codes (
        id VARCHAR(36) PRIMARY KEY,
        phone_number VARCHAR(20) NOT NULL,
        code VARCHAR(10) NOT NULL UNIQUE,
        expires_at BIGINT NOT NULL,
        created_at BIGINT NOT NULL
      );

      CREATE TABLE IF NOT EXISTS verification_codes (
        id VARCHAR(36) PRIMARY KEY,
        email VARCHAR(255) NOT NULL,
        code VARCHAR(10) NOT NULL,
        type VARCHAR(50) NOT NULL,
        expires_at BIGINT NOT NULL,
        created_at BIGINT NOT NULL,
        UNIQUE(email, type)
      );

      CREATE TABLE IF NOT EXISTS verified_registrations (
        id VARCHAR(36) PRIMARY KEY,
        email VARCHAR(255) NOT NULL UNIQUE,
        expires_at BIGINT NOT NULL,
        created_at BIGINT NOT NULL
      );
      
      CREATE TABLE IF NOT EXISTS admin_logs (
        log_id VARCHAR(36) PRIMARY KEY,
        admin_id VARCHAR(36) NOT NULL REFERENCES users(user_id),
        action VARCHAR(255) NOT NULL,
        details TEXT,
        created_at BIGINT NOT NULL
      );
      
      CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);
      CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);
      CREATE INDEX IF NOT EXISTS idx_votes_election_voter ON votes(election_id, voter_id);
      CREATE INDEX IF NOT EXISTS idx_candidates_position ON candidates(position_id);
      CREATE INDEX IF NOT EXISTS idx_elections_position ON elections(position_id);
      CREATE INDEX IF NOT EXISTS idx_verification_codes_email_type ON verification_codes(email, type);
      CREATE INDEX IF NOT EXISTS idx_verification_codes_expires_at ON verification_codes(expires_at);
      CREATE INDEX IF NOT EXISTS idx_verified_registrations_email ON verified_registrations(email);
    `);
    console.log('✅ Database tables initialized');
    return true;
  } catch (error) {
    if (!error.message.includes('already exists')) {
      console.error('⚠️  Database initialization error:', error.message);
      console.warn('   Continuing in mock mode for testing...');
    }
    dbConnected = true; // Assume tables exist
    return true;
  }
}


// Seed database with initial data (for development)
export async function seedDatabase() {
  if (!dbConnected || !pool) {
    console.warn('⚠️  Database not connected - skipping seed');
    return;
  }

  try {
    const client = await pool.connect();
    
    // Check if data already exists
    const result = await client.query('SELECT COUNT(*) FROM users');
    if (result.rows[0].count > 0) {
      console.log('✅ Database already seeded');
      client.release();
      // Still seed admin user even if DB is already seeded
      await seedAdminUser();
      return;
    }
    
    // Seed faculties
    const faculties = ['Engineering', 'Science', 'Medicine', 'Law', 'Business'];
    for (const faculty of faculties) {
      await client.query(
        'INSERT INTO faculties (faculty_id, name, created_at) VALUES ($1, $2, $3)',
        [generateId(), faculty, Date.now()]
      );
    }
    
    console.log('✅ Database seeded successfully');
    client.release();
    
    // Always seed admin user
    await seedAdminUser();
  } catch (error) {
    console.warn('⚠️  Database seeding error:', error.message);
  }
}

/**
 * Seed admin user with bcrypt-hashed password
 * Username: admin
 * Password: admin (bcrypt hashed with 12 rounds)
 */
export async function seedAdminUser() {
  console.log('\n🔐 ========== ADMIN USER SEEDING START ==========');
  
  if (!dbConnected || !pool) {
    console.error('❌ Database not connected - cannot seed admin');
    console.log('   dbConnected:', dbConnected);
    console.log('   pool:', pool ? 'exists' : 'null');
    console.log('🔐 ========== ADMIN USER SEEDING FAILED ==========\n');
    return;
  }

  let client;
  try {
    client = await pool.connect();
    console.log('✅ Database client connected');
    
    // First check if users table exists
    const tableCheck = await client.query(`
      SELECT EXISTS (
        SELECT FROM information_schema.tables 
        WHERE table_name = 'users'
      )
    `);
    
    if (!tableCheck.rows[0].exists) {
      console.error('❌ Users table does not exist!');
      console.log('🔐 ========== ADMIN USER SEEDING FAILED ==========\n');
      return;
    }
    console.log('✅ Users table exists');
    
    // Check if admin user already exists
    console.log('📋 Checking for existing admin user...');
    const adminCheck = await client.query(
      'SELECT user_id, username, role FROM users WHERE username = $1',
      ['admin']
    );

    if (adminCheck.rows.length > 0) {
      const existing = adminCheck.rows[0];
      console.log('✅ Admin user already exists');
      console.log(`   User ID: ${existing.user_id}`);
      console.log(`   Username: ${existing.username}`);
      console.log(`   Role: ${existing.role}`);
      
      // Update admin email to ensure it's correct
      const correctEmail = 'sovs.ac.ke@gmail.com';
      await client.query(
        'UPDATE users SET email = $1, updated_at = $2 WHERE user_id = $3',
        [correctEmail, Date.now(), existing.user_id]
      );
      console.log(`   Email updated to: ${correctEmail}`);
      console.log('🔐 ========== ADMIN USER SEEDING COMPLETE ==========\n');
      return;
    }
    
    console.log('📝 Admin user not found, creating new account...');

    // Hash password using bcryptjs (12 rounds for security)
    const plainPassword = 'admin';
    console.log('🔒 Hashing password with bcryptjs (12 rounds)...');
    const passwordHash = await bcryptjs.hash(plainPassword, 12);
    console.log(`   ✅ Hash created: ${passwordHash.substring(0, 20)}...`);

    // Create admin user
    const adminId = uuidv4();
    const adminEmail = 'sovs.ac.ke@gmail.com';
    const fullName = 'Administrator';

    console.log('💾 Inserting admin user into database...');
    console.log(`   User ID: ${adminId}`);
    console.log(`   Username: admin`);
    console.log(`   Full Name: ${fullName}`);
    console.log(`   Email: ${adminEmail}`);
    console.log(`   Role: admin`);
    
    const now = Date.now();
    const insertResult = await client.query(
      `INSERT INTO users (
        user_id, 
        username,
        full_name,
        email, 
        password_hash, 
        role, 
        is_verified, 
        is_locked,
        created_at,
        updated_at
      ) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10)
      RETURNING user_id, username, full_name, email, role, password_hash`,
      [
        adminId,
        'admin',
        fullName,
        adminEmail,
        passwordHash,
        'admin',
        true,
        false,
        now,
        now
      ]
    );

    const createdUser = insertResult.rows[0];
    console.log('\n✅ Admin user created successfully!');
    console.log('📋 ACCOUNT CREATED:');
    console.log(`   User ID: ${createdUser.user_id}`);
    console.log(`   Username: ${createdUser.username}`);
    console.log(`   Full Name: ${createdUser.full_name}`);
    console.log(`   Email: ${createdUser.email}`);
    console.log(`   Role: ${createdUser.role}`);
    console.log(`   Password: admin (bcrypt hashed - 12 rounds)`);
    console.log(`   Hash: ${createdUser.password_hash.substring(0, 20)}...`);
    console.log('🔐 ========== ADMIN USER SEEDING COMPLETE ==========\n');

  } catch (error) {
    console.error('❌ Admin seeding error:');
    console.error(`   Message: ${error.message}`);
    console.error(`   Code: ${error.code}`);
    console.error(`   Detail: ${error.detail}`);
    console.log('   Stack:', error.stack);
    console.log('🔐 ========== ADMIN USER SEEDING FAILED ==========\n');
  } finally {
    if (client) {
      client.release();
      console.log('✅ Database client released');
    }
  }
}

export function generateId() {
  return crypto.randomUUID();
}

export async function query(text, params) {
  if (!dbConnected || !pool) {
    console.warn('⚠️  Database not connected - attempting to reconnect...');
    await testDatabaseConnection(2);
    if (!dbConnected || !pool) {
      throw new Error('Database not connected');
    }
  }
  
  const maxRetries = 3;
  for (let attempt = 1; attempt <= maxRetries; attempt++) {
    try {
      return await pool.query(text, params);
    } catch (error) {
      // Retry on transient errors
      if (error.code === 'ECONNREFUSED' || 
          error.code === 'ETIMEDOUT' || 
          error.code === 'EHOSTUNREACH' ||
          error.message?.includes('idle')) {
        
        console.warn(`⚠️  Query attempt ${attempt}/${maxRetries} failed (transient error): ${error.message}`);
        
        if (attempt < maxRetries) {
          const delayMs = 500 * attempt;
          console.log(`⏳ Retrying query in ${delayMs}ms...`);
          await new Promise(resolve => setTimeout(resolve, delayMs));
          continue;
        }
      }
      
      // For non-transient errors or final retry, throw immediately
      throw error;
    }
  }
}

export async function getClient() {
  if (!dbConnected || !pool) {
    throw new Error('Database not connected');
  }
  return pool.connect();
}

export function isConnected() {
  return dbConnected && pool !== null;
}

export default pool;
