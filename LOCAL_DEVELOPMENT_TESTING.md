# Local Development Testing Guide - SOVS

## Quick Start for Local Testing

This guide helps you test the complete SOVS system locally with email verification.

---

## Prerequisites

1. **Node.js** 24.x installed
2. **PostgreSQL** 15+ running locally OR
3. **Render PostgreSQL** accessible with proper network setup
4. **Gmail Account** with:
   - 2-Factor Authentication enabled
   - App Password generated (16 characters)

---

## Setup Steps

### Step 1: Get Gmail App Password

1. Go to myaccount.google.com → Security
2. Enable 2-Step Verification (if not already enabled)
3. Go to App passwords → Select "Mail" → "Windows Computer"
4. Copy the 16-character password

### Step 2: Update `.env` File

Edit `backend-node/.env`:

```env
# Keep existing settings, but update email:
SMTP_SENDER_EMAIL=your-email@gmail.com
SMTP_APP_PASSWORD=xxxx-xxxx-xxxx-xxxx

# If using local PostgreSQL:
DATABASE_URL=postgresql://localhost/sovs_dev

# If using Render PostgreSQL (from Render dashboard):
DATABASE_URL=postgresql://user:password@dpg-xxxx.render-external.app/sovs_db
```

### Step 3: Start Backend Server

```bash
cd backend-node
npm start

# Expected output:
# ✅ Connected to database
# 📧 Email service configured
# 🚀 Server running on port 8080
```

---

## Testing Flows

### Test 1: Registration with Email Verification

**1.1 Send Registration Code**
```powershell
$body = @{email="test123@example.com"} | ConvertTo-Json
$response = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/send-registration-code" `
  -Method Post -Body $body -ContentType "application/json"
Write-Host $response.Content
```

Expected response:
```json
{
  "message": "Verification code sent to email",
  "email": "test123@example.com"
}
```

**1.2 Check Email & Verify Code**
- Open your email inbox
- Find email from "sovs.ac.ke@gmail.com" with subject "SOVS - Email Verification Code"
- Extract the 6-digit code

```powershell
$body = @{email="test123@example.com"; code="123456"} | ConvertTo-Json
$response = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/verify-registration-code" `
  -Method Post -Body $body -ContentType "application/json"
Write-Host $response.Content
```

Expected response:
```json
{
  "message": "Email verified successfully",
  "email": "test123@example.com"
}
```

**1.3 Complete Registration**
```powershell
$body = @{
  username="student001"
  password="SecurePassword123!"
  fullName="Test User"
  email="test123@example.com"
  regNo="REG2024001"
  faculty="School of Engineering"
  course="Software Engineering"
  phoneNumber="+254712345678"
} | ConvertTo-Json

$response = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/register" `
  -Method Post -Body $body -ContentType "application/json"
Write-Host $response.Content
```

Expected response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "user": {
    "userId": "550e8400-e29b-41d4-a716-446655440000",
    "username": "student001",
    "email": "test123@example.com",
    "isVerified": true,
    ...
  }
}
```

✅ **Registration successful!**

---

### Test 2: Login with Email 2FA

**2.1 Submit Login Credentials**
```powershell
$body = @{username="student001"; password="SecurePassword123!"} | ConvertTo-Json
$response = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/login" `
  -Method Post -Body $body -ContentType "application/json"
Write-Host $response.Content
```

Expected response:
```json
{
  "message": "Login credentials verified. Verification code sent to email.",
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "email": "test123@example.com",
  "user": {...}
}
```

✉️ Check email for verification code

**2.2 Verify Login Code**
```powershell
$body = @{email="test123@example.com"; code="654321"} | ConvertTo-Json
$response = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/verify-login-code" `
  -Method Post -Body $body -ContentType "application/json"
Write-Host $response.Content
```

Expected response:
```json
{
  "message": "Login successful",
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "user": {...}
}
```

🔒 **Login successful!** Token can be used for authenticated API calls

---

### Test 3: Password Reset with Email

**3.1 Request Password Reset**
```powershell
$body = @{email="test123@example.com"} | ConvertTo-Json
$response = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/send-password-reset-code" `
  -Method Post -Body $body -ContentType "application/json"
Write-Host $response.Content
```

**3.2 Verify Reset Code**
```powershell
$body = @{email="test123@example.com"; code="reset-code-from-email"} | ConvertTo-Json
$response = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/verify-password-reset-code" `
  -Method Post -Body $body -ContentType "application/json"
