# SOVS Backend - Render Deployment

University Voting System Backend API built with Node.js/Express and PostgreSQL (Supabase).

## Quick Start

### Prerequisites
- Node.js 24.x or higher
- npm (comes with Node.js)
- PostgreSQL database (Supabase)
- Render account (https://render.com)

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/sovs365/sovs.git
   cd sovs/backend-node
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Create .env file** (copy from .sovs and update with your values)
   ```bash
   cp .sovs .env
   ```

4. **Update environment variables in .env**
   - Update `DATABASE_URL` with your Supabase connection string
   - Keep other values as provided

5. **Start the server**
   ```bash
   npm start
   ```

   For development with auto-reload:
   ```bash
   npm run dev
   ```

The server will start on: `http://localhost:3000`

---

## Environment Variables

All required environment variables are documented in the `.sovs` file:

| Variable | Description | Example |
|----------|-------------|---------|
| DATABASE_URL | PostgreSQL connection string | `postgresql://postgres:password@host:5432/db` |
| JWT_SECRET | Secret key for JWT tokens | `gdfreyo01-voting-backend-2024-secure-key` |
| JWT_ISSUER | JWT issuer identifier | `university-voting-app` |
| JWT_AUDIENCE | JWT audience identifier | `university-voting-users` |
| JWT_EXPIRATION_MS | Token expiration time in ms | `86400000` (24 hours) |
| NODE_ENV | Environment mode | `production` |
| APP_ENV | Application environment | `production` |
| PORT | Server port | `3000` |

---

## Deployment to Render

### Step 1: Create Render Account
1. Go to https://render.com
2. Sign up with GitHub or email
3. Connect your GitHub account

### Step 2: Create New Web Service
1. Dashboard → New Web Service
2. Select: `https://github.com/sovs365/sovs`
3. Configure:
   - **Name**: `sovs-backend`
   - **Runtime**: Node
   - **Build Command**: `npm install`
   - **Start Command**: `npm start`

### Step 3: Add Environment Variables
1. In Render dashboard → Environment
2. Add all variables from `.sovs` file:
   ```
   DATABASE_URL=postgresql://postgres:YOUR_PASSWORD@db.xxx.supabase.co:5432/postgres
   JWT_SECRET=gdfreyo01-voting-backend-2024-secure-key
   JWT_ISSUER=university-voting-app
   JWT_AUDIENCE=university-voting-users
   JWT_EXPIRATION_MS=86400000
   NODE_ENV=production
   APP_ENV=production
   ```

### Step 4: Deploy
1. Click "Create Web Service"
2. Render will automatically build and deploy
3. Get your live URL: `https://your-service-name.onrender.com`

---

## Database Setup

### Using Supabase

1. Create project at https://app.supabase.com
2. Get connection string: Settings → Database → Connection string
3. Copy the PostgreSQL connection URL
4. Set as `DATABASE_URL` environment variable

The app will automatically create tables on first run using the schema defined in `src/db.js`.

---

## API Endpoints

- `GET /` - Health check
- `POST /api/users/register` - Register new user
- `POST /api/users/login` - User login
- `GET /api/users` - Get all users
- And more... (check routes in `src/routes/`)

---

## Project Structure

```
backend-node/
├── src/
│   ├── index.js        - Main application file
│   ├── db.js           - Database configuration
│   ├── auth.js         - JWT authentication
│   ├── routes/         - API routes
│   └── ...
├── package.json        - Dependencies
├── render.yaml         - Render deployment config
├── .sovs               - Environment variables template
└── ...
```

---

## Troubleshooting

### Build fails on Render
- Check Node.js version: `node --version` (needs 24.x)
- Verify all dependencies are in package.json
- Check build logs in Render dashboard

### Database connection error
- Verify DATABASE_URL is correct
- Ensure Supabase database is running
- Check password doesn't have special characters that need escaping

### App crashes after deployment
- Check logs: Render dashboard → Logs
- Verify all environment variables are set
- Make sure DATABASE_URL uses correct format

### Can't connect to frontend
- Ensure CORS is properly configured
- Check `CORS_ALLOWED_HOSTS` environment variable
- Frontend must use the Render service URL

---

## Monitoring

View your app in production:
- **Render Dashboard**: https://dashboard.render.com
- **View Logs**: Select service → Logs
- **Monitor Performance**: Service → Analytics
- **View Deployed App**: Click the service URL

---

## Auto-deployment

- Any push to GitHub main branch automatically triggers Render deployment
- Render pulls latest code and rebuilds automatically
- Deployment typically takes 2-5 minutes

---

## Support

- GitHub: https://github.com/sovs365/sovs
- Render Docs: https://render.com/docs
- Supabase Docs: https://supabase.com/docs

---

## License

MIT License - See LICENSE file for details
