import pkg from 'pg';
const { Pool } = pkg;
import dotenv from 'dotenv';
import crypto from 'crypto';
import bcryptjs from 'bcryptjs';
import { v4 as uuidv4 } from 'uuid';

dotenv.config();

let pool = null;
let dbConnected = false;

function quoteIdent(identifier) {
  return `"${String(identifier).replace(/"/g, '""')}"`;
}

function parseDatabaseUrl(databaseUrl = process.env.DATABASE_URL || '') {
  const normalized = String(databaseUrl || '').trim();
  if (!normalized) {
    return null;
  }

  try {
    return new URL(normalized);
  } catch (error) {
    return null;
  }
}

function resolveSslConfig(databaseUrl = process.env.DATABASE_URL || '') {
  const explicitMode = String(process.env.DATABASE_SSL || process.env.PGSSLMODE || '').trim().toLowerCase();
  if (['false', '0', 'off', 'disable', 'disabled'].includes(explicitMode)) {
    return false;
  }

  const parsed = parseDatabaseUrl(databaseUrl);
  const hostname = parsed?.hostname?.toLowerCase() || '';
  const sslMode = (parsed?.searchParams?.get('sslmode') || '').trim().toLowerCase();

  if (sslMode === 'disable') {
    return false;
  }

  if (['localhost', '127.0.0.1', '::1'].includes(hostname) || hostname.endsWith('.internal')) {
    return false;
  }

  return { rejectUnauthorized: false };
}

function getDatabaseHostLabel(databaseUrl = process.env.DATABASE_URL || '') {
  return parseDatabaseUrl(databaseUrl)?.hostname || 'unknown-host';
}

async function tableExists(client, tableName) {
  const result = await client.query(
    `SELECT EXISTS (
       SELECT 1
       FROM information_schema.tables
       WHERE table_schema = current_schema()
         AND LOWER(table_name) = LOWER($1)
     ) AS exists`,
    [tableName]
  );

  return Boolean(result.rows[0]?.exists);
}

async function findColumnName(client, tableName, columnName) {
  const result = await client.query(
    `SELECT column_name
     FROM information_schema.columns
     WHERE table_schema = current_schema()
       AND LOWER(table_name) = LOWER($1)
       AND LOWER(column_name) = LOWER($2)
     LIMIT 1`,
    [tableName, columnName]
  );

  return result.rows[0]?.column_name || null;
}

async function renameColumnIfNeeded(client, tableName, legacyColumnName, nextColumnName) {
  if (!await tableExists(client, tableName)) {
    return false;
  }

  const currentColumnName = await findColumnName(client, tableName, legacyColumnName);
  if (!currentColumnName) {
    return false;
  }

  if (await findColumnName(client, tableName, nextColumnName)) {
    return false;
  }

  console.log(`Migrating ${tableName}.${currentColumnName} -> ${nextColumnName}`);
  await client.query(
    `ALTER TABLE ${quoteIdent(tableName)} RENAME COLUMN ${quoteIdent(currentColumnName)} TO ${quoteIdent(nextColumnName)}`
  );
  return true;
}

async function addColumnIfMissing(client, tableName, columnName, definition) {
  if (!await tableExists(client, tableName)) {
    return false;
  }

  if (await findColumnName(client, tableName, columnName)) {
    return false;
  }

  console.log(`Adding column ${tableName}.${columnName}`);
  await client.query(
    `ALTER TABLE ${quoteIdent(tableName)} ADD COLUMN ${quoteIdent(columnName)} ${definition}`
  );
  return true;
}

async function setColumnTypeIfExists(client, tableName, columnName, nextType) {
  if (!await tableExists(client, tableName)) {
    return false;
  }

  const actualColumnName = await findColumnName(client, tableName, columnName);
  if (!actualColumnName) {
    return false;
  }

  await client.query(
    `ALTER TABLE ${quoteIdent(tableName)} ALTER COLUMN ${quoteIdent(actualColumnName)} TYPE ${nextType}`
  );
  return true;
}

