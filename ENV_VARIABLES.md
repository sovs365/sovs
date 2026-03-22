# Environment Variables Guide

This document outlines all environment variables required for the SOVS Backend Node.js application for both development and production deployments.

## Required Variables

### Database Connection
```
DATABASE_URL=postgresql://postgres:YOUR_PASSWORD@db.ypvruuolmsqgihcgyuiu.supabase.co:5432/postgres
```
- **Description**: PostgreSQL connection string for Supabase database
- **Source**: Supabase Dashboard → Settings → Database → Direct connection string
- **Required**: YES
- **Environment**: Production, Development

### JWT Configuration
```
JWT_SECRET=gdfreyo01-voting-backend-2024-secure-key
JWT_ISSUER=university-voting-app
JWT_AUDIENCE=university-voting-users
JWT_EXPIRATION_MS=86400000
```
- **JWT_SECRET**: Secret key for signing JWT tokens
- **JWT_ISSUER**: Token issuer identifier
- **JWT_AUDIENCE**: Expected audience for tokens
- **JWT_EXPIRATION_MS**: Token expiration time in milliseconds (86400000 = 24 hours)
- **Required**: YES
- **Environment**: Production, Development

### Environment Settings
```
NODE_ENV=production
APP_ENV=production
PORT=3000
```
- **NODE_ENV**: Must be set to "production" for production builds
- **APP_ENV**: Application environment mode
- **PORT**: Server port (default: 8080 if not specified)
- **Required**: YES
- **Environment**: Production

### CORS Configuration (Optional)
```
CORS_ALLOWED_HOSTS=*
```
- **Description**: Comma-separated list of allowed origins for CORS
- **Default**: Allow all origins (*)
- **Example**: `https://sovs365.vercel.app,https://example.com`
- **Required**: NO
- **Environment**: Production

---

## Deployment to Vercel

### Step 1: Add Environment Variables
1. Go to: https://vercel.com/godfreyo01s-projects/sovs365/settings/environment-variables
2. Click "Add New" for each variable
3. Enter the variable name and value
4. Select environments: Production, Preview, Development
5. Click "Save"

### Variables to Add to Vercel:
| Variable | Value | Example |
|----------|-------|---------|
| DATABASE_URL | Your Supabase connection string | `postgresql://postgres:pwd@db.xxx.supabase.co:5432/postgres` |
| JWT_SECRET | Secret key for JWT | `gdfreyo01-voting-backend-2024-secure-key` |
| JWT_ISSUER | Token issuer | `university-voting-app` |
| JWT_AUDIENCE | Token audience | `university-voting-users` |
| JWT_EXPIRATION_MS | Expiration time in ms | `86400000` |
| NODE_ENV | Production mode | `production` |
| APP_ENV | App environment | `production` |

### Step 2: Redeploy
- Go to "Deployments"
- Click the three-dot menu on the latest deployment
- Click "Redeploy"

---

## Local Development

Create a `.env` file in the project root:

```
DATABASE_URL=postgresql://postgres:local_password@localhost:5432/votingdb
JWT_SECRET=dev-secret-key-change-in-production
JWT_ISSUER=university-voting-app
JWT_AUDIENCE=university-voting-users
JWT_EXPIRATION_MS=86400000
NODE_ENV=development
APP_ENV=development
PORT=8080
CORS_ALLOWED_HOSTS=*
```

**Note**: Never commit `.env` files to git. They are in `.gitignore`.

---

## Current Production Values (from .env.production)

```
JWT_SECRET=gdfreyo01-voting-backend-2024-secure-key
JWT_ISSUER=university-voting-app
JWT_AUDIENCE=university-voting-users
NODE_ENV=production
APP_ENV=production
```

**Alert**: Remember to add `DATABASE_URL` with your actual Supabase connection string when deploying!

---

## Supabase Connection String

Your Supabase project: **supabase-purple-house**
Project ID: **ypvruuolmsqgihcgyuiu**

To get the connection string:
1. Go to: https://app.supabase.com
2. Select your project
3. Click Settings → Database
4. Copy the **Direct connection string** under CONNECTION STRING
5. It will look like: `postgresql://postgres:[password]@db.ypvruuolmsqgihcgyuiu.supabase.co:5432/postgres`

---

## Security Best Practices

1. **Never commit secrets** - Keep `.env` and `.env.production` in `.gitignore`
2. **Use unique passwords** - Don't use the same password for multiple services
3. **Rotate secrets regularly** - Update JWT_SECRET periodically
4. **Environment-specific values** - Use different secrets for dev and production
5. **Secure storage** - Store real secrets in Vercel dashboard or secure password manager, not in code

---

## Troubleshooting

### Database Connection Errors
- Check `DATABASE_URL` format is correct
- Verify Supabase password is correct (check if expired)
- Ensure Supabase database is running

### JWT Token Issues
- Verify `JWT_SECRET` matches across all deployments
- Check `JWT_EXPIRATION_MS` is reasonable
- Ensure `JWT_ISSUER` and `JWT_AUDIENCE` are consistent

### Deployment Failures
- Confirm all required variables are set in Vercel
- Check that variables are set for the correct environment (Production)
- Try redeploying after adding variables
