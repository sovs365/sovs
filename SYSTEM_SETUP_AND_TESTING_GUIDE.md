# SOVS System Setup & Testing Guide

## Overview
This document provides comprehensive setup instructions and testing procedures for the complete SOVS (Student Online Voting System) with email verification for login and registration.

---

## System Architecture

```
Android App (Kotlin)
    ↓ HTTPS REST API
Node.js/Express Backend (Render.com)
    ↓ PostgreSQL Driver
PostgreSQL Database (Render.com)
```

### Technology Stack
- **Mobile**: Android Kotlin
- **Backend**: Node.js/Express
- **Database**: PostgreSQL (Render)
- **Auth**: JWT + BCrypt
- **Email Service**: Gmail SMTP
- **API**: REST with email verification (2FA)

---

## Part 1: Backend Setup & Configuration

### 1.1 Environment Configuration

The backend requires the following environment variables (in `.env`):

```env
# Database - Render PostgreSQL
DATABASE_URL=postgresql://user:password@dpg-xxx.render-external.app/database_name

# JWT Authentication
JWT_SECRET=your-secure-jwt-secret-key
JWT_ISSUER=university-voting-app
JWT_AUDIENCE=university-voting-users

# Environment
NODE_ENV=production
APP_ENV=production
PORT=3000

# Email Service (Gmail SMTP)
SMTP_SENDER_EMAIL=your-email@gmail.com
SMTP_APP_PASSWORD=your-16-char-gmail-app-password

# CORS
CORS_ALLOWED_HOSTS=*
```

### 1.2 Gmail Setup for Email Verification

To enable email verification codes to be sent via Gmail:

1. **Enable 2-Factor Authentication** on your Gmail account
   - Go to myaccount.google.com → Security
   - Enable 2-Step Verification

2. **Generate App Password**
   - Go to myaccount.google.com → Security → App passwords
   - Select "Mail" and "Windows Computer"
   - Copy the 16-character password

3. **Update `.env` file**
   ```
   SMTP_SENDER_EMAIL=your-email@gmail.com
   SMTP_APP_PASSWORD=xxxx-xxxx-xxxx-xxxx
   ```

### 1.3 PostgreSQL Database (Render)

The system uses Render's PostgreSQL service. The database includes:

**Tables:**
- `users` - User accounts with email verification status
- `faculties` - University faculties
- `courses` - University courses
- `positions` - Voting positions
- `elections` - Election events
- `candidates` - Election candidates
- `votes` - Cast votes (anonymous)
- `admin_logs` - Admin action audit trail

**Automatic Schema Creation:**
The backend automatically creates all tables on first run with proper indexes.

---

## Part 2: Authentication Flows

### 2.1 User Registration Flow with Email Verification

```
1. User submits registration form
   POST /api/auth/send-registration-code
   {email: "user@university.ac.ke"}
   ↓
   Backend generates 6-digit code → Stores in memory (10 min expiry) → Sends via email
   ✉️ User receives email with code

2. User enters verification code
   POST /api/auth/verify-registration-code
   {email: "...", code: "123456"}
   ✓ Code verified → Marked as verified → Can now complete registration

3. User submits complete registration
   POST /api/auth/register
   {
     username: "student123",
     password: "secure-password",
     email: "...",
     fullName: "John Doe",
     regNo: "REG123",
     faculty: "Engineering",
     course: "Software Engineering"
   }
   ✓ User created with JWT token
```

### 2.2 User Login Flow with Email 2FA

```
1. User submits username & password
   POST /api/auth/login
   {username: "student123", password: "secure-password"}
   ↓
   Backend validates credentials → Generates verification code → Sends via email
   ✉️ User receives email with code

2. User enters verification code
   POST /api/auth/verify-login-code
   {email: "...", code: "123456"}
   ✓ Code verified → JWT token generated & returned
   🔒 User now authenticated for API calls
```

### 2.3 Password Reset Flow

```
1. User requests password reset
   POST /api/auth/send-password-reset-code
   {email: "user@university.ac.ke"}
   ↓
   Backend generates code → Sends via email
   ✉️ User receives email with code

2. User verifies reset code
   POST /api/auth/verify-password-reset-code
   {email: "...", code: "123456"}
   ✓ Code verified

3. User resets password
   POST /api/auth/reset-password
   {email: "...", code: "123456", newPassword: "...", confirmPassword: "..."}
   ✓ Password updated successfully
```

---

## Part 3: Email Verification System

### 3.1 Code Generation & Storage

- **Code Format**: 6-digit random number
- **Expiration**: 10 minutes
- **Storage**: In-memory (with automatic cleanup every 5 minutes)
- **Email Template**: Professional HTML emails with security warnings

### 3.2 Email Types

