import nodemailer from 'nodemailer';

class BrevoEmailService {
  constructor() {
    this.apiKey = (process.env.BREVO_API_KEY || '').trim();
    this.fromEmail = (process.env.BREVO_FROM_EMAIL || '').trim();
    this.fromName = process.env.BREVO_FROM_NAME || 'SOVS System';
    this.apiBaseUrl = (process.env.BREVO_API_BASE_URL || 'https://api.brevo.com/v3').trim();
    this.smtpLogin = (process.env.BREVO_SMTP_LOGIN || this.fromEmail).trim();
    this.smtpPort = Number(process.env.BREVO_SMTP_PORT || 587);
    this.smtpSecure = String(process.env.BREVO_SMTP_SECURE || 'false').toLowerCase() === 'true';

    this.transporter = nodemailer.createTransport({
      host: 'smtp-relay.brevo.com',
      port: this.smtpPort,
      secure: this.smtpSecure,
      auth: {
        user: this.smtpLogin || '',
        pass: this.apiKey || ''
      },
      connectionTimeout: 10000,
      greetingTimeout: 10000,
      socketTimeout: 15000
    });

    this.lastError = null;
    this.isConfigured = this.validateConfiguration();
  }

  validateConfiguration() {
    const apiKey = this.apiKey;
    const fromEmail = this.fromEmail;

    const isValid = apiKey.length > 0 &&
      fromEmail.length > 0 &&
      !apiKey.toLowerCase().includes('placeholder') &&
      !fromEmail.toLowerCase().includes('placeholder');

    if (!isValid) {
      this.lastError = 'Brevo configuration is missing required environment variables.';
      console.warn('WARN Brevo email service not properly configured.');
      console.warn('     Set BREVO_API_KEY and BREVO_FROM_EMAIL environment variables.');
    } else {
      this.lastError = null;
    }

    return isValid;
  }

  async sendViaHttpApi(recipientEmail, subject, htmlBody) {
    const endpoint = `${this.apiBaseUrl}/smtp/email`;
    const controller = new AbortController();
    const timeoutId = setTimeout(() => controller.abort(), 15000);

    try {
      const response = await fetch(endpoint, {
        method: 'POST',
        headers: {
          accept: 'application/json',
          'api-key': this.apiKey,
          'content-type': 'application/json'
        },
        body: JSON.stringify({
          sender: {
            name: this.fromName,
            email: this.fromEmail
          },
          to: [{ email: recipientEmail }],
          subject,
          htmlContent: htmlBody
        }),
        signal: controller.signal
      });

      if (!response.ok) {
        const responseText = await response.text();
        const details = responseText.slice(0, 300);
        const error = new Error(`Brevo API ${response.status}: ${details}`);
        error.status = response.status;
        throw error;
      }

      return true;
    } finally {
      clearTimeout(timeoutId);
    }
  }

  async sendVerificationCode(recipientEmail, code, type = 'registration') {
    if (!this.isConfigured) {
      this.lastError = 'Brevo not configured.';
      console.error('ERROR Brevo email service not configured. Cannot send email.');
      return false;
    }

    const normalizedEmail = (recipientEmail || '').trim();
    if (!normalizedEmail) {
      this.lastError = 'Recipient email is empty.';
      console.error('ERROR Invalid recipient email');
      return false;
    }

    const maxRetries = 3;
    const retryDelayMs = 1000;

    for (let attempt = 1; attempt <= maxRetries; attempt++) {
      const { subject, body } = this.getEmailTemplate(code, type);

      try {
        await this.sendViaHttpApi(normalizedEmail, subject, body);
        this.lastError = null;
        console.log(`OK Verification email sent to ${normalizedEmail} via Brevo API`);
        return true;
      } catch (apiError) {
        this.lastError = `Brevo API error: ${apiError.message}`;
        console.warn(`WARN Brevo API attempt ${attempt}/${maxRetries} failed: ${apiError.message}`);
      }

      try {
        const mailOptions = {
          from: `${this.fromName} <${this.fromEmail}>`,
          to: normalizedEmail,
          subject,
          html: body
        };

        const info = await this.transporter.sendMail(mailOptions);
        this.lastError = null;
        console.log(`OK Verification email sent to ${normalizedEmail} via Brevo SMTP`);
        console.log(`   Message ID: ${info.messageId}`);
        return true;
      } catch (smtpError) {
        this.lastError = `Brevo SMTP error: ${smtpError.message}`;
        console.warn(`WARN Brevo SMTP fallback attempt ${attempt}/${maxRetries} failed: ${smtpError.message}`);

        if (smtpError.message?.includes('authentication') || smtpError.message?.includes('Invalid credentials')) {
          console.error('ERROR Brevo SMTP authentication failed.');
          console.error('      Check BREVO_API_KEY and BREVO_SMTP_LOGIN/BREVO_FROM_EMAIL settings.');
          return false;
        }
      }

      if (attempt < maxRetries) {
        const delayMs = retryDelayMs * attempt;
        console.log(`INFO Retrying in ${delayMs}ms...`);
        await new Promise((resolve) => setTimeout(resolve, delayMs));
      } else {
        if (!this.lastError) {
          this.lastError = 'Brevo email delivery failed after all retries.';
        }
        console.error('ERROR Failed to send verification email after all retries.');
        return false;
      }
    }

    if (!this.lastError) {
      this.lastError = 'Brevo email delivery failed.';
    }
    return false;
  }

