# 🚀 RENDER DEPLOYMENT GUIDE FOR BEGINNERS
## Complete Step-by-Step Instructions for SOVS Backend

---

## ⚡ IMPORTANT: DATABASE SETUP FIRST

**BEFORE following this guide**, set up your Render PostgreSQL database:

📖 **Follow**: `RENDER_DATABASE_SETUP.md`

This will give you your DATABASE_URL, which you'll need in this guide!

---

Make sure you have:
- [ ] GitHub account (`sovs365`)
- [ ] GitHub repository created (`sovs365/sovs`)
- [ ] Supabase database set up (got your connection string)
- [ ] Render account (free signup at https://render.com)
- [ ] 15 minutes of time
- [ ] This guide open on your screen

---

## 🔑 ALL YOUR ENVIRONMENT VARIABLES

**IMPORTANT**: These are saved in your `.env.sovs` file in the repository

```
DATABASE_URL=postgresql://postgres:sovsnew@365@db.xxxxx.supabase.co:5432/postgres
JWT_SECRET=gdfreyo01-voting-backend-2024-secure-key
JWT_ISSUER=university-voting-app
JWT_AUDIENCE=university-voting-users
JWT_EXPIRATION_MS=86400000
NODE_ENV=production
APP_ENV=production
PORT=3000
CORS_ALLOWED_HOSTS=*
```

**Total Variables**: 9

---

# PART 1: GETTING YOUR RENDER DATABASE CONNECTION STRING

## Step 1: Create Your Render Database FIRST

**IMPORTANT**: Before starting this deployment, you MUST create a PostgreSQL database in Render.

**Follow This Guide**: `RENDER_DATABASE_SETUP.md`

This will show you:
- How to create PostgreSQL database on Render
- Where to find your connection string
- How to copy your DATABASE_URL

**⏱️ Time**: 5-10 minutes

## After Database Is Created:

Once you have your DATABASE_URL from Render (from RENDER_DATABASE_SETUP.md), continue to PART 2 below.

---

# PART 2: CREATE RENDER ACCOUNT

## Step 1: Go to Render Website
1. Open: https://render.com
2. Click **"Sign Up"** (top right)

## Step 2: Choose Sign Up Method
- Option A: Sign up with **GitHub** (easiest - connects automatically)
- Option B: Sign up with email

### If Using GitHub:
1. Click **"GitHub"**
2. Click **"Authorize render"**
3. GitHub will ask permission - click **"Authorize"**

### If Using Email:
1. Enter your email
2. Create a password
3. Click **"Sign up"**

## Step 3: Verify Email
- Check your email for verification link
- Click the link
- You're done! ✅

---

# PART 3: CONNECT RENDER TO GITHUB

## If You Signed Up With GitHub:
**Skip to Part 4** - Already connected!

## If You Signed Up With Email:

1. Go to: https://dashboard.render.com
2. Click your **Account** (top right) → **Account Settings**
3. Scroll down to **"Connected Services"**
4. Click **"Connect GitHub"**
5. Authorize Render to access your GitHub account
6. Click **"Authorize render-oss"**

---

# PART 4: CREATE A WEB SERVICE ON RENDER

## Step 1: Go to Dashboard
1. Open: https://dashboard.render.com
2. You should see the **Dashboard**

## Step 2: Create New Service
1. Click **"New +"** button (usually top right)
2. Click **"Web Service"**

## Step 3: Select Your Repository
1. You'll see: **"Connect a repository"**
2. Click **"GitHub"** (or your connected service)
3. Find and click: **`sovs365/sovs`** (your repository)
4. Click **"Connect"**

## Step 4: Configure the Service

**Fill in these fields:**

| Field | Value |
|-------|-------|
| **Name** | `sovs-backend` |
| **Environment** | `Node` |
| **Region** | `Ohio (us-east)` (or closest to you) |
| **Branch** | `main` |
| **Build Command** | `npm install` |
| **Start Command** | `npm start` |

Leave other fields as default.

---

# PART 5: ADD ENVIRONMENT VARIABLES TO RENDER

## Step 1: Scroll Down to "Environment"
On the same page, scroll down until you see:
- **"Environment Variables"** section

## Step 2: Add Each Variable

**You'll Add 9 Variables - Do Them One By One:**

### Variable 1: DATABASE_URL
1. Click **"Add Environment Variable"** (or + button)
2. **Key**: `DATABASE_URL`
3. **Value**: Paste your Supabase connection string from Part 1
   ```
   postgresql://postgres:sovsnew@365@db.xxxxx.supabase.co:5432/postgres
   ```
4. Press **Tab** or click elsewhere - it saves automatically

### Variable 2: JWT_SECRET
1. Click **"Add Environment Variable"**
2. **Key**: `JWT_SECRET`
3. **Value**: `gdfreyo01-voting-backend-2024-secure-key`

### Variable 3: JWT_ISSUER
1. Click **"Add Environment Variable"**
2. **Key**: `JWT_ISSUER`
3. **Value**: `university-voting-app`

### Variable 4: JWT_AUDIENCE
1. Click **"Add Environment Variable"**
2. **Key**: `JWT_AUDIENCE`
3. **Value**: `university-voting-users`

### Variable 5: JWT_EXPIRATION_MS
1. Click **"Add Environment Variable"**
2. **Key**: `JWT_EXPIRATION_MS`
3. **Value**: `86400000`

### Variable 6: NODE_ENV
1. Click **"Add Environment Variable"**
2. **Key**: `NODE_ENV`
3. **Value**: `production`

### Variable 7: APP_ENV
1. Click **"Add Environment Variable"**
2. **Key**: `APP_ENV`
3. **Value**: `production`

### Variable 8: PORT
1. Click **"Add Environment Variable"**
2. **Key**: `PORT`
3. **Value**: `3000`

### Variable 9: CORS_ALLOWED_HOSTS
1. Click **"Add Environment Variable"**
2. **Key**: `CORS_ALLOWED_HOSTS`
3. **Value**: `*`

**Verify All 9 Variables Are Added** ✓

---

# PART 6: DEPLOY TO RENDER

## Step 1: Find the Deploy Button
1. At the **bottom right** of the page
2. You should see: **"Create Web Service"** button
3. Click it

## Step 2: Wait for Build
**Building starts automatically:**
- You'll see a **Build** section showing progress
- This takes **2-5 minutes**
- You'll see messages like:
  - "Building..."
  - "Installing dependencies..."
  - "Build successful"

## Step 3: Wait for Deployment
- After build completes, deployment starts
- You'll see a **Deploy** section
- Status should show: **"Live"** ✅

## Step 4: Get Your Live URL
- At the top of your service page
- You'll see your **Live URL**, like:
  ```
  https://sovs-backend.onrender.com
  ```
- This is your API URL! 🎉

---

# PART 7: TEST YOUR DEPLOYMENT

## Test If Your API Is Working

### Option 1: Using Browser
1. Open your Render URL in browser
2. Should see something like:
   ```
   {"message":"API is working"}
   ```
   Or just show the HTML

### Option 2: Using curl (Terminal)
```bash
curl https://your-render-url.onrender.com
```

### Option 3: Using Postman
1. Create new GET request
2. Enter: `https://your-render-url.onrender.com`
3. Click **"Send"**
4. Should get a response

**If you see a response** → ✅ Your API is working!

---

# PART 8: WHAT HAPPENS NEXT

## Auto-Deployment
- **Every time** you push code to GitHub
- **Render automatically** rebuilds and deploys
- Takes 2-5 minutes

## How to Update Your App

### If You Make Code Changes:
1. Edit files locally
2. Commit to GitHub:
   ```bash
   git add .
   git commit -m "your message"
   git push origin main
   ```
3. Render automatically redeploys
4. Check logs in Render to see progress

## Monitor Your App
1. Go to: https://dashboard.render.com
2. Select your service: `sovs-backend`
3. You can see:
   - **Logs** - What's happening
   - **Metrics** - Performance
   - **Events** - Deployment history

---

# PART 9: TROUBLESHOOTING

## Problem: Build Fails

### Check This:
1. Go to **Logs** tab in Render
2. Scroll to top to see error message
3. Common errors:
   - Missing environment variables
   - Wrong variable values
   - Node.js version issue

### Fix:
- Check Part 5 again - make sure all 9 variables are added
- Verify values are exactly correct (no spaces, no typos)
- Trigger a manual redeploy

## Problem: App Deployed but Returns Error

### Check This:
1. Open **Logs** in Render
2. Look for error message like:
   - "Cannot connect to database"
   - "JWT_SECRET not found"

### Fix:
- DATABASE_URL missing or wrong → add it in Environment Variables
- Missing JWT variables → add all 9 from Part 5
- Save changes → trigger **"Deploy latest"**

## Problem: Can't Access Your Live URL

### Check This:
1. Render shows status as **"Live"** (green)? If not, wait or check logs
2. Try refreshing the page (Ctrl+R or Cmd+R)
3. Make sure you're using the correct URL

### If Still Not Working:
1. Go to dashboard https://dashboard.render.com
2. Select your service
3. Click **"Deploy latest"** button (force redeploy)

---

# PART 10: YOUR LIVE SYSTEM

## You Now Have:

✅ **Live API** accessible worldwide  
✅ **Database** connected on Supabase  
✅ **Auto-deployment** from GitHub  
✅ **Environment variables** secured in Render  
✅ **Logs** for monitoring  

## Your URLs:

| What | URL |
|------|-----|
| Render Dashboard | https://dashboard.render.com |
| Your Live API | https://sovs-backend.onrender.com (or your URL) |
| GitHub Repo | https://github.com/sovs365/sovs |
| Supabase DB | https://app.supabase.com |

---

# PART 11: NEXT STEPS

## After Deployment:

1. **Test Your API**
   - Try making requests to your endpoints
   - Verify database connection works

2. **Connect Your Frontend**
   - Use your Render URL as API base: `https://sovs-backend.onrender.com`
   - Update CORS if needed

3. **Monitor Performance**
   - Check Render logs regularly
   - Monitor database usage in Supabase

4. **Keep Code Updated**
   - Push changes to GitHub
   - Render auto-deploys

---

# QUICK REFERENCE

## All 9 Environment Variables (Copy From `.env.sovs`):

```
DATABASE_URL=postgresql://postgres:sovsnew@365@db.xxxxx.supabase.co:5432/postgres
JWT_SECRET=gdfreyo01-voting-backend-2024-secure-key
JWT_ISSUER=university-voting-app
JWT_AUDIENCE=university-voting-users
JWT_EXPIRATION_MS=86400000
NODE_ENV=production
APP_ENV=production
PORT=3000
CORS_ALLOWED_HOSTS=*
```

## Important Links:

- **Render Sign Up**: https://render.com
- **Render Dashboard**: https://dashboard.render.com
- **Supabase Dashboard**: https://app.supabase.com
- **Your GitHub Repo**: https://github.com/sovs365/sovs

---

# ❓ NEED HELP?

## Common Questions:

**Q: How long does deployment take?**  
A: Usually 2-5 minutes for build + deploy

**Q: Can I have multiple environments?**  
A: Yes! Create separate services for staging/production

**Q: How do I update my code?**  
A: Push to GitHub → Render auto-deploys

**Q: How much does Render cost?**  
A: Free tier available! Paid plans start at $7/month

**Q: Where are my logs?**  
A: Render Dashboard → Your Service → Logs tab

---

**YOU'RE READY TO DEPLOY! 🎉**

Follow the steps in order, and your API will be live on Render!

Any questions? Check Part 9 (Troubleshooting) or refer to the `.env.sovs` file in your repository.
