# Visual Checklist: GitHub Upload & Vercel Deployment

Follow this step-by-step. Don't skip! Print this out if needed.

---

## **✅ PHASE 1: Git Configuration (5 minutes)**

### Step 1: Check Git is installed
```
Windows Key + R → type "cmd" → press Enter
In Command Prompt type: git --version
```
- ❌ If error, download from: https://git-scm.com/download
- ✅ If you see git version, continue

### Step 2: Configure Git (One-time only)
```bash
git config --global user.name "Your Full Name"
git config --global user.email "youremail@gmail.com"
```
Replace with YOUR actual name and email.

---

## **✅ PHASE 2: Push Project to GitHub (10 minutes)**

### Step 3: Navigate to your backend folder
```bash
cd Desktop/smartbiz/SOVS/backend-node
```

**Verify you're in the right place:**
```bash
dir
```
You should see: `package.json`, `src`, `vercel.json`, etc.

### Step 4: Initialize Git
```bash
git init
```

### Step 5: Add all files
```bash
git add .
```

### Step 6: Make first commit
```bash
git commit -m "Initial backend setup"
```

### Step 7: Connect to GitHub
```bash
git remote add origin https://github.com/Godfreyo01/sovs.git
```

### Step 8: Push to GitHub
```bash
git push -u origin main
```

When asked for authentication:
- Enter your GitHub username
- Use your Personal Access Token as password (if needed)
  - Create one here: https://github.com/settings/tokens

**✅ Verify:** Go to https://github.com/Godfreyo01/sovs
You should see all your files!

---

## **✅ PHASE 3: Deploy to Vercel (5 minutes)**

### Step 9: Go to Vercel Dashboard
Visit: https://vercel.com/dashboard

### Step 10: Create New Project
1. Click "Add New"
2. Click "Project"
3. Click "Import Git Repository"

### Step 11: Connect GitHub
1. Paste: `https://github.com/Godfreyo01/sovs.git`
2. Click "Continue"
3. Authorize GitHub if prompted

### Step 12: Configure Vercel Project
- Project Name: `university-voting-backend`
- Framework: `Other`
- Root Directory: `./`
- Click "Deploy"

**Wait 2-3 minutes for deployment to complete...**

✅ When deployment is done, you'll see: **"Deployment Complete!"**

---

## **✅ PHASE 4: Add Environment Variables (5 minutes)**

### Step 13: Get Your Supabase Connection String

1. Go to: https://supabase.com
2. Click your project
3. Settings → Database
4. Copy connection string (Node.js version)
5. It looks like: `postgresql://postgres:[PASSWORD]@db.xxxxx.supabase.co:5432/postgres`

**IMPORTANT:** Replace `[PASSWORD]` with your actual database password!

### Step 14: Generate JWT Secret
In Command Prompt, run:
```bash
node -e "console.log(require('crypto').randomBytes(32).toString('hex'))"
```
Copy the long text that appears.

### Step 15: Add to Vercel
1. Go to your Vercel project
2. Click "Settings"
3. Click "Environment Variables"
4. Add each variable:

| Name | Value |
|------|-------|
| `APP_ENV` | `production` |
| `NODE_ENV` | `production` |
| `PORT` | `3000` |
| `DATABASE_URL` | Your Supabase connection string |
| `JWT_SECRET` | The long text you generated |
| `JWT_ISSUER` | `university-voting-app` |
| `JWT_AUDIENCE` | `university-voting-users` |
| `CORS_ALLOWED_HOSTS` | Your frontend URL (if you have one) |

### Step 16: Redeploy
1. Go to "Deployments"
2. Click the three dots "..."
3. Click "Redeploy"

**Wait 2 minutes for redeployment...**

---

## **✅ PHASE 5: Test Backend (3 minutes)**

### Step 17: Get Your Vercel URL
1. In Vercel dashboard, look for your project URL
2. It looks like: `https://university-voting-backend-xxxxx.vercel.app`
3. Copy it

### Step 18: Test Health Endpoint
Open browser and visit:
```
https://YOUR_URL.vercel.app/health
```

**You should see:** `OK`

✅ If you see OK, your backend is running!

### Step 19: Test API (Optional)
Open Command Prompt and run:
```bash
curl -X POST https://YOUR_URL.vercel.app/api/auth/register -H "Content-Type: application/json" -d "{\"username\":\"testuser\",\"password\":\"Test123\",\"fullName\":\"Test User\",\"phoneNumber\":\"2547XXXXXXXX\",\"role\":\"voter\"}"
```

**You should get a response with a token!**

---

## **✅ DONE! Your Backend is Live!** 🎉

### Your Backend URL:
```
https://university-voting-backend-xxxxx.vercel.app
```

**Use this URL in your frontend app!**

---

## **Quick Ref: Making Future Updates**

After this, updating is super easy:

```bash
cd Desktop/smartbiz/SOVS/backend-node
# Make your changes...
git add .
git commit -m "Describe what changed"
git push origin main
```

**Vercel automatically redeploys!** (1-2 minutes)

---

## **Troubleshooting Checklist**

| Problem | Solution |
|---------|----------|
| Git command not found | Install Git from https://git-scm.com |
| Push fails | Create Personal Access Token on GitHub |
| Vercel deployment fails | Check Vercel logs for error message |
| Health check returns error | Verify DATABASE_URL is correct |
| 502 Bad Gateway | Redeploy with correct environment variables |
| Can't connect from frontend | Check CORS_ALLOWED_HOSTS setting |

---

## **Your Deployment Checklist**

- [ ] Git installed (`git --version` works)
- [ ] Git configured with name and email
- [ ] Code pushed to GitHub (verify on github.com)
- [ ] Project deployed to Vercel
- [ ] Environment variables added
- [ ] Vercel redeployed
- [ ] Health check returns "OK"
- [ ] API test works
- [ ] Frontend connected to backend URL

---

**You did it! The hardest part is behind you now!** 🚀

If you get stuck, refer to `BEGINNER_GITHUB_VERCEL_GUIDE.md` for detailed explanations.
