# Complete Vercel Deployment Guide for SOVS Backend

A step-by-step beginner's guide to deploy your Node.js backend to Vercel with Supabase PostgreSQL database.

---

## 📋 PART 1: Gather Your Information

Before starting, collect these details:

### Your GitHub Account
- **GitHub Organization**: `sovs365`
- **GitHub Repository**: `sovs365/sovs`
- **Repository URL**: `https://github.com/sovs365/sovs`

### Your Supabase Database
- **Project Name**: `supabase-purple-house`
- **Project ID**: `ypvruuolmsqgihcgyuiu`
- **Supabase Dashboard**: https://app.supabase.com

### Environment Variables (All Required - Copy Exactly)
```
DATABASE_URL=postgresql://postgres:YOUR_PASSWORD@db.ypvruuolmsqgihcgyuiu.supabase.co:5432/postgres
JWT_SECRET=gdfreyo01-voting-backend-2024-secure-key
JWT_ISSUER=university-voting-app
JWT_AUDIENCE=university-voting-users
JWT_EXPIRATION_MS=86400000
NODE_ENV=production
APP_ENV=production
PORT=3000
```

---

## 📚 PART 2: Get Your Supabase Connection String

**⚠️ IMPORTANT**: The connection string contains your database password. Keep it PRIVATE!

### Step 1: Open Supabase Dashboard
1. Go to: https://app.supabase.com
2. Login with your Supabase account
3. Click on your project: **"supabase-purple-house"**

### Step 2: Find Connection String
1. On the left sidebar, click **"Settings"** (gear icon)
2. Click **"Database"**
3. Look for section titled **"Connection string"** or **"Direct connection string"**
4. You should see this format:
   ```
   postgresql://postgres:[password]@db.ypvruuolmsqgihcgyuiu.supabase.co:5432/postgres
   ```

### Step 3: Copy the Full Connection String
1. Click the **"Copy"** button next to the connection string
2. The full URL will be copied with your actual password included
3. **SAVE THIS** - You'll need it for Vercel

### If you can't find the password:
1. In the same "Database" settings page, scroll down
2. Click **"Reset database password"**
3. Enter a new strong password (example: `MySecurePass123!@#`)
4. Copy the new connection string that appears

**Your final DATABASE_URL should look like:**
```
postgresql://postgres:MyActualPassword@db.ypvruuolmsqgihcgyuiu.supabase.co:5432/postgres
```

---

## 🚀 PART 3: Deploy to Vercel

### Step 1: Create a New Vercel Account (Optional)
- Go to: https://vercel.com/signup
- Sign up with email or GitHub
- Verify your email

### Step 2: Import Your GitHub Project
1. Go to: https://vercel.com/new
2. Click **"Import Git Repository"**
3. Paste your repository URL:
   ```
   https://github.com/sovs365/sovs
   ```
4. Click **"Continue"**
5. Select your GitHub account
6. Click **"Import"**

### Step 3: Configure Project Settings
1. You'll see a "Configure Project" page
2. Leave the default settings:
   - Framework: None (or Node.js)
   - Root Directory: ./
3. Click **"Continue"**

### Step 4: Add Environment Variables ⭐ IMPORTANT
1. You'll see an **"Environment Variables"** section
2. For each variable below, click **"Add New"** and enter:

**Add Variable 1:**
- **Name**: `DATABASE_URL`
- **Value**: Paste your full Supabase connection string (from Part 2)
  ```
  postgresql://postgres:YourPassword@db.ypvruuolmsqgihcgyuiu.supabase.co:5432/postgres
  ```
- **Environments**: Select ✅ Production, Preview, Development
- Click **"Add"**

**Add Variable 2:**
- **Name**: `JWT_SECRET`
- **Value**: `gdfreyo01-voting-backend-2024-secure-key`
- **Environments**: Select ✅ Production, Preview, Development
- Click **"Add"**

**Add Variable 3:**
- **Name**: `JWT_ISSUER`
- **Value**: `university-voting-app`
- **Environments**: Select ✅ Production, Preview, Development
- Click **"Add"**

**Add Variable 4:**
- **Name**: `JWT_AUDIENCE`
- **Value**: `university-voting-users`
- **Environments**: Select ✅ Production, Preview, Development
- Click **"Add"**

**Add Variable 5:**
- **Name**: `JWT_EXPIRATION_MS`
- **Value**: `86400000`
- **Environments**: Select ✅ Production, Preview, Development
- Click **"Add"**

**Add Variable 6:**
- **Name**: `NODE_ENV`
- **Value**: `production`
- **Environments**: Select ✅ Production, Preview, Development
- Click **"Add"**

