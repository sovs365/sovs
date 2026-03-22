# Environment Variables - Quick Reference

Copy and paste these exactly when deploying to Vercel.

## All Required Variables

```
DATABASE_URL=postgresql://postgres:YOUR_PASSWORD@db.ypvruuolmsqgihcgyuiu.supabase.co:5432/postgres
JWT_SECRET=gdfreyo01-voting-backend-2024-secure-key
JWT_ISSUER=university-voting-app
JWT_AUDIENCE=university-voting-users
JWT_EXPIRATION_MS=86400000
NODE_ENV=production
APP_ENV=production
```

## Variables to Add in Vercel (One by One)

| # | Name | Value |
|---|------|-------|
| 1 | DATABASE_URL | `postgresql://postgres:YOUR_PASSWORD@db.ypvruuolmsqgihcgyuiu.supabase.co:5432/postgres` |
| 2 | JWT_SECRET | `gdfreyo01-voting-backend-2024-secure-key` |
| 3 | JWT_ISSUER | `university-voting-app` |
| 4 | JWT_AUDIENCE | `university-voting-users` |
| 5 | JWT_EXPIRATION_MS | `86400000` |
| 6 | NODE_ENV | `production` |
| 7 | APP_ENV | `production` |

## How to Use This

1. In Vercel: Settings → Environment Variables → Add New
2. Copy the **Name** exactly as shown above
3. Copy the **Value** exactly as shown above
4. Select all environments: ✅ Production, Preview, Development
5. Click "Add"
6. Repeat for each variable

## Important Notes

- Replace `YOUR_PASSWORD` in DATABASE_URL with your actual Supabase database password
- Get the full CONNECTION_URL from Supabase: Settings → Database → Direct connection string
- Copy the entire URL (it includes the password)
- Don't modify any other values
- Make sure each variable is set for ALL environments
