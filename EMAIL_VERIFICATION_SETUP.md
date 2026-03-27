# Email Verification Setup Guide

## Overview
This guide explains how to configure and test email verification codes for registration and login in the SOVS system.

## System Components

### Backend Components
1. **Email Service** (`backend-node/src/utils/emailService.js`)
   - Sends verification code emails via Gmail SMTP
   - Supports three code types: registration, login, password_reset
   - HTML-formatted professional email templates

2. **Verification Code Manager** (`backend-node/src/utils/verificationCodeManager.js`)
   - Stores verification codes in-memory (with 10-minute expiration)
   - Uses email + type as key for code storage
   - Automatic cleanup of expired codes every 5 minutes

3. **API Routes** (`backend-node/src/routes/authRoutes.js`)
   - `/api/auth/send-registration-code` - Send code to email
   - `/api/auth/verify-registration-code` - Verify code
   - `/api/auth/login` - Authenticate and send login code
   - `/api/auth/verify-login-code` - Verify code for login
   - `/api/auth/send-password-reset-code` - Send reset code
   - `/api/auth/verify-password-reset-code` - Verify reset code
   - `/api/auth/reset-password` - Reset password with code

### Frontend Components
1. **Android Email Service** (`app/src/main/java/com/university/voting/services/EmailService.kt`)
   - Sends emails directly from Android device via Gmail SMTP
   - Only uses if SMTP is configured in build

2. **Verification Code Service** (`app/src/main/java/com/university/voting/services/VerificationCodeService.kt`)
   - Manages verification code sending and local storage
   - Fallback for when backend email fails
   - Shows helpful error messages for misconfiguration

3. **UI Activities**
   - `RegistrationActivity` - Initiates registration with email verification
   - `RegisterVerifyCodeActivity` - User enters received code
   - `LoginActivity` - Authenticates, receives code
   - `LoginVerifyCodeActivity` - User verifies login with code
   - `ForgotPasswordActivity` - Password reset flow

## Configuration Steps

### Step 1: Gmail App Password Setup

**For the Backend Server:**

1. Go to [Google Account Security](https://myaccount.google.com/security)
2. Enable "2-Step Verification" if not already enabled
3. Scroll to "App passwords" section
4. Select App: "Mail" and Device: "Windows Computer" (or other)
5. Google will generate a 16-character password with spaces
6. **Remove all spaces** from the password (e.g., `abcd efgh ijkl mnop` → `abcdefghijklmnop`)
7. Copy the password without spaces

**Result:** You'll have:
- Email: `your-email@gmail.com`
- App Password: `xxxxxxxxxxxx` (16 characters, no spaces)

### Step 2: Environment Variables

#### Backend (.env.sovs or Render Dashboard)
```bash
SMTP_SENDER_EMAIL=your-email@gmail.com
SMTP_APP_PASSWORD=xxxxxxxxxxxxxxxxxxxx  # 16 chars, no spaces
NODE_ENV=production
APP_ENV=production
```

#### Android App (local.properties)
```properties
SMTP_SENDER_EMAIL=your-email@gmail.com
SMTP_APP_PASSWORD=xxxxxxxxxxxxxxxxxxxx  # 16 chars, no spaces
```

### Step 3: Verify Configuration

#### Test Backend Email Service
```bash
# Within Node.js shell or test file:
import emailService from './src/utils/emailService.js';

const result = await emailService.sendVerificationCode(
  'test@example.com',
  '123456',
  'registration'
);
console.log(result ? 'Email sent successfully' : 'Email failed');
```

#### Expected Output
✅ Email sent successfully: 
```
✅ Verification email sent to test@example.com. MessageId: xxxxxxx@xxx
```

❌ Email failed:
```
❌ Email service not configured. Cannot send email.
```

## Usage Flow

### Registration Flow
```
1. User fills registration form
   ↓
2. User clicks "Register"
   ↓
3. App calls: POST /api/auth/send-registration-code
   - Sends: { email: "user@example.com" }
   - Backend generates 6-digit code
   - Backend stores code (expires in 10 minutes)
   - Backend sends HTML email with code
   ↓
4. User receives email with verification code
   ↓
5. User enters code in RegisterVerifyCodeActivity
   ↓
6. App calls: POST /api/auth/verify-registration-code
   - Sends: { email: "user@example.com", code: "123456" }
   - Backend verifies code matches and hasn't expired
   ↓
7. App calls: POST /api/auth/register
   - Sends: Full registration details
   - Backend creates user account
   - User is auto-logged in
   ↓
8. User redirected to dashboard
```

### Login Flow
```
1. User enters username & password
   ↓
2. User clicks "Login"
   ↓
3. App calls: POST /api/auth/login
   - Sends: { username: "john_doe", password: "password123" }
   - Backend validates credentials
   - Backend generates 6-digit code
   - Backend sends code to user's email
   - Backend returns userId and email
   ↓
4. App transitions to LoginVerifyCodeActivity
   ↓
5. User receives email with verification code
   ↓
6. User enters code in app
   ↓
7. App calls: POST /api/auth/verify-login-code
   - Sends: { email: "user@example.com", code: "123456" }
   - Backend verifies code and generates JWT
   ↓
8. User receives JWT token and is logged in
   ↓
9. User redirected to dashboard
```

### Password Reset Flow
```
1. User goes to Forgot Password
   ↓
2. User enters email address
   ↓
3. App calls: POST /api/auth/send-password-reset-code
   - Backend checks if email exists
   - Backend generates code
   - Backend sends code to email
   ↓
4. User receives email with reset code
   ↓
5. User enters code and new password
   ↓
6. App calls: POST /api/auth/verify-password-reset-code
   - Backend verifies code
   ↓
7. App calls: POST /api/auth/reset-password
   - Sends: { email, code, newPassword, confirmPassword }
   - Backend updates password
   ↓
8. User can login with new password
```

## API Endpoints Reference

### Send Registration Code
```http
POST /api/auth/send-registration-code
Content-Type: application/json

{
  "email": "student@university.edu"
}

Response (201):
{
  "message": "Verification code sent to email",
  "email": "student@university.edu"
}

Response (400):
{
  "error": "Email already registered"
}

Response (500):
{
  "error": "Failed to send verification code. Please check email configuration."
}
```

### Verify Registration Code
```http
POST /api/auth/verify-registration-code
Content-Type: application/json

{
  "email": "student@university.edu",
  "code": "123456"
}

Response (200):
{
  "message": "Email verified successfully",
  "email": "student@university.edu"
}

Response (400):
{
  "error": "Invalid or expired verification code"
}
```

### Login (Send Code)
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123"
}