async function ensureBigIntColumn(client, tableName, columnName, fallbackColumnName = null) {
  if (!await tableExists(client, tableName)) {
    return;
  }

  await addColumnIfMissing(client, tableName, columnName, 'BIGINT');

  const actualColumnName = await findColumnName(client, tableName, columnName);
  const actualFallbackColumnName = fallbackColumnName
    ? await findColumnName(client, tableName, fallbackColumnName)
    : null;
  const coalesceParts = [quoteIdent(actualColumnName)];

  if (actualFallbackColumnName) {
    coalesceParts.push(quoteIdent(actualFallbackColumnName));
  }

  coalesceParts.push(String(Date.now()));

  await client.query(
    `UPDATE ${quoteIdent(tableName)}
     SET ${quoteIdent(actualColumnName)} = COALESCE(${coalesceParts.join(', ')})
     WHERE ${quoteIdent(actualColumnName)} IS NULL`
  );
  await client.query(
    `ALTER TABLE ${quoteIdent(tableName)} ALTER COLUMN ${quoteIdent(actualColumnName)} SET NOT NULL`
  );
}

async function ensureBooleanColumn(client, tableName, columnName, defaultValue = false) {
  if (!await tableExists(client, tableName)) {
    return;
  }

  await addColumnIfMissing(client, tableName, columnName, 'BOOLEAN');

  const actualColumnName = await findColumnName(client, tableName, columnName);
  const sqlBoolean = defaultValue ? 'TRUE' : 'FALSE';

  await client.query(
    `UPDATE ${quoteIdent(tableName)}
     SET ${quoteIdent(actualColumnName)} = ${sqlBoolean}
     WHERE ${quoteIdent(actualColumnName)} IS NULL`
  );
  await client.query(
    `ALTER TABLE ${quoteIdent(tableName)} ALTER COLUMN ${quoteIdent(actualColumnName)} SET DEFAULT ${sqlBoolean}`
  );
  await client.query(
    `ALTER TABLE ${quoteIdent(tableName)} ALTER COLUMN ${quoteIdent(actualColumnName)} SET NOT NULL`
  );
}

async function migrateLegacySchemaNames(client) {
  const renameOperations = [
    ['users', 'userId', 'user_id'],
    ['users', 'passwordHash', 'password_hash'],
    ['users', 'fullName', 'full_name'],
    ['users', 'phoneNumber', 'phone_number'],
    ['users', 'regNo', 'reg_no'],
    ['users', 'subjectCombination', 'subject_combination'],
    ['users', 'levelOfStudy', 'level_of_study'],
    ['users', 'yearOfStudy', 'year_of_study'],
    ['users', 'profilePhotoPath', 'profile_photo_path'],
    ['users', 'isVerified', 'is_verified'],
    ['users', 'isLocked', 'is_locked'],
    ['users', 'createdAt', 'created_at'],
    ['users', 'loginAttempts', 'login_attempts'],
    ['positions', 'positionId', 'position_id'],
    ['positions', 'facultyId', 'faculty_id'],
    ['positions', 'courseId', 'course_id'],
    ['positions', 'subjectCombinationId', 'subject_combination_id'],
    ['positions', 'yearOfStudy', 'year_of_study'],
    ['elections', 'electionId', 'election_id'],
    ['elections', 'positionId', 'position_id'],
    ['elections', 'startDate', 'start_date'],
    ['elections', 'endDate', 'end_date'],
    ['candidates', 'candidateId', 'candidate_id'],
    ['candidates', 'positionId', 'position_id'],
    ['candidates', 'userId', 'user_id'],
    ['votes', 'voteId', 'vote_id'],
    ['votes', 'electionId', 'election_id'],
    ['votes', 'positionId', 'position_id'],
    ['votes', 'voterId', 'voter_id'],
    ['votes', 'candidateId', 'candidate_id'],
    ['votes', 'timestamp', 'created_at'],
    ['votes', 'hash', 'vote_hash'],
    ['faculties', 'facultyId', 'faculty_id'],
    ['courses', 'courseId', 'course_id'],
    ['courses', 'facultyId', 'faculty_id'],
    ['subject_combinations', 'courseId', 'course_id'],
    ['password_reset_codes', 'phoneNumber', 'phone_number'],
    ['password_reset_codes', 'createdAt', 'created_at'],
    ['password_reset_codes', 'expiresAt', 'expires_at'],
    ['password_reset_codes', 'codeHash', 'code_hash'],
    ['password_reset_codes', 'isUsed', 'is_used'],
    ['registration_notices', 'updatedAt', 'updated_at']
  ];

  for (const [tableName, legacyColumnName, nextColumnName] of renameOperations) {
    await renameColumnIfNeeded(client, tableName, legacyColumnName, nextColumnName);
  }
}

