# Complete GitHub Upload & Vercel Deployment Guide for Beginners

This guide walks you through uploading your Node.js backend to GitHub and deploying to Vercel with Supabase. **No prior experience needed!**

## Prerequisites Checklist

Before starting, ensure you have:

- ✅ Git installed on your computer: https://git-scm.com/download
  - To check: Open Command Prompt and type `git --version`
  
- ✅ GitHub account created: https://github.com
  
- ✅ Vercel account created: https://vercel.com
  
- ✅ Supabase database already set up in Vercel
  
- ✅ Repository created: https://github.com/Godfreyo01/sovs

## Step-by-Step Instructions

---

## **PART 1: Learn Basic Git Concepts (5 minutes)**

Don't worry about command line - we'll do this step-by-step.

**What is Git?**
- Git is version control software that tracks changes to your code
- It's like Google Docs but for code files

**Key concepts:**
- **Repository (Repo)** = Your project folder on GitHub
- **Commit** = Saving a version of your code
- **Push** = Uploading your code to GitHub
- **Clone** = Downloading a repo from GitHub

---

## **PART 2: Open Command Prompt**

### Windows Users:

1. Press **Windows Key + R**
2. Type: `cmd`
3. Press **Enter**
4. You should see: `C:\Users\YourName>`

### You're now ready for command line!

---

## **PART 3: Configure Git (One-Time Setup)**

Run these commands in Command Prompt (copy & paste, press Enter):

```bash
git config --global user.name "Your Name"
git config --global user.email "your@email.com"
```

**Replace:**
- `Your Name` with your actual name (e.g., John Doe)
- `your@email.com` with your GitHub email

**Example:**
```bash
git config --global user.name "Godfrey"
git config --global user.email "godfrey@example.com"
```

**What this does:** Tells Git who you are when you save code.

---

## **PART 4: Navigate to Your Backend Folder**

Close Command Prompt and open it again. Then run:

```bash
cd Desktop/smartbiz/SOVS/backend-node
```

**You should now see:**
```
C:\Users\YourName\Desktop\smartbiz\SOVS\backend-node>
```

**Verify you're in the right folder:**
```bash
dir
```

You should see these files:
- `package.json`
- `src` (folder)
- `vercel.json`
- etc.

---

## **PART 5: Initialize Git in Your Project**

Run this command:

```bash
git init
```

**You should see:**
```
Initialized empty Git repository in C:\Users\...\backend-node\.git
```

---

## **PART 6: Add All Files to Git**

Run this command:

```bash
git add .
```

**What this does:** Stages all files for saving (like putting items in a box).

---

## **PART 7: Create Your First Commit**

Run this command:

```bash
git commit -m "Initial backend setup"
```

**What this does:** Saves this version with a message explaining what changed.

**You should see:**
```
[main (root-commit) abc1234] Initial backend setup
 20 files changed, 1500 insertions(+)
```

---

## **PART 8: Connect to GitHub Repository**

### Step 1: Get Your Repository URL

1. Go to https://github.com/Godfreyo01/sovs
2. Click the green **"Code"** button
3. Click **"HTTPS"** tab
4. Copy the URL (should look like: `https://github.com/Godfreyo01/sovs.git`)

### Step 2: Add GitHub as Remote

Run this command (replace with YOUR repo URL):

```bash
git remote add origin https://github.com/Godfreyo01/sovs.git
```

**Verify it worked:**
```bash
git remote -v
```

**You should see:**
```
origin  https://github.com/Godfreyo01/sovs.git (fetch)
origin  https://github.com/Godfreyo01/sovs.git (push)
```

---

## **PART 9: Push Code to GitHub**

Run this command:

```bash
git push -u origin main
```

**First time only:** Git might ask for authentication.
- Click the blue "Authorize in browser" link
- Or enter your GitHub username and Personal Access Token (if prompted)

**You should see something like:**
```
Counting objects: 100% (25/25)
Delta compression using up to 4 threads
Compressing objects: 100% (20/20), done.
Writing objects: 100% (25/25), 2.50 KiB | 2.50 MiB/s, done.
Total 25 (delta 0), reused 0 (delta 0), pack-reused 0
remote:
remote: Create a pull request for 'main' on GitHub by visiting:
remote:      https://github.com/Godfreyo01/sovs/pull/new/main
```

### ✅ **Your code is now on GitHub!**

**Verify:** Go to https://github.com/Godfreyo01/sovs and you should see your code files.

---

## **PART 10: Deploy to Vercel**

### Step 1: Go to Vercel Dashboard

1. Go to https://vercel.com/dashboard
2. Click **"Add New"** button
3. Select **"Project"**

### Step 2: Import from GitHub

1. Click **"Import Git Repository"**
2. Paste your repository URL: `https://github.com/Godfreyo01/sovs`
3. Click **"Continue"**

**If prompted to authenticate with GitHub:**
- Click **"Authorize"** and sign in with your GitHub account

### Step 3: Configure Project

On the "Import Project" page:

1. **Project Name:** `university-voting-backend` (or any name you like)
2. **Framework Preset:** Select **"Other"**
3. **Root Directory:** Leave as `./`
4. Click **"Deploy"**

**Vercel will now build your project** (takes 1-3 minutes).

---

## **PART 11: Get Your Supabase Connection String**

### Step 1: Access Supabase

1. Go to https://supabase.com
2. Sign in with your account
3. Click on your **"voting-app"** project
4. Go to **"Settings"** (bottom left)
5. Click **"Database"**

### Step 2: Copy Connection String

1. Look for **"Connection String"**
2. Select **"Node.js"** from the dropdown (if available)
3. Copy the entire connection string
4. It will look like:
```
postgresql://postgres:[YOUR-PASSWORD]@db.xxxxx.supabase.co:5432/postgres
```

