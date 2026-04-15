import express from 'express';
import cors from 'cors';
import helmet from 'helmet';
import morgan from 'morgan';
import rateLimit from 'express-rate-limit';
import dotenv from 'dotenv';
import { initializeDatabase, seedDatabase, seedAdminUser } from './db.js';
import authRoutes from './routes/authRoutes.js';
import electionRoutes from './routes/electionRoutes.js';
import voteRoutes from './routes/voteRoutes.js';
import adminRoutes from './routes/adminRoutes.js';
import noticeRoutes from './routes/noticeRoutes.js';

dotenv.config();

const app = express();
const PORT = process.env.PORT || 8080;
const APP_ENV = process.env.APP_ENV || 'development';

// Trust proxy - required for rate limiting behind Render reverse proxy
app.set('trust proxy', 1);

// Middleware
app.use(helmet());
app.use(morgan('combined'));
app.use(cors({
  origin: process.env.CORS_ALLOWED_HOSTS?.split(',') || '*',
  credentials: true
}));

app.use(express.json({ limit: '8mb' }));
app.use(express.urlencoded({ extended: true, limit: '8mb' }));

// Rate limiting
const generalLimiter = rateLimit({
  windowMs: 15 * 60 * 1000, // 15 minutes
  max: 100, // limit each IP to 100 requests per windowMs
  message: 'Too many requests from this IP, please try again later.'
});

const authLimiter = rateLimit({
  windowMs: 15 * 60 * 1000,
  max: 5, // 5 requests for auth endpoints
  skipSuccessfulRequests: true,
  message: 'Too many login attempts, please try again later.'
});

app.use(generalLimiter);
app.use('/api/auth/', authLimiter);

// Routes
app.get('/', (req, res) => {
  res.json({
    message: 'University Voting App API v1.0',
    status: 'running',
    environment: APP_ENV
  });
});

// Health check endpoints
app.get('/health', (req, res) => {
  res.status(200).send('OK');
});

app.get('/health/ready', async (req, res) => {
  try {
    // Quick health check
    res.json({
      status: 'healthy',
      database: 'connected',
      version: '1.0.0'
    });
  } catch (error) {
    res.status(503).json({
      status: 'unhealthy',
      database: 'unreachable',
      error: error.message
    });
  }
});

// API Routes
app.use('/api/auth', authRoutes);
app.use('/api', electionRoutes);
app.use('/api', voteRoutes);
app.use('/api', adminRoutes);
app.use('/api', noticeRoutes);

// Error handling middleware
app.use((err, req, res, next) => {
  console.error('Error:', err);

  if (err?.type === 'entity.too.large') {
    return res.status(413).json({ error: 'Profile photo is too large. Please choose a smaller image.' });
  }
  
  if (err.name === 'UnauthorizedError') {
    return res.status(401).json({ error: 'Unauthorized' });
  }

  if (err.name === 'ValidationError') {
    return res.status(400).json({ error: err.message });
  }

  res.status(500).json({
    error: APP_ENV === 'development' ? err.message : 'Internal server error'
  });
});

// 404 handler
app.use((req, res) => {
  res.status(404).json({ error: 'Route not found' });
});

// Initialize database and start server
async function startServer() {
  try {
    console.log(`\n🚀 Starting SOVS Backend Server...`);
    console.log(`📋 Environment: ${APP_ENV}`);
    console.log(`🔌 Server Port: ${PORT}`);
    console.log(`━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n`);
    
    // Initialize database (non-blocking - server continues if DB fails)
    const dbHealthy = await initializeDatabase();
    
    if (APP_ENV === 'development') {
      try {
        await seedDatabase();
      } catch (error) {
        console.warn('⚠️  Could not seed database:', error.message);
      }
    }

    // Always seed admin user (idempotent - safe to call multiple times)
    try {
      await seedAdminUser();
    } catch (error) {
      console.warn('⚠️  Could not seed admin user:', error.message);
    }

    app.listen(PORT, '0.0.0.0', () => {
      console.log(`✅ Server is running at http://localhost:${PORT}`);
      console.log(`📱 Android Emulator: http://10.0.2.2:${PORT}`);
      console.log(`\n⚡ Ready to accept requests!\n`);
      
      if (!dbHealthy) {
        console.warn('⚠️  WARNING: Server running in MOCK MODE (database unavailable)');
        console.warn('   - API will accept requests but may not persist data');
        console.warn('   - Configure DATABASE_URL to use a real database\n');
      }
    });
  } catch (error) {
    console.error('❌ Critical error starting server:', error);
    process.exit(1);
  }
}

startServer();

export default app;
