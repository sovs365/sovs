# Quick Start Guide - Deploying to Vercel in 15 Minutes

Follow this streamlined guide to deploy your Node.js backend to Vercel in 15 minutes.

## Prerequisites Check (2 min)

- ✅ Node.js 18+ installed: `node --version`
- ✅ Git installed: `git --version`
- ✅ Vercel account created: https://vercel.com (free)
- ✅ GitHub account created: https://github.com (free)
- ✅ PostgreSQL database ready (Vercel Postgres recommended)

## Setup Local Project (3 min)

```bash
cd backend-node

# Install dependencies
npm install

# Create environment file
cp .env.example .env
```

## Configure Database (2 min)

1. **Get Database Connection String:**
   - Vercel Postgres: https://vercel.com/dashboard → Storage → Create Database
   - Supabase: https://supabase.com → Create Project → Get Connection String
   - Railway: https://railway.app → New Project → PostgreSQL

2. **Update `.env` file:**
```bash
# Edit .env and add:
DATABASE_URL=postgresql://user:password@host:5432/voting_app
JWT_SECRET=your-super-secret-key-generate-with-openssl
JWT_ISSUER=university-voting-app
JWT_AUDIENCE=university-voting-users
APP_ENV=production
PORT=3000
CORS_ALLOWED_HOSTS=https://yourfrontend.com
```

3. **Generate Strong JWT Secret:**
```bash
# macOS/Linux:
openssl rand -base64 64

# Windows PowerShell:
[Convert]::ToBase64String([Security.Cryptography.RNGCryptoServiceProvider]::new().GetBytes(48))
```

## Push to GitHub (3 min)

```bash
# Initialize and commit code
git init
git add .
git commit -m "Initial backend for Vercel"
git branch -M main

# Add GitHub remote
git remote add origin https://github.com/YOUR_USERNAME/university-voting-backend.git

# Push to GitHub
git push -u origin main
```

## Deploy to Vercel (3 min)

1. Go to https://vercel.com/dashboard
2. Click **"Add New..."** → **"Project"**
3. Click **"Import Git Repository"**
4. Select your `university-voting-backend` repository
5. Click **"Import"**

## Configure Environment Variables (2 min)

1. In Vercel dashboard, go to **"Settings"** → **"Environment Variables"**
2. Add all variables from your `.env` file:

| Key | Value |
|-----|-------|
| `APP_ENV` | `production` |
| `DATABASE_URL` | Your PostgreSQL connection string |
| `JWT_SECRET` | Generated 64-char secret |
| `JWT_ISSUER` | `university-voting-app` |
| `JWT_AUDIENCE` | `university-voting-users` |
| `CORS_ALLOWED_HOSTS` | `https://yourfrontend.com` |

3. Click **"Save"**

## Deploy

1. Vercel automatically detects your code
2. Click **"Deploy"** button
3. Wait for deployment to complete (usually 1-2 minutes)
4. Get your deployment URL from the success page

## Verify Deployment (1 min)

```bash
# Test health endpoint
curl https://YOUR_VERCEL_URL.vercel.app/health

# Should respond: OK

# Test API
curl -X POST https://YOUR_VERCEL_URL.vercel.app/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "test",
    "password": "Test123",
    "fullName": "Test User",
    "phoneNumber": "254712345678",
    "role": "voter"
  }'
```

## Troubleshooting

### Website shows 502 Bad Gateway?
- Check Vercel logs: Dashboard → Deployments → Latest → Logs
- Verify DATABASE_URL is correct
- Ensure database is running

### Deployment fails?
- Check build logs: Dashboard → Deployments → Logs
- Verify all environment variables are set
- Try redeploying: Dashboard → Deployments → Redeploy

### Can't connect to database?
- Verify connection string is correct
- Check if database IP whitelisting is enabled
- Test locally first with same connection string

## Auto-Deploy

Every time you push to GitHub, Vercel automatically deploys:

```bash
# Make changes
# ... edit files ...

# Push to deploy
git add .
git commit -m "Your changes"
git push origin main

# Check deployment: https://vercel.com/dashboard
```

## Environment Check

You can verify environment variables are working:

```bash
curl https://YOUR_VERCEL_URL.vercel.app/ \
  -H "Authorization: Bearer YOUR_TOKEN"
```

## Next Steps

1. ✅ Backend deployed to Vercel
2. Update your frontend to use new backend URL
3. Test all API endpoints
4. Configure custom domain (optional)
5. Monitor logs and performance

## Backend API URL

Your backend API is now available at:
```
https://YOUR-VERCEL-URL.vercel.app
```

Use this URL in your frontend app:
```javascript
const API_URL = "https://YOUR-VERCEL-URL.vercel.app";

// Register
fetch(`${API_URL}/api/auth/register`, {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({...})
});
```

## Support

- **Common Issues:** See DEPLOYMENT_GUIDE.md
- **API Docs:** See README.md
- **Vercel Support:** https://vercel.com/support

---

**🎉 Your backend is now live on Vercel!**
