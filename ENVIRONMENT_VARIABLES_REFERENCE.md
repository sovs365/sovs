# Render Environment Variables (backend-node)

This backend now supports multiple email providers.
At least one email provider must be configured for registration/login/password-reset codes to send.

## Core required variables

1. `DATABASE_URL`
2. `JWT_SECRET`
3. `JWT_ISSUER`
4. `JWT_AUDIENCE`
5. `JWT_EXPIRATION_MS`
6. `NODE_ENV`
7. `APP_ENV`
8. `PORT`
9. `CORS_ALLOWED_HOSTS`

## Email provider variables (choose at least one provider)

### Option A (recommended): Brevo SMTP
10. `BREVO_API_KEY`
11. `BREVO_FROM_EMAIL`
12. `BREVO_FROM_NAME` (optional, defaults to `SOVS System`)
13. `BREVO_SMTP_LOGIN` (optional, defaults to `BREVO_FROM_EMAIL`)

### Option B: Gmail/SMTP fallback
10. `SMTP_SENDER_EMAIL`
11. `SMTP_APP_PASSWORD`

### Option C: Mailgun fallback
10. `MAILGUN_API_KEY`
11. `MAILGUN_DOMAIN`
12. `MAILGUN_FROM_EMAIL`

## Example production values

```env
NODE_ENV=production
APP_ENV=production
PORT=3000
CORS_ALLOWED_HOSTS=*
JWT_ISSUER=university-voting-app
JWT_AUDIENCE=university-voting-users
JWT_EXPIRATION_MS=86400000
```

## Quick verification after deploy

1. Open `GET /api/auth/health-email`.
2. Confirm `diagnosis.hasAnyConfiguredProvider` is `true`.
3. Confirm `diagnosis.activeProviders` contains at least one provider.