**Important:** Replace `[YOUR-PASSWORD]` with your actual database password!

---

## **PART 12: Add Environment Variables to Vercel**

### Step 1: Go to Vercel Project Settings

1. Go to https://vercel.com/dashboard
2. Click your **"university-voting-backend"** project
3. Click **"Settings"** tab

### Step 2: Add Environment Variables

1. On the left, click **"Environment Variables"**
2. For each variable below, click **"Add New"** and enter:

| Variable Name | Value | Notes |
|---------------|-------|-------|
| `APP_ENV` | `production` | Fixed value |
| `NODE_ENV` | `production` | Fixed value |
| `PORT` | `3000` | Fixed value |
| `DATABASE_URL` | Your Supabase connection string | Replace [PASSWORD] with your DB password |
| `JWT_SECRET` | Generate a strong key (see below) | **Keep it secret!** |
| `JWT_ISSUER` | `university-voting-app` | Fixed value |
| `JWT_AUDIENCE` | `university-voting-users` | Fixed value |
| `CORS_ALLOWED_HOSTS` | Your frontend URL | e.g., `https://yourfrontend.com` |

### Step 3: Generate JWT_SECRET

Run this in Command Prompt:

```bash
node -e "console.log(require('crypto').randomBytes(32).toString('hex'))"
```

**Copy the output** and use it as your `JWT_SECRET` value.

---

## **PART 13: Redeploy Vercel**

After adding environment variables:

1. Go to your Vercel project
2. Click **"Deployments"** tab
3. Click the three dots **"..."** on your latest deployment
4. Click **"Redeploy"**

**Vercel will rebuild** with your environment variables.

---

## **PART 14: Verify Deployment**

### Check if Backend is Running

1. Go to your Vercel project
2. Copy your deployment URL (looks like: `https://university-voting-backend-xxx.vercel.app`)
3. Open in browser: `https://YOUR_VERCEL_URL/health`
4. You should see: **OK**

### Test with Complete URL

```
https://your-vercel-url.vercel.app/health
```

If you see **"OK"**, your backend is working! ✅

---

## **PART 15: Test the API**

### Test Registration Endpoint

1. Open Command Prompt
2. Run this command (replace YOUR_VERCEL_URL):

```bash
curl -X POST https://YOUR_VERCEL_URL.vercel.app/api/auth/register -H "Content-Type: application/json" -d "{\"username\":\"testuser\",\"password\":\"Test123\",\"fullName\":\"Test User\",\"phoneNumber\":\"254712345678\",\"role\":\"voter\"}"
```

**If it works, you'll see a JSON response with a token!**

If curl doesn't work, use Postman instead:
1. Download Postman: https://www.postman.com/downloads/
2. Create new POST request to: `https://YOUR_VERCEL_URL.vercel.app/api/auth/register`
3. Add JSON body:
```json
{
  "username": "testuser",
  "password": "Test123",
  "fullName": "Test User",
  "phoneNumber": "254712345678",
  "role": "voter"
}
```
4. Click Send

---

## **PART 16: Troubleshooting**

### Problem: "Failed to push to GitHub"

**Solution:**
1. Go to GitHub Settings → Developer settings → Personal access tokens
2. Create a new token with `repo` scope
3. Use the token instead of your password when pushing

### Problem: Vercel deployment fails (Error in logs)

**Solution:**
1. Check logs: Vercel Dashboard → Deployments → Click failed deployment → Logs
2. Look for error message
3. Common issues:
   - `DATABASE_URL` is incorrect
   - Missing environment variables
   - Node version mismatch

### Problem: "502 Bad Gateway" after deployment

**Solution:**
1. Check your `DATABASE_URL` is correct
2. Verify database password contains no special characters
3. Redeploy from Vercel dashboard

### Problem: CORS Error in browser

**Solution:**
1. Add your frontend URL to `CORS_ALLOWED_HOSTS`
2. Include protocol: `https://yourfrontend.com` (not `yourfrontend.com`)
3. Redeploy

---

## **Summary of What You've Done**

✅ Configured Git with your identity
✅ Initialized Git in your backend project
✅ Created first commit
✅ Pushed code to GitHub
✅ Imported GitHub repo to Vercel
✅ Connected Supabase database
✅ Added environment variables
✅ Deployed to Vercel
✅ Verified backend is running

---

## **Your Backend is Now Live!**

### Your Backend URL:
```
https://your-vercel-url.vercel.app
```

### Use this URL in your frontend to connect!

---

## **Quick Reference: After First Deployment**

### Making Changes and Redeploying:

Every time you make changes:

```bash
# Navigate to your backend folder
cd Desktop/smartbiz/SOVS/backend-node

# Save your changes
git add .
git commit -m "Describe your changes here"
git push origin main
```

**Vercel will automatically redeploy!** (usually within 1-2 minutes)

---

## **Next Steps**

1. ✅ Backend deployed to Vercel
2. ⬜ Connect your frontend to this backend URL
3. ⬜ Test API endpoints from frontend
4. ⬜ Deploy frontend to Vercel/Netlify
5. ⬜ Test end-to-end

---

## **Additional Resources**

- **Vercel Docs:** https://vercel.com/docs
- **Git Guide:** https://rogerdudley.github.io/git-guide/
- **Supabase Docs:** https://supabase.com/docs
- **REST API Testing:** https://www.postman.com/

---

## **Need Help?**

If stuck at any step:
1. Read the error message carefully
2. Check this guide for that step
3. Look at troubleshooting section
4. Check Vercel/GitHub logs

---

**You did it! Your backend is deployed! 🎉**