**Add Variable 7:**
- **Name**: `APP_ENV`
- **Value**: `production`
- **Environments**: Select ✅ Production, Preview, Development
- Click **"Add"**

### Step 5: Deploy
1. After adding all variables, click **"Deploy"**
2. Wait for deployment to complete (usually 2-5 minutes)
3. You'll see a ✅ when deployment is successful
4. Copy your deployment URL (example: `https://sovs-abc123.vercel.app`)

### Step 6: Test Your Deployment
1. Visit your deployment URL
2. You should see your API running
3. Check the logs if there are any errors

---

## 📝 Quick Reference: All Environment Variables

| Variable | Value | Example |
|----------|-------|---------|
| DATABASE_URL | Supabase connection string | `postgresql://postgres:pwd@db.xxx.supabase.co:5432/postgres` |
| JWT_SECRET | Secret for JWT tokens | `gdfreyo01-voting-backend-2024-secure-key` |
| JWT_ISSUER | Token issuer | `university-voting-app` |
| JWT_AUDIENCE | Token audience | `university-voting-users` |
| JWT_EXPIRATION_MS | Token expiration (milliseconds) | `86400000` (24 hours) |
| NODE_ENV | Environment mode | `production` |
| APP_ENV | App environment | `production` |
| PORT | Server port | `3000` |

---

## ✅ Checklist Before Deploying

- [ ] GitHub account created (sovs365)
- [ ] Repository created on GitHub (sovs365/sovs)
- [ ] Project code pushed to GitHub
- [ ] Supabase project created
- [ ] Database connection string obtained from Supabase
- [ ] Vercel account created (or ready to use existing)
- [ ] All 7 environment variables copied and ready
- [ ] You understand not to commit secrets to GitHub

---

## 🐛 Troubleshooting

### Error: "Database initialization error: ENOTFOUND"
**Cause**: DATABASE_URL not set or incorrect
**Solution**:
1. Check DATABASE_URL is set in Vercel environment variables
2. Verify the connection string includes the password
3. Ensure it's set for Production environment
4. Redeploy after adding/fixing the variable

### Error: "Failed to initialize database"
**Cause**: Database connection string format is wrong
**Solution**:
1. Copy the connection string directly from Supabase
2. Ensure format is: `postgresql://postgres:password@host:5432/postgres`
3. Make sure password doesn't have special characters that need escaping

### Error: "JWT token invalid"
**Cause**: JWT_SECRET not matching across deploys
**Solution**:
1. Verify JWT_SECRET is exactly: `gdfreyo01-voting-backend-2024-secure-key`
2. Don' change JWT_SECRET between deployments
3. Make sure it's set in all environments (Production, Preview, Development)

### Deployment shows successful but app doesn't start
**Cause**: Could be any of the environment variables
**Solution**:
1. Go to Vercel project → Deployments → Latest → Logs
2. Check the error messages in the logs
3. Add the correct environment variable to fix the error
4. Redeploy

### Can't see deployment URL
**Cause**: Deployment still in progress
**Solution**:
1. Wait 2-5 minutes for deployment to complete
2. You'll see a ✅ checkmark when done
3. The URL will appear on the success page

---

## 📞 After Deployment

### View Your Live Application
- Your deployment URL: `https://your-project-name.vercel.app`
- All routes are available at this URL

### Monitor Your Deployment
- Go to: https://vercel.com/dashboard
- Select your project
- View logs, monitor performance, check deployments

### Make Updates
If you make changes to your code:
1. Commit changes to GitHub
2. Push to GitHub repo
3. Vercel automatically redeploys (auto-deployment on push)

---

## 🔐 Security Notes

1. **Never commit secrets to GitHub** - Keep `.env` out of version control
2. **Store secrets only in Vercel** - Use Vercel's environment variables UI
3. **Rotate secrets periodically** - Consider changing JWT_SECRET quarterly
4. **Use strong database passwords** - Make them complex when resetting in Supabase
5. **Limit access** - Only share deployment URLs with authorized users

---

## 📞 Quick Support

### If deployment fails:
1. Check **Vercel Logs** for the exact error
2. Verify all environment variables are correct
3. Ensure DATABASE_URL is set for Production environment
4. Try redeploying

### Where to check logs:
- Vercel Dashboard → Select Project → Deployments → Click deployment → Logs tab

---

## 🎯 Next Steps (After Successful Deployment)

1. ✅ Test your API endpoints
2. ✅ Set up a frontend to connect to your API
3. ✅ Configure CORS if needed
4. ✅ Monitor performance in Vercel Analytics
5. ✅ Set up error tracking/monitoring

---

**Your GitHub Repository**: https://github.com/sovs365/sovs  
**Your Supabase Dashboard**: https://app.supabase.com  
**Your Vercel Dashboard**: https://vercel.com/dashboard