  getLastError() {
    return this.lastError;
  }

  getEmailTemplate(code, type) {
    const templates = {
      registration: {
        subject: 'SOVS - Email Verification Code',
        body: `
          <div style="font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px;">
            <div style="background-color: white; padding: 30px; border-radius: 8px; max-width: 500px; margin: 0 auto;">
              <h2 style="color: #2c3e50; text-align: center;">Welcome to SOVS!</h2>
              <p style="color: #555; text-align: center; font-size: 16px;">
                Your email verification code is:
              </p>
              <div style="background-color: #ecf0f1; padding: 20px; border-radius: 5px; text-align: center; margin: 20px 0;">
                <h1 style="color: #3498db; letter-spacing: 5px; margin: 0;">${code}</h1>
              </div>
              <p style="color: #999; text-align: center; font-size: 12px;">
                This code is valid for 10 minutes. Please do not share this code with anyone.
              </p>
              <p style="color: #999; text-align: center; font-size: 12px;">
                If you did not register for SOVS, please ignore this email.
              </p>
            </div>
          </div>
        `
      },
      login: {
        subject: 'SOVS - Your 2FA Login Code',
        body: `
          <div style="font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px;">
            <div style="background-color: white; padding: 30px; border-radius: 8px; max-width: 500px; margin: 0 auto;">
              <h2 style="color: #2c3e50; text-align: center;">SOVS Login Code</h2>
              <p style="color: #555; text-align: center; font-size: 16px;">
                Your login verification code is:
              </p>
              <div style="background-color: #ecf0f1; padding: 20px; border-radius: 5px; text-align: center; margin: 20px 0;">
                <h1 style="color: #27ae60; letter-spacing: 5px; margin: 0;">${code}</h1>
              </div>
              <p style="color: #999; text-align: center; font-size: 12px;">
                This code is valid for 10 minutes and is for your security.
              </p>
              <p style="color: #999; text-align: center; font-size: 12px;">
                If you did not attempt to login, please secure your account immediately.
              </p>
            </div>
          </div>
        `
      },
      password_reset: {
        subject: 'SOVS - Password Reset Code',
        body: `
          <div style="font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px;">
            <div style="background-color: white; padding: 30px; border-radius: 8px; max-width: 500px; margin: 0 auto;">
              <h2 style="color: #2c3e50; text-align: center;">SOVS Password Reset</h2>
              <p style="color: #555; text-align: center; font-size: 16px;">
                Your password reset code is:
              </p>
              <div style="background-color: #ecf0f1; padding: 20px; border-radius: 5px; text-align: center; margin: 20px 0;">
                <h1 style="color: #e74c3c; letter-spacing: 5px; margin: 0;">${code}</h1>
              </div>
              <p style="color: #999; text-align: center; font-size: 12px;">
                This code is valid for 30 minutes.
              </p>
              <p style="color: #999; text-align: center; font-size: 12px;">
                If you did not request a password reset, please ignore this email.
              </p>
            </div>
          </div>
        `
      }
    };

    return templates[type] || templates.registration;
  }
}

export default new BrevoEmailService();
