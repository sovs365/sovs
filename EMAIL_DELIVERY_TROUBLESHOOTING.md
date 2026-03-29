# Email Delivery Troubleshooting Guide

## Current Status
- ✅ Admin user created and authenticated successfully
- ✅ Login response returns 200 OK
- ✅ Backend says "Verification code sent to email"
- ❌ Emails NOT arriving in inbox (sovs.ac.ke@gmail.com)

## Root Cause Analysis

### Verified - Email Service Works Locally
✅ Tested local email sending with test-email-direct.js
- Gmail SMTP credentials ARE valid
- Email service CAN send to Gmail account
- Credentials: sovs.ac.ke@gmail.com / app password (16 char)

### Issue - Environment Variables Not Set on Render
The problem is likely that the SMTP environment variables are NOT configured in Render's dashboard:
- ❌ SMTP_SENDER_EMAIL
- ❌ SMTP_APP_PASSWORD

These variables are:
✅ Configured locally in .env.sovs
❌ NOT configured in Render's environment variables

## Solution: Add Environment Variables to Render

### Step 1: Go to Render Dashboard
1. Visit https://dashboard.render.com
2. Log in with your Render account
3. Find the "sovs-backend" service
4. Click on the service to view details

### Step 2: Add Environment Variables
Go to **Settings** → **Environment** section and add:

| Variable | Value |
|----------|-------|
| `SMTP_SENDER_EMAIL` | `sovs.ac.ke@gmail.com` |
| `SMTP_APP_PASSWORD` | `jacfbuibmzdbpqbl` |
| `SMTP_HOST` | `smtp.gmail.com` |
| `SMTP_PORT` | `587` |
| `SMTP_SECURE` | `false` |
| `SMTP_TIMEOUT_MS` | `10000` |

Render should already have:
- DATABASE_URL
- JWT_SECRET
- JWT_ISSUER
- JWT_AUDIENCE
- JWT_EXPIRATION_MS
- CORS_ALLOWED_HOSTS
- BCRYPT_COST_FACTOR (should be `12`)

### Step 3: Deploy
After adding the variables, Render will automatically redeploy the backend.

### Step 4: Test Email
Once deployed, test the email with:
```
POST https://sovs-zeo8.onrender.com/api/auth/login
Body: {"username":"admin","password":"admin"}
```

You should:
1. Get a 200 response
2. Receive a verification code in your email (sovs.ac.ke@gmail.com inbox)
3. Check spam folder if not in inbox

## Diagnostic Steps

### Check Configuration (Once Deployed)
```
GET https://sovs-zeo8.onrender.com/api/auth/health-email
```

Response should show:
```json
{
  "diagnosis": {
    "smtpEmailConfigured": true,
    "smtpPasswordConfigured": true,
    "databaseConfigured": true,
    "smtpEmail": "sovs.***",
    "timestamp": "2026-03-29T..."
  }
}
```

### Check Deployment Version
```
GET https://sovs-zeo8.onrender.com/api/auth/version
```

Should show `hasVerificationCode: true` once new code is deployed.

## What We've Implemented

### 1. Verification Code Added to Login Response
The login endpoint now returns the generated code (temporary for debugging):
```json
{
  "message": "Login credentials verified. Verification code sent to email.",
  "userId": "70f8c4a3-c816-4af8-bad7-3bc0b34d225a",
  "email": "sovs.ac.ke@gmail.com",
  "verificationCode": "123456"  // <-- FOR DEBUGGING
}
```

### 2. Test Email Endpoint
New endpoint for testing email configuration:
```
POST /api/auth/test-email
Body: {"email":"test@example.com"}
```

### 3. Non-Blocking Email Sending
Email is sent asynchronously without blocking the login response, preventing timeout errors.

### 4. Improved Error Logging
Email service now logs detailed error information including SMTP response codes.

## Next Steps

1. **Add SMTP Environment Variables to Render** (CRITICAL)
   - Without these, email cannot be sent
   - This is the most likely cause of delivery failure

2. **Wait for Render Redeploy**
   - Should happen automatically after env vars are added
   - May take 2-5 minutes

3. **Test Email Delivery**
   - Use admin login to test
   - Verify code arrives in inbox

4. **Monitor for Issues**
   - Check Render logs for any errors
   - Use health-email endpoint to diagnose config

## Security Note
⚠️ The temporary `verificationCode` in the login response should be removed once email delivery is confirmed working. This is only for debugging purposes and exposes the verification code to the client.

Remove this line once email is working:
```javascript
// Remove before production
verificationCode: verificationCode
```