async function createSchema(client) {
  await client.query(`
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
      profile_photo_path TEXT,
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

    CREATE TABLE IF NOT EXISTS registration_notices (
      audience VARCHAR(50) PRIMARY KEY,
      deadline BIGINT,
      updated_at BIGINT NOT NULL
    );

    CREATE TABLE IF NOT EXISTS admin_logs (
      log_id VARCHAR(36) PRIMARY KEY,
      admin_id VARCHAR(36) NOT NULL REFERENCES users(user_id),
      action VARCHAR(255) NOT NULL,
      details TEXT,
      created_at BIGINT NOT NULL
    );
  `);
}

async function finalizeSchema(client) {
  await ensureBigIntColumn(client, 'users', 'updated_at', 'created_at');
  await ensureBigIntColumn(client, 'positions', 'created_at');
  await ensureBigIntColumn(client, 'elections', 'created_at');
  await ensureBigIntColumn(client, 'elections', 'updated_at', 'created_at');
  await ensureBigIntColumn(client, 'candidates', 'created_at');
  await ensureBigIntColumn(client, 'votes', 'created_at');
  await ensureBigIntColumn(client, 'faculties', 'created_at');
  await ensureBigIntColumn(client, 'courses', 'created_at');
  await ensureBigIntColumn(client, 'subject_combinations', 'created_at');
  await ensureBigIntColumn(client, 'password_reset_codes', 'created_at');
  await ensureBigIntColumn(client, 'password_reset_codes', 'expires_at', 'created_at');
  await ensureBigIntColumn(client, 'registration_notices', 'updated_at');
  await ensureBigIntColumn(client, 'admin_logs', 'created_at');

  await ensureBooleanColumn(client, 'users', 'is_verified', false);
  await ensureBooleanColumn(client, 'users', 'is_locked', false);
  await ensureBooleanColumn(client, 'candidates', 'is_verified', false);

  await setColumnTypeIfExists(client, 'users', 'profile_photo_path', 'TEXT');

  await client.query(`
    CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);
    CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);
    CREATE INDEX IF NOT EXISTS idx_users_lower_email ON users(LOWER(email));
    CREATE INDEX IF NOT EXISTS idx_votes_election_voter ON votes(election_id, voter_id);
    CREATE INDEX IF NOT EXISTS idx_candidates_position ON candidates(position_id);
    CREATE INDEX IF NOT EXISTS idx_elections_position ON elections(position_id);
    CREATE INDEX IF NOT EXISTS idx_verification_codes_email_type ON verification_codes(email, type);
    CREATE INDEX IF NOT EXISTS idx_verification_codes_lower_email_type ON verification_codes(LOWER(email), type);
    CREATE INDEX IF NOT EXISTS idx_verification_codes_expires_at ON verification_codes(expires_at);
    CREATE INDEX IF NOT EXISTS idx_verified_registrations_email ON verified_registrations(email);
    CREATE INDEX IF NOT EXISTS idx_verified_registrations_lower_email ON verified_registrations(LOWER(email));
  `);
}

