# University Voting App - Node.js Backend

A production-ready Express.js REST API backend for the University Voting App, fully compatible with Vercel deployment.

## Features

- ✅ **Full API Compatibility** - 100% compatible with Kotlin backend
- ✅ **JWT Authentication** - Stateless, secure token-based auth
- ✅ **PostgreSQL Database** - Production-ready relational database
- ✅ **Role-Based Access** - Admin, Candidate, and Voter roles
- ✅ **Vercel Ready** - One-click deployment
- ✅ **Security** - Rate limiting, CORS, helmet, bcrypt hashing
- ✅ **Comprehensive API** - Elections, voting, candidates, reports

## Tech Stack

- **Runtime:** Node.js 18+
- **Framework:** Express.js 4.18.2
- **Database:** PostgreSQL
- **Authentication:** JWT (jsonwebtoken)
- **Encryption:** bcryptjs
- **Security:** helmet, express-rate-limit
- **Deployment:** Vercel

## Quick Start

### Installation

```bash
# Install dependencies
npm install

# Create environment file
cp .env.example .env

# Edit .env with your database details
```

### Development

```bash
# Start development server with auto-reload
npm run dev

# Server starts at http://localhost:8080
```

### Production

```bash
# Start production server
npm start
```

## Database Setup

### Local PostgreSQL

```bash
# Create database
createdb voting_app

# Connection string
DATABASE_URL=postgresql://user:password@localhost:5432/voting_app
```

### Cloud Providers

- **Vercel Postgres:** Built-in with Vercel (easiest)
- **Supabase:** https://supabase.com (free tier)
- **Railway:** https://railway.app (simple)

## Environment Variables

```env
# Application
APP_ENV=production
NODE_ENV=production
PORT=3000

# Database
DATABASE_URL=postgresql://user:password@host:5432/voting_app

# JWT Configuration
JWT_SECRET=generate-with-openssl-rand-base64-64
JWT_ISSUER=university-voting-app
JWT_AUDIENCE=university-voting-users
JWT_EXPIRATION_MS=86400000

# CORS
CORS_ALLOWED_HOSTS=https://yourfrontend.com
```

## API Documentation

### Authentication

```
POST /api/auth/register     - Register new user
POST /api/auth/login        - User login
GET  /api/auth/me          - Get current user (requires auth)
```

### Elections

```
GET  /api/elections         - List all elections
POST /api/elections         - Create election (admin only)
GET  /api/elections/:id     - Get election details
PATCH /api/elections/:id/status - Update election status (admin only)
```

### Voting

```
POST /api/votes             - Cast vote
GET  /api/votes/results/:electionId - Get election results
```

### Candidates

```
GET  /api/candidates        - List all candidates
POST /api/candidates        - Register as candidate
GET  /api/candidates/:id    - Get candidate details
```

### Admin

```
GET  /api/admin/users       - List all users (admin only)
GET  /api/admin/reports     - Get statistics (admin only)
POST /api/admin/faculties   - Create faculty (admin only)
POST /api/admin/courses     - Create course (admin only)
POST /api/admin/subject-combinations - Create subject combo (admin only)
```

### Health Check

```
GET /health                 - Liveness probe
GET /health/ready          - Readiness probe (DB check)
```

## Deployment to Vercel

### Step 1: Push to GitHub

```bash
git init
git add .
git commit -m "Initial commit"
git remote add origin https://github.com/YOUR_USERNAME/university-voting-backend.git
git push -u origin main
```

### Step 2: Deploy to Vercel

1. Go to https://vercel.com/dashboard
2. Click "Add New..." > "Project"
3. Import your GitHub repository
4. Configure environment variables
5. Click "Deploy"

### Step 3: Configure Vercel Postgres (Optional)

```bash
# Vercel CLI
npm install -g vercel
vercel postgres create
```

See [DEPLOYMENT_GUIDE.md](./DEPLOYMENT_GUIDE.md) for detailed instructions.

## Project Structure

```
backend-node/
├── src/
│   ├── index.js              # Main application entry
│   ├── db.js                 # Database configuration
│   ├── auth.js               # JWT & authentication
│   └── routes/
│       ├── authRoutes.js     # Auth endpoints
│       ├── electionRoutes.js # Election endpoints
│       ├── voteRoutes.js     # Voting endpoints
│       └── adminRoutes.js    # Admin endpoints
├── package.json              # Dependencies
├── vercel.json              # Vercel configuration
├── .env.example             # Environment template
├── .gitignore               # Git ignore rules
├── README.md                # This file
└── DEPLOYMENT_GUIDE.md      # Detailed deployment guide
```

## Database Schema

### Users Table
- user_id (UUID, primary key)
- username (string, unique)
- password_hash (string)
- full_name (string)
- role (enum: voter, candidate, admin)
- email, phone_number, faculty, course, etc.
- is_verified, is_locked (boolean)
- created_at, updated_at (timestamp)

### Elections Table
- election_id (UUID, primary key)
- position_id (FK)
- title, description (string)
- start_date, end_date (timestamp)
- status (enum: draft, open, closed)

### Candidates Table
- candidate_id (UUID, primary key)
- position_id, user_id (FK)
- manifesto (text)
- is_verified (boolean)

### Votes Table
- vote_id (UUID, primary key)
- election_id, voter_id, candidate_id (FK)
- vote_hash (string)
- created_at (timestamp)

### Positions, Faculties, Courses, SubjectCombinations
- Organizational hierarchy for university structure

## Security Features

- **Password Hashing:** bcryptjs with salt rounds 12
- **JWT Authentication:** HS256 algorithm with 24h expiration
- **Rate Limiting:** 5 attempts per 15 min for auth, 100 for general
- **CORS:** Configurable allowed origins
- **Helmet:** Security headers
- **Input Validation:** Required field checks
- **Unique Constraints:** Prevent duplicate entries

## Error Handling

All endpoints return consistent error responses:

```json
{
  "error": "Error message describing the issue"
}
```

HTTP Status Codes:
- `200` - Success
- `201` - Created
- `400` - Bad Request
- `401` - Unauthorized
- `403` - Forbidden
- `404` - Not Found
- `500` - Server Error
- `503` - Service Unavailable

## Performance

- Connection pooling with HikariCP equivalent (pg pool)
- Database indexes on frequently queried fields
- Efficient query design with JOINs
- Response compression enabled
- Rate limiting to prevent abuse

## Development Tips

### Testing Endpoints

```bash
# Register user
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "TestPass123",
    "fullName": "Test User",
    "phoneNumber": "2547XXXXXXXX",
    "role": "voter"
  }'

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "testuser", "password": "TestPass123"}'

# Get current user (replace TOKEN with actual token)
curl -X GET http://localhost:8080/api/auth/me \
  -H "Authorization: Bearer TOKEN"
```

### Debugging

```bash
# Enable verbose logging
DEBUG=* npm run dev

# Check database connection
psql $DATABASE_URL -c "SELECT 1;"
```

## Monitoring

- Health check endpoint: `/health/ready`
- Database connectivity validation
- Error logging with Morgan
- Vercel deployment logs

## License

Proprietary - University Voting App

## Support

For deployment issues, see [DEPLOYMENT_GUIDE.md](./DEPLOYMENT_GUIDE.md)

---

**Ready to deploy? Follow the [DEPLOYMENT_GUIDE.md](./DEPLOYMENT_GUIDE.md) for step-by-step instructions!**
