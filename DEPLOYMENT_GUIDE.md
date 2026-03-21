# University Voting App - Node.js Backend Deployment Guide for Vercel

This document provides complete step-by-step instructions to deploy the Node.js/Express backend to Vercel with a PostgreSQL database.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Database Setup](#database-setup)
3. [Local Development](#local-development)
4. [Vercel Deployment](#vercel-deployment)
5. [Environment Configuration](#environment-configuration)
6. [API Endpoints](#api-endpoints)
7. [Troubleshooting](#troubleshooting)

---

## Prerequisites

Before deployment, ensure you have:

1. **Node.js & npm** (18.0.0 or higher)
   - Download from https://nodejs.org

2. **Git** (for Vercel deployment)
   - Download from https://git-scm.com

3. **Vercel Account** (free tier available)
   - Sign up at https://vercel.com

4. **PostgreSQL Database** (managed service recommended)
   - **Option 1: Vercel Postgres** (easiest, integrated with Vercel)
   - **Option 2: Supabase** (free tier, PostgreSQL hosting)
   - **Option 3: Railway** (simple PostgreSQL hosting)

---

## Database Setup

### Database Option 1: Vercel Postgres (Recommended)

1. **Access Vercel Dashboard**
   - Go to https://vercel.com/dashboard

2. **Create New PostgreSQL Database**
   - Click "Storage" tab
   - Click "Create Database"
   - Select "Postgres"
   - Choose region closest to your users
   - Accept terms and create

3. **Obtain Connection String**
   - Click on your database
   - Copy the "Postgres Connection String"
   - Save for later (looks like: `postgresql://user:password@host:5432/dbname`)

### Database Option 2: Supabase

1. **Create Supabase Account**
   - Go to https://supabase.com
   - Click "Start your project"

2. **Create New Project**
   - Project name: "voting-app"
   - Database password: (generate strong password)
   - Region: Choose closest to you
   - Click "Create new project"

3. **Get Connection String**
   - In project, go to Settings > Database
   - Copy the connection string
   - Replace `[YOUR-PASSWORD]` with your database password

### Database Option 3: Railway

1. **Create Railway Account**
   - Go to https://railway.app
   - Sign up and verify email

2. **Create PostgreSQL Database**
   - Click "New"
   - Select "Database"
   - Choose "PostgreSQL"
   - Click "Deploy"

3. **Get Connection URL**
   - Go to Connect tab
   - Copy "Database URL"

---

## Local Development

### 1. Setup Local Environment

```bash
# Navigate to backend directory
cd backend-node

# Install dependencies
npm install

# Create .env file (copy from .env.example)
cp .env.example .env
```

### 2. Configure .env for Local Development

Edit `.env` file with your database connection:

```env
# Application
APP_ENV=development
PORT=8080

# Database (use your local or cloud database)
DATABASE_URL=postgresql://user:password@localhost:5432/voting_app

# JWT (generate secure key: openssl rand -base64 64)
JWT_SECRET=dev-secret-key-change-in-production
JWT_ISSUER=university-voting-app
JWT_AUDIENCE=university-voting-users

# CORS
CORS_ALLOWED_HOSTS=http://localhost:3000,http://localhost:8080
```

### 3. Run Local Development Server

```bash
# Terminal 1: Start backend
npm run dev

# Expected output:
# Server running at http://0.0.0.0:8080
```

### 4. Test Local API

```bash
# Health check
curl http://localhost:8080/health

# Should respond: OK

# Try register endpoint
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "TestPass123",
    "fullName": "Test User",
    "phoneNumber": "2547XXXXXXXX",
    "role": "voter"
  }'
```

---

## Vercel Deployment

### Step 1: Prepare Repository

```bash
# Initialize git repository (if not done)
cd backend-node
git init

# Create .gitignore (already included)
# Add all files
git add .

# Initial commit
git commit -m "Initial backend setup for Vercel"
```

### Step 2: Push to GitHub

1. **Create GitHub Account** (if you don't have one)
   - Go to https://github.com/signup

2. **Create New Repository**
   - Click "+" > "New repository"
   - Name: `university-voting-backend`
   - Visibility: Private (recommended)
   - Do NOT initialize README/gitignore/license
   - Click "Create repository"

3. **Push Code to GitHub**

```bash
# Add GitHub remote
git remote add origin https://github.com/YOUR_USERNAME/university-voting-backend.git

# Set main branch
git branch -M main

# Push code
git push -u origin main

# Enter GitHub credentials when prompted
```

### Step 3: Deploy to Vercel

1. **Connect to Vercel**
   - Go to https://vercel.com/dashboard
   - Click "Add New..." > "Project"
   - Click "Import Git Repository"
   - Select your GitHub repository
   - Click "Import"

2. **Configure Project Settings**
   - **Project Name:** university-voting-backend
   - **Framework Preset:** Other
   - **Root Directory:** ./
   - Click "Deploy"

3. **Add Environment Variables**
   - Go to "Settings" > "Environment Variables"
   - Add each variable from your `.env`:

```
APP_ENV = production
JWT_SECRET = [Generate with: openssl rand -base64 64]
JWT_ISSUER = university-voting-app
JWT_AUDIENCE = university-voting-users
DATABASE_URL = [Your PostgreSQL connection string]
CORS_ALLOWED_HOSTS = https://yourfrontend.com,https://yourbackend.vercel.app
```

### Step 4: Verify Deployment

```bash
# After deployment completes, test health endpoint
curl https://YOUR_VERCEL_URL.vercel.app/health

# Should respond: OK
```

---

## Environment Configuration

### Production Environment Variables

| Variable | Description | Example |
|----------|-------------|---------|
| `APP_ENV` | Environment mode | production |
| `PORT` | Server port (Vercel uses 3000) | 3000 |
| `DATABASE_URL` | PostgreSQL connection string | postgresql://... |
| `JWT_SECRET` | Secret key for JWT signing | (64 chars, base64) |
| `JWT_ISSUER` | JWT issuer claim | university-voting-app |
| `JWT_AUDIENCE` | JWT audience claim | university-voting-users |
| `JWT_EXPIRATION_MS` | Token expiration time | 86400000 (24h) |
| `CORS_ALLOWED_HOSTS` | Comma-separated frontend URLs | https://example.com |

### Generating JWT Secret

```bash
# For macOS/Linux:
openssl rand -base64 64

# For Windows (PowerShell):
[Convert]::ToBase64String([Security.Cryptography.RNGCryptoServiceProvider]::new().GetBytes(48))
```

---

## API Endpoints

### Authentication Endpoints

**Register User**
```bash
POST /api/auth/register
Content-Type: application/json

{
  "username": "student001",
  "password": "SecurePassword123",
  "fullName": "John Doe",
  "phoneNumber": "254712345678",
  "email": "john@example.com",
  "role": "voter",
  "regNo": "REG/001",
  "faculty": "Engineering",
  "course": "Computer Science",
  "yearOfStudy": "3"
}
```

**Login**
```bash
POST /api/auth/login
Content-Type: application/json

{
  "username": "student001",
  "password": "SecurePassword123"
}
```

**Get Current User**
```bash
GET /api/auth/me
Authorization: Bearer {TOKEN}
```

### Elections Endpoints

**Get All Elections**
```bash
GET /api/elections
```

**Create Election** (Admin only)
```bash
POST /api/elections
Authorization: Bearer {ADMIN_TOKEN}
Content-Type: application/json

{
  "title": "2024 Student Leadership",
  "description": "Annual student leadership elections",
  "positionId": "pos-123",
  "startDate": 1704067200000,
  "endDate": 1704153600000
}
```

### Voting Endpoints

**Cast Vote**
```bash
POST /api/votes
Authorization: Bearer {TOKEN}
Content-Type: application/json

{
  "electionId": "elec-123",
  "votes": [
    {
      "positionId": "pos-123",
      "candidateId": "cand-456"
    }
  ]
}
```

**Get Results**
```bash
GET /api/votes/results/{electionId}
```

### Admin Endpoints

**Get All Users** (Admin only)
```bash
GET /api/admin/users
Authorization: Bearer {ADMIN_TOKEN}
```

**Get Reports** (Admin only)
```bash
GET /api/admin/reports
Authorization: Bearer {ADMIN_TOKEN}
```

---

## Troubleshooting

### Issue: Database Connection Error

**Error Message:** `connect ENOTFOUND host`

**Solution:**
1. Verify `DATABASE_URL` is correct in environment variables
2. Check your database is running and accepting connections
3. Ensure your IP is whitelisted (if database has firewall)
4. Test connection with `psql`:
   ```bash
   psql {DATABASE_URL}
   ```

### Issue: 503 Service Unavailable

**Error Message:** `Database connection failed`

**Solution:**
1. Check database status in Vercel/Supabase/Railway dashboard
2. Verify all environment variables are set correctly
3. Redeploy with `git push origin main`

### Issue: 401 Unauthorized

**Solution:**
1. Verify JWT token is included in `Authorization` header
2. Check token hasn't expired
3. Ensure JWT_SECRET is the same on backend

### Issue: CORS Errors

**Error Message:** `Access to XMLHttpRequest blocked by CORS policy`

**Solution:**
1. Add frontend URL to `CORS_ALLOWED_HOSTS` environment variable
2. Redeploy after updating environment
3. Use format: `https://yourdomain.com` (include https)

### Issue: Rate Limiting

**Error Message:** `Too many requests from this IP`

**Solution:**
1. Wait 15 minutes before retrying
2. This is intentional security measure
3. Adjust `rateLimit` in `src/index.js` if needed

---

## Auto-Deploy on Code Changes

Vercel automatically deploys when you push to GitHub:

```bash
# Make changes locally
# ...edit files...

# Commit and push
git add .
git commit -m "Description of changes"
git push origin main

# Vercel will automatically deploy!
```

---

## Monitoring and Logs

1. **Access Logs**
   - Go to Vercel Dashboard
   - Click your project
   - Go to "Deployments" tab
   - Click latest deployment
   - View logs in real-time

2. **Check Deployment Status**
   - Green check = Successful
   - Orange circle = Building/Deploying
   - Red X = Failed (check logs)

---

## Database Backups

### Vercel Postgres
- Automatic backups included
- Go to Storage > Your DB > Backups

### Supabase
- Go to Backups section
- Download or restore backups

### Railway
- Automatic daily backups
- Check database dashboard

---

## Next Steps

1. ✅ Backend deployed to Vercel
2. ⬜ Deploy frontend to Vercel/Netlify
3. ⬜ Connect frontend to backend API URL
4. ⬜ Test all functionality
5. ⬜ Set up domain (optional)
6. ⬜ Enable HTTPS (automatic with Vercel)

---

## Support & Resources

- **Vercel Docs:** https://vercel.com/docs
- **PostgreSQL Docs:** https://www.postgresql.org/docs/
- **Supabase Docs:** https://supabase.com/docs
- **Railway Docs:** https://docs.railway.app
- **Express.js Docs:** https://expressjs.com/

---

## Summary

Your Node.js backend is now deployed to Vercel with:
- ✅ Full API compatibility with Kotlin backend
- ✅ PostgreSQL database for production
- ✅ JWT authentication
- ✅ Rate limiting and security
- ✅ CORS support
- ✅ Health check endpoints
- ✅ Error handling
- ✅ Admin dashboard features

**The backend is production-ready and 100% compatible with your frontend!**
