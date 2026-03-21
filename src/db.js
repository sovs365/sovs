import pkg from 'pg';
const { Pool } = pkg;
import dotenv from 'dotenv';
import crypto from 'crypto';

dotenv.config();

const pool = new Pool({
  connectionString: process.env.DATABASE_URL,
  ssl: process.env.DATABASE_URL?.includes('localhost') 
    ? false 
    : { rejectUnauthorized: false }
});

// Initialize database schema
export async function initializeDatabase() {
  try {
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
    `);
    console.log('Database initialized successfully');
    return true;
  } catch (error) {
    if (!error.message.includes('already exists')) {
      console.error('Database initialization error:', error);
      return false;
    }
    return true;
  }
}

// Seed database with initial data (for development)
export async function seedDatabase() {
  try {
    const client = await pool.connect();
    
    // Check if data already exists
    const result = await client.query('SELECT COUNT(*) FROM users');
    if (result.rows[0].count > 0) {
      client.release();
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
    
    console.log('Database seeded successfully');
    client.release();
  } catch (error) {
    console.error('Database seeding error:', error);
  }
}

export function generateId() {
  return crypto.randomUUID();
}

export async function query(text, params) {
  return pool.query(text, params);
}

export async function getClient() {
  return pool.connect();
}

export default pool;