const initializePool = () => {
  try {
    const databaseUrl = process.env.DATABASE_URL;
    const sslConfig = resolveSslConfig(databaseUrl);
    console.log(`Database host: ${getDatabaseHostLabel(databaseUrl)} | SSL: ${sslConfig ? 'enabled' : 'disabled'}`);

    pool = new Pool({
      connectionString: databaseUrl,
      ssl: sslConfig,
      connectionTimeoutMillis: 5000,
      idleTimeoutMillis: 30000,
      max: 10
    });

    pool.on('error', (err) => {
      console.error('Unexpected error on idle client:', err.message);
      dbConnected = false;
      setTimeout(() => {
        console.log('Attempting to reconnect to database...');
        testDatabaseConnection();
      }, 5000);
    });

    pool.on('connect', () => {
      console.log('New client connected to pool');
    });

    return pool;
  } catch (error) {
    console.error('Failed to create connection pool:', error.message);
    dbConnected = false;
    return null;
  }
};

async function testDatabaseConnection(retries = 3) {
  for (let attempt = 1; attempt <= retries; attempt += 1) {
    try {
      if (!pool) {
        pool = initializePool();
      }

      const client = await pool.connect();
      await client.query('SELECT NOW()');
      client.release();

      dbConnected = true;
      console.log('Database connection test successful');
      return true;
    } catch (error) {
      console.warn(`Database connection attempt ${attempt}/${retries} failed: ${error.message}`);

      if (attempt < retries) {
        const delayMs = 1000 * attempt;
        console.log(`Retrying in ${delayMs}ms...`);
        await new Promise((resolve) => setTimeout(resolve, delayMs));
      }
    }
  }

  dbConnected = false;
  console.error('Failed to connect to database after all retries');
  return false;
}

export async function initializeDatabase() {
  if (!pool) {
    pool = initializePool();
  }

  if (!pool) {
    console.warn('Database pool not initialized - running in mock mode');
    console.warn('The app will start but use mock data for testing');
    dbConnected = false;
    return false;
  }

  const isConnected = await testDatabaseConnection(3);
  if (!isConnected) {
    console.warn('Could not establish database connection');
    return false;
  }

  try {
    const client = await pool.connect();
    try {
      await client.query('BEGIN');
      await migrateLegacySchemaNames(client);
      await createSchema(client);
      await finalizeSchema(client);
      await client.query('COMMIT');
    } catch (error) {
      await client.query('ROLLBACK');
      throw error;
    } finally {
      client.release();
    }

    console.log('Database tables initialized');
    return true;
  } catch (error) {
    console.error('Database initialization error:', error.message);
    dbConnected = false;
    return false;
  }
}

export async function seedDatabase() {
  if (!dbConnected || !pool) {
    console.warn('Database not connected - skipping seed');
    return;
  }

  try {
    const client = await pool.connect();

    const result = await client.query('SELECT COUNT(*) FROM users');
    if (result.rows[0].count > 0) {
      console.log('Database already seeded');
      client.release();
      await seedAdminUser();
      return;
    }

    const faculties = ['Engineering', 'Science', 'Medicine', 'Law', 'Business'];
    for (const faculty of faculties) {
      await client.query(
        'INSERT INTO faculties (faculty_id, name, created_at) VALUES ($1, $2, $3)',
        [generateId(), faculty, Date.now()]
      );
    }

    console.log('Database seeded successfully');
    client.release();

    await seedAdminUser();
  } catch (error) {
    console.warn('Database seeding error:', error.message);
  }
}