Response (200):
{
  "message": "Login credentials verified. Verification code sent to email.",
  "userId": "uuid-here",
  "email": "student@university.edu",
  "verificationCode": "123456",  // Only in development
  "user": { ... }
}

Response (401):
{
  "error": "Invalid credentials"
}
```

### Verify Login Code
```http
POST /api/auth/verify-login-code
Content-Type: application/json

{
  "email": "student@university.edu",
  "code": "123456"
}

Response (200):
{
  "message": "Login successful",
  "token": "eyJhbGc...",
  "user": { ... }
}

Response (400):
{
  "error": "Invalid or expired verification code"
}
```

### Send Password Reset Code
```http
POST /api/auth/send-password-reset-code
Content-Type: application/json

{
  "email": "student@university.edu"
}

Response (200):
{
  "message": "Password reset code sent to email",
  "email": "student@university.edu"
}

Response (400):
{
  "error": "Email not found"
}
```

### Reset Password
```http
POST /api/auth/reset-password
Content-Type: application/json

{
  "email": "student@university.edu",
  "code": "123456",
  "newPassword": "newpassword123",
  "confirmPassword": "newpassword123"
}

Response (200):
{
  "message": "Password reset successfully"
}

Response (400):
{
  "error": "Invalid or expired verification code"
}
```

## Troubleshooting

### Issue: "Email service not configured"
**Solution:**
- Check SMTP_SENDER_EMAIL is set and not "placeholder"
- Check SMTP_APP_PASSWORD is set and not "placeholder"
- Verify no spaces in SMTP_APP_PASSWORD
- Rebuild Android app to reload BuildConfig values

### Issue: Email not arriving
**Solution:**
1. Check Gmail "Less secure apps" setting (if using regular Gmail password)
2. Verify app password is correct (no spaces)
3. Check email address is valid
4. Check spam/junk folder
5. Verify in development logs: `console.log()` shows email attempted

### Issue: "Code expired"
**Solution:**
- Code is valid for 10 minutes
- User must enter code quickly after receiving it
- Can request new code by resubmitting

### Issue: "Invalid code"
**Solution:**
- Check code matches exactly (letter-case sensitive)
- Verify correct email address was used
- Ensure code hasn't expired
- Confirm backend logs show code was generated

## Development vs Production

### Development Mode
- `APP_ENV=development`
- Login endpoint returns `verificationCode` in response
- Useful for testing without actually checking email
- Email still sent if SMTP configured

### Production Mode
- `APP_ENV=production`
- Login endpoint does NOT return code
- Code only sendable via email
- Emails must be properly configured

## Example Test Case

### Step 1: Register New User
```bash
curl -X POST http://localhost:3000/api/auth/send-registration-code \
  -H "Content-Type: application/json" \
  -d '{"email": "test@example.com"}'

# Response:
# {
#   "message": "Verification code sent to email",
#   "email": "test@example.com"
# }
```

### Step 2: Check Email (or dev logs)
User receives email with code, e.g., "123456"

### Step 3: Verify Code
```bash
curl -X POST http://localhost:3000/api/auth/verify-registration-code \
  -H "Content-Type: application/json" \
  -d '{"email": "test@example.com", "code": "123456"}'

# Response:
# {
#   "message": "Email verified successfully",
#   "email": "test@example.com"
# }
```

### Step 4: Register User
```bash
curl -X POST http://localhost:3000/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123",
    "fullName": "Test User",
    "email": "test@example.com",
    "phoneNumber": "254123456789",
    "role": "voter"
  }'

# Response:
# {
#   "token": "eyJhbGc...",
#   "user": { ... }
# }
```

## Gmail Security Notes

⚠️ **Important Security Considerations:**

1. **App Passwords vs Regular Passwords**
   - Never use your main Gmail password
   - Always use an app-specific password
   - App passwords are more secure and revokable

2. **Environment Variables**
   - Never commit real credentials to GitHub
   - Use .env files (git-ignored)
   - For Render, use dashboard environment variables

3. **Email Rate Limiting**
   - Gmail allows ~100 emails/minute per app
   - Should be sufficient for university voting system
   - If hitting limits, implement rate limiting in backend

4. **Code Security**
   - 6-digit codes are cryptographically random
   - Codes expire after 10 minutes
   - Invalid code attempts don't lock account
   - In production, code not returned in response

## Support & Documentation

- View email logs: Check Node.js console output
- Test email offline: Rebuild Android app with test data
- Debug codes: Check verificationCodeManager memory store
- API documentation: See full API docs in COMPLETE_SYSTEM_DOCUMENTATION.md