1. **Registration Verification**
   - Subject: "SOVS - Email Verification Code"
   - Contains: 6-digit code + security notice
   - Validity: 10 minutes

2. **Login Verification**
   - Subject: "SOVS - Login Verification Code"
   - Contains: 6-digit code + security warning
   - Validity: 10 minutes

3. **Password Reset**
   - Subject: "SOVS - Password Reset Code"
   - Contains: 6-digit code + security notice
   - Validity: 10 minutes

### 3.3 Security Features

- ✅ Rate limiting: 5 attempts per 15 minutes
- ✅ JWT token expiration: 24 hours
- ✅ Password hashing: BCrypt (cost factor: 12)
- ✅ Email verification code encryption: Random 6-digit
- ✅ CORS protection: Restricted to allowed hosts
- ✅ Helmet security headers: XSS, CSRF protections

---

## Part 4: Testing the Complete System

### 4.1 Prerequisites

- Node.js 24.x installed
- Backend server running on port 8080 (local) or 3000 (production)
- Gmail account with app password configured
- PostgreSQL database accessible (Render)

### 4.2 Local Testing Setup

1. **Install Dependencies**
   ```bash
   cd backend-node
   npm install
   ```

2. **Configure `.env`** with your credentials:
   ```bash
   cp .env.sovs .env
   # Edit .env with:
   # - DATABASE_URL (Render PostgreSQL)
   # - SMTP_SENDER_EMAIL (Gmail)
   # - SMTP_APP_PASSWORD (Gmail app password)
   ```

3. **Start Backend Server**
   ```bash
   npm start
   # Server will start on port 8080
   ```

### 4.3 Test Registration Flow

**Step 1: Send Registration Code**
```bash
curl -X POST http://localhost:8080/api/auth/send-registration-code \
  -H "Content-Type: application/json" \
  -d '{"email":"your-test@example.com"}'

# Response:
# {
#   "message": "Verification code sent to email",
#   "email": "your-test@example.com"
# }
```

**Step 2: Verify Registration Code**
```bash
# Check email for the 6-digit code, then:
curl -X POST http://localhost:8080/api/auth/verify-registration-code \
  -H "Content-Type: application/json" \
  -d '{"email":"your-test@example.com","code":"123456"}'

# Response:
# {
#   "message": "Email verified successfully",
#   "email": "your-test@example.com"
# }
```

**Step 3: Complete Registration**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username":"testuser123",
    "password":"SecurePass123!",
    "fullName":"Test User",
    "email":"your-test@example.com",
    "regNo":"REG12345",
    "faculty":"Engineering",
    "course":"Software Engineering",
    "phoneNumber":"+254712345678"
  }'

# Response:
# {
#   "token": "eyJhbGciOiJIUzI1NiIs...",
#   "user": {
#     "userId": "...",
#     "username": "testuser123",
#     "email": "your-test@example.com",
#     "isVerified": true,
#     ...
#   }
# }
```

### 4.4 Test Login Flow

**Step 1: Submit Login Credentials**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser123","password":"SecurePass123!"}'

# Response:
# {
#   "message": "Login credentials verified. Verification code sent to email.",
#   "userId": "...",
#   "email": "your-test@example.com",
#   "user": {...}
# }
# ✉️ Check email for verification code
```

**Step 2: Verify Login Code**
```bash
# Check email for the 6-digit code, then:
curl -X POST http://localhost:8080/api/auth/verify-login-code \
  -H "Content-Type: application/json" \
  -d '{"email":"your-test@example.com","code":"123456"}'

# Response:
# {
#   "message": "Login successful",
#   "token": "eyJhbGciOiJIUzI1NiIs...",
#   "user": {...}
# }
# 🔒 User is now authenticated
```

### 4.5 Test Password Reset Flow

**Step 1: Request Password Reset**
```bash
curl -X POST http://localhost:8080/api/auth/send-password-reset-code \
  -H "Content-Type: application/json" \
  -d '{"email":"your-test@example.com"}'

# Response:
# {
#   "message": "Password reset code sent to email",
#   "email": "your-test@example.com"
# }
# ✉️ Check email for code
```

**Step 2: Verify Reset Code**
```bash
curl -X POST http://localhost:8080/api/auth/verify-password-reset-code \
  -H "Content-Type: application/json" \
  -d '{"email":"your-test@example.com","code":"123456"}'

# Response:
# {
#   "message": "Verification code is valid. You can now reset your password.",
#   "verified": true
# }
```

**Step 3: Reset Password**
```bash
curl -X POST http://localhost:8080/api/auth/reset-password \
  -H "Content-Type: application/json" \
  -d '{
    "email":"your-test@example.com",
    "code":"123456",
    "newPassword":"NewSecurePass123!",
    "confirmPassword":"NewSecurePass123!"
  }'

# Response:
# {
#   "message": "Password reset successfully"
# }
```