export async function seedAdminUser() {
  console.log('\n========== ADMIN USER SEEDING START ==========');

  if (!dbConnected || !pool) {
    console.error('Database not connected - cannot seed admin');
    console.log('dbConnected:', dbConnected);
    console.log('pool:', pool ? 'exists' : 'null');
    console.log('========== ADMIN USER SEEDING FAILED ==========\n');
    return;
  }

  let client;
  try {
    client = await pool.connect();
    console.log('Database client connected');

    const tableCheck = await client.query(`
      SELECT EXISTS (
        SELECT FROM information_schema.tables
        WHERE table_name = 'users'
      )
    `);

    if (!tableCheck.rows[0].exists) {
      console.error('Users table does not exist');
      console.log('========== ADMIN USER SEEDING FAILED ==========\n');
      return;
    }
    console.log('Users table exists');

    console.log('Checking for existing admin user...');
    const adminCheck = await client.query(
      'SELECT user_id, username, role FROM users WHERE username = $1',
      ['admin']
    );

    if (adminCheck.rows.length > 0) {
      const existing = adminCheck.rows[0];
      console.log('Admin user already exists');
      console.log(`User ID: ${existing.user_id}`);
      console.log(`Username: ${existing.username}`);
      console.log(`Role: ${existing.role}`);

      const correctEmail = 'sovs.ac.ke@gmail.com';
      await client.query(
        'UPDATE users SET email = $1, updated_at = $2 WHERE user_id = $3',
        [correctEmail, Date.now(), existing.user_id]
      );
      console.log(`Email updated to: ${correctEmail}`);
      console.log('========== ADMIN USER SEEDING COMPLETE ==========\n');
      return;
    }

    console.log('Admin user not found, creating new account...');
    const plainPassword = 'admin';
    console.log('Hashing password with bcryptjs (12 rounds)...');
    const passwordHash = await bcryptjs.hash(plainPassword, 12);
    console.log(`Hash created: ${passwordHash.substring(0, 20)}...`);

    const adminId = uuidv4();
    const adminEmail = 'sovs.ac.ke@gmail.com';
    const fullName = 'Administrator';

    console.log('Inserting admin user into database...');
    console.log(`User ID: ${adminId}`);
    console.log('Username: admin');
    console.log(`Full Name: ${fullName}`);
    console.log(`Email: ${adminEmail}`);
    console.log('Role: admin');

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
    console.log('\nAdmin user created successfully');
    console.log('ACCOUNT CREATED:');
    console.log(`User ID: ${createdUser.user_id}`);
    console.log(`Username: ${createdUser.username}`);
    console.log(`Full Name: ${createdUser.full_name}`);
    console.log(`Email: ${createdUser.email}`);
    console.log(`Role: ${createdUser.role}`);
    console.log('Password: admin (bcrypt hashed - 12 rounds)');
    console.log(`Hash: ${createdUser.password_hash.substring(0, 20)}...`);
    console.log('========== ADMIN USER SEEDING COMPLETE ==========\n');
  } catch (error) {
    console.error('Admin seeding error:');
    console.error(`Message: ${error.message}`);
    console.error(`Code: ${error.code}`);
    console.error(`Detail: ${error.detail}`);
    console.log('Stack:', error.stack);
    console.log('========== ADMIN USER SEEDING FAILED ==========\n');
  } finally {
    if (client) {
      client.release();
      console.log('Database client released');
    }
  }
}

export function generateId() {
  return crypto.randomUUID();
}

export async function query(text, params) {
  if (!dbConnected || !pool) {
    console.warn('Database not connected - attempting to reconnect...');
    await testDatabaseConnection(2);
    if (!dbConnected || !pool) {
      throw new Error('Database not connected');
    }
  }

  const maxRetries = 3;
  for (let attempt = 1; attempt <= maxRetries; attempt += 1) {
    try {
      return await pool.query(text, params);
    } catch (error) {
      if (
        error.code === 'ECONNREFUSED' ||
        error.code === 'ETIMEDOUT' ||
        error.code === 'EHOSTUNREACH' ||
        error.message?.includes('idle')
      ) {
        console.warn(`Query attempt ${attempt}/${maxRetries} failed (transient error): ${error.message}`);

        if (attempt < maxRetries) {
          const delayMs = 500 * attempt;
          console.log(`Retrying query in ${delayMs}ms...`);
          await new Promise((resolve) => setTimeout(resolve, delayMs));
          continue;
        }
      }

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
