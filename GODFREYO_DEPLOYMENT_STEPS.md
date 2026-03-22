# GitHub Upload for Godfreyo01 - Step-by-Step

## Your Information
- **GitHub Username:** Godfreyo01
- **Repository URL:** https://github.com/Godfreyo01/sovs.git
- **Backend Folder:** c:\Users\cyber\Desktop\smartbiz\SOVS\backend-node

## What's Done ✅
- Git initialized
- 18 files committed
- Branch renamed to `main`
- Remote added to GitHub

## What's Left: Push to GitHub

### Issue Encountered
Authentication error (Permission 403)

### Solution: Create Personal Access Token

**Step 1: Go to GitHub Token Settings**
- Visit: https://github.com/settings/tokens/new
- Sign in with your credentials: `Godfreyo01`

**Step 2: Create New Token**
- Click "Generate new token" → "Generate new token (classic)"

**Step 3: Configure Your Token**
```
Token name: sovs-backend-token
Expiration: 90 days
Scopes to select:
  ☑ repo (Full control of private repositories)
  ☑ workflow (Update GitHub Actions and workflows)
```

**Step 4: Generate & Copy Token**
- Click "Generate token"
- **COPY THE TOKEN IMMEDIATELY** (you won't see it again!)
- Paste it somewhere safe temporarily

**Step 5: Open Command Prompt (Not PowerShell!)**
- Press: `Windows Key + R`
- Type: `cmd`
- Press: `Enter`

**Step 6: Navigate to Your Backend**
```bash
cd Desktop\smartbiz\SOVS\backend-node
```

**Step 7: Push to GitHub**
```bash
git push -u origin main
```

**Step 8: Authentication Prompt**
When asked:
- Username: `Godfreyo01`
- Password: **Paste your Personal Access Token** (not your GitHub password)

### Expected Output
```
Counting objects: 100% (18/18)
Delta compression using up to 4 threads
Compressing objects: 100% (16/16), done.
Writing objects: 100% (18/18), 2.50 KiB | 2.50 MiB/s, done.
Total 18 (delta 0), reused 0 (delta 0), pack-reused 0
remote:
remote: Create a pull request for 'main' on GitHub by visiting:
remote:      https://github.com/Godfreyo01/sovs/pull/new/main
To https://github.com/Godfreyo01/sovs.git
 * [new branch]      main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

### Verify on GitHub
After successful push:
1. Go to: https://github.com/Godfreyo01/sovs
2. Click the "Code" tab
3. You should see your files:
   - `src/` folder
   - `package.json`
   - `README.md`
   - etc.

---

## Next: Deploy to Vercel

Once GitHub push is successful, follow these steps:

### Step 1: Go to Vercel
- Visit: https://vercel.com/godfreyo01s-projects/~/stores
- Your store is already set up ✓

### Step 2: Create New Project
1. Click "Add New"
2. Click "Project"
3. Click "Import Git Repository"

### Step 3: Connect GitHub
1. Click "Authorize GitHub" if needed
2. Paste your repo URL: `https://github.com/Godfreyo01/sovs.git`
3. Click "Continue"

### Step 4: Configure Project
- **Project Name:** `university-voting-backend`
- **Framework Preset:** `Other`
- **Root Directory:** `./`
- Click "Deploy"

**Wait 2-3 minutes for build...**

### Step 5: Add Environment Variables
After deployment completes:

1. Go back to Vercel project
2. Click "Settings" tab
3. Click "Environment Variables"
4. Add these variables:

| Variable Name | Your Value |
|---------------|-----------|
| `APP_ENV` | `production` |
| `NODE_ENV` | `production` |
| `PORT` | `3000` |
| `DATABASE_URL` | Your Supabase connection string |
| `JWT_SECRET` | Generate: openssl rand -base64 64 |
| `JWT_ISSUER` | `university-voting-app` |
| `JWT_AUDIENCE` | `university-voting-users` |
| `CORS_ALLOWED_HOSTS` | `https://yourfrontend.com` |

### Step 6: Get Supabase Connection String
1. Go to: https://supabase.com
2. Open your project
3. Settings → Database → Connection Strings
4. Copy "Node.js" version
5. Replace `[YOUR-PASSWORD]` with your database password

### Step 7: Redeploy Vercel
1. Back in Vercel project
2. Click "Deployments"
3. Click "..." on latest deployment
4. Click "Redeploy"

**Wait 2 minutes...**

### Step 8: Verify Deployment
Visit: `https://university-voting-backend-XXXXX.vercel.app/health`

You should see: `OK`

✅ Your backend is LIVE!

---

## Your Backend URL (After Deployment)
```
https://university-voting-backend-XXXXX.vercel.app
```

**Use this in your frontend app!**

---

## Troubleshooting

### If Push Fails Again
**Error:** `Permission denied`
- Solution: Make sure you're using Personal Access Token, not password
- Make sure token was created with `repo` scope

**Error:** `fatal: unable to access repository`
- Solution: Check internet connection
- Check repo URL is correct: `https://github.com/Godfreyo01/sovs.git`

### If Vercel Deployment Fails
**Check logs:**
1. Vercel Dashboard → Deployments → Failed deployment
2. Click to view detailed logs
3. Look for error message
4. Common issues:
   - DATABASE_URL is wrong
   - Missing environment variables
   - Node version incompatibility

---

## Quick Ref: Future Updates

After initial deployment, updating is easy:

```bash
cd Desktop\smartbiz\SOVS\backend-node
# Make your changes...
git add .
git commit -m "Your changes description"
git push origin main
```

Vercel automatically redeploys! (1-2 minutes)

---

**Ready? Start with creating your Personal Access Token from:**
https://github.com/settings/tokens/new