---

## Part 5: Android App Integration

### 5.1 Backend URL Configuration

The Android app is configured to use:
```
Base URL: https://sovs-backend.onrender.com/
```

**Location**: [RetrofitClient.kt](app/src/main/java/com/university/voting/api/RetrofitClient.kt)

### 5.2 Testing on Android Emulator

For local testing with Android emulator:
1. Use IP address `10.0.2.2:8080` to connect to localhost
2. Ensure backend is running on port 8080
3. Configure in RetrofitClient.kt if needed

### 5.3 Registration Flow on Android

1. Open Registration Activity
2. Enter user details
3. System sends verification code to email
4. User enters code in verification activity
5. Complete registration
6. JWT token received and stored locally
7. User can now login

### 5.4 Login Flow on Android

1. Open Login Activity
2. Enter username and password
3. System sends verification code to email
4. User enters code in verification activity
5. JWT token received
6. User authenticated for API calls

---

## Part 6: Deployment to Render

### 6.1 Database Configuration

1. Create PostgreSQL database on Render
2. Get connection string from Render dashboard
3. Update `DATABASE_URL` in environment variables

### 6.2 Environment Variables on Render

Set these in Render dashboard under "Environment":
- `DATABASE_URL` - PostgreSQL connection string
- `JWT_SECRET` - Secure random key (minimum 32 characters)
- `JWT_ISSUER` - university-voting-app
- `SMTP_SENDER_EMAIL` - Your Gmail address
- `SMTP_APP_PASSWORD` - Gmail app password (16 characters)
- `NODE_ENV` - production
- `PORT` - 3000 (or auto-assigned by Render)
- `CORS_ALLOWED_HOSTS` - Frontend domains

### 6.3 Deployment Steps

1. Push code to GitHub
2. Connect Render to your GitHub repository
3. Set environment variables on Render
4. Deploy service
5. Test endpoints on deployed URL

---

## Part 7: Troubleshooting

### Database Connection Issues

**Problem**: "Database not connected" errors

**Solutions**:
1. Verify `DATABASE_URL` is correct (check Render dashboard)
2. Ensure database is active on Render
3. For local development, confirm PostgreSQL server is running
4. Check network connectivity to database host
5. Verify username/password are correct

### Email Not Being Sent

**Problem**: "Failed to send verification code"

**Solutions**:
1. Verify Gmail 2-factor authentication is enabled
2. Check app password is correct (should be 16 characters)
3. Confirm `SMTP_SENDER_EMAIL` is correct
4. Check email inbox and spam folder
5. Review backend logs for SMTP errors

### JWT Token Issues

**Problem**: "Invalid token" or "Token expired"

**Solutions**:
1. Ensure `JWT_SECRET` is consistent across restarts
2. Check token expiration time (default: 24 hours)
3. Verify token format in Authorization header: `Bearer <token>`
4. Check system time is synchronized

---

## Part 8: Security Best Practices

### 8.1 Password Security
- Minimum 8 characters recommended
- Use mix of letters, numbers, special characters
- Passwords hashed with BCrypt (cost factor 12)
- Never stored in plain text

### 8.2 Email Verification
- Codes are 6-digit random numbers
- Code valid for 10 minutes only
- Code expires automatically
- Code cannot be reused after verification

### 8.3 API Security
- All endpoints use JWT authentication (except auth endpoints)
- Bearer token in Authorization header
- CORS enabled for allowed domains only
- Helmet.js security headers enabled
- Rate limiting: 5 requests per 15 minutes on auth endpoints

### 8.4 Production Deployment
- Always use HTTPS (Render provides SSL automatically)
- Never expose JWT_SECRET or database credentials
- Use environment variables for all secrets
- Enable database SSL connections (Render provides)
- Monitor logs for suspicious activity

---

## Quick Start Commands

```bash
# Install dependencies
cd backend-node
npm install

# Start local development server (port 8080)
npm start

# Run tests
npm test

# Build for production
npm run build

# Deploy to Render
git push origin main
# (Render will auto-deploy on push)
```

---

## Support & Troubleshooting

For issues, check:
1. Backend logs: `npm start` output
2. Email template: `backend-node/src/utils/emailService.js`
3. Authentication logic: `backend-node/src/routes/authRoutes.js`
4. Database schema: `backend-node/src/db.js`
5. Rate limiting: `backend-node/src/index.js`

---

## Version Information

- Backend: Node.js 24.x
- Database: PostgreSQL 15+
- Android: Kotlin, Android API 23-34
- Framework: Express.js 4.x
- ORM: Native PostgreSQL driver

---

*Last Updated: March 27, 2026*
*System: SOVS (Student Online Voting System)*