Write-Host $response.Content
```

**3.3 Reset Password**
```powershell
$body = @{
  email="test123@example.com"
  code="reset-code-from-email"
  newPassword="NewSecurePassword123!"
  confirmPassword="NewSecurePassword123!"
} | ConvertTo-Json

$response = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/reset-password" `
  -Method Post -Body $body -ContentType "application/json"
Write-Host $response.Content
```

✅ **Password reset successful!**

---

## Troubleshooting

### Issue: "Failed to send verification code"

**Possible Causes:**
1. Gmail credentials incorrect
2. Gmail 2FA not enabled
3. App password not generated correctly
4. Network connectivity issue

**Solution:**
```bash
# Check backend logs for detailed error:
npm start
# Look for error message mentioning SMTP or Gmail

# Verify .env file has correct values:
Cat backend-node\.env | Select-String -Pattern 'SMTP'
```

### Issue: "Database not connected"

**If using Render PostgreSQL:**
- Cannot connect from local machine (network restriction)
- Must deploy backend to Render or enable database public access

**Solution: Use local PostgreSQL for development**
```bash
# Install PostgreSQL locally
# Then update .env:
DATABASE_URL=postgresql://postgres:password@localhost:5432/sovs_dev

# The backend will auto-create tables on first run
```

### Issue: "Email delay" or "Email not received"

**Possible Causes:**
- Gmail rate limiting (5+ requests per minute)
- Email marked as spam
- Gmail blocking "Less secure apps"

**Solution:**
- Wait 5 minutes between tests
- Check spam folder
- Enable Gmail app password access

---

## Database Options

### Option 1: Local PostgreSQL (Recommended for Development)

```bash
# Install PostgreSQL (Windows)
# From https://www.postgresql.org/download/windows/

# Create local database
psql -U postgres
> CREATE DATABASE sovs_dev;
> \q

# Update .env
DATABASE_URL=postgresql://postgres:password@localhost:5432/sovs_dev

# Start backend
npm start
```

### Option 2: Render PostgreSQL (For Testing Deployed Version)

```bash
# Use connection string from Render dashboard
DATABASE_URL=postgresql://user:password@dpg-xxxx.render-external.app/sovs_db

# Must have network access or deploy backend to Render
```

---

## Complete Test Scenario

### Scenario: Fresh User Lifecycle

1. **New user registers** → Receives email code → Verifies email → Creates account
2. **User logs in** → Receives email code → Verifies code → Gets JWT token
3. **User accesses protected endpoints** → Uses JWT token in Authorization header
4. **User forgets password** → Receives reset code → Resets password → Logs in with new password

### Running Complete Test

```bash
# Terminal 1: Start backend
cd backend-node
npm start

# Terminal 2: Run tests
# Test 1: Registration
powershell -Command "... (see registration test above)"

# Test 2: Login
powershell -Command "... (see login test above)"

# Test 3: Password Reset
powershell -Command "... (see password reset test above)"
```

---

## Email Verification Details

### Code Properties
- **Format**: 6-digit random number (000000-999999)
- **Expiration**: 10 minutes
- **Cleanup**: Automatic every 5 minutes
- **Storage**: In-memory (codes lost on server restart)
- **Resend**: Safe to send new code, old code auto-expires

### Email Template Features
- Professional HTML layout
- Security warnings
- Code validity duration
- Support contact info
- Branding (SOVS Team)

### Rate Limiting
- **Auth endpoints**: 5 requests per 15 minutes per IP
- **Code sending**: 1 request per user email per minute
- **Code verification**: Unlimited attempts (code length makes brute-force impractical)

---

## Production Deployment Checklist

- ✅ Backend fixes applied (security, error handling)
- ✅ Email verification working (tested locally)
- ✅ Database connectivity verified (Render PostgreSQL)
- ✅ Environment variables set on Render dashboard
- ✅ JWT secrets are secure and unique
- ✅ CORS configured for frontend domains
- ✅ Rate limiting enabled
- ✅ Helmet security headers enabled
- ✅ Logs reviewed for errors
- ✅ Android app backend URL correct (https://sovs-backend.onrender.com/)

---

## Next Steps

1. **Test locally** using this guide
2. **Deploy backend** to Render
3. **Configure Android app** with Render URL
4. **Test from Android device** or emulator
5. **Monitor logs** for production issues

---

*Updated: March 27, 2026*
