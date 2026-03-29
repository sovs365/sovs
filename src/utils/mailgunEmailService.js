import FormData from 'form-data';
import Mailgun from 'mailgun.js';

class MailgunEmailService {
  constructor() {
    this.mailgun = new Mailgun(FormData);
    this.mg = this.mailgun.client({
      username: 'api',
      key: process.env.MAILGUN_API_KEY || ''
    });
    
    this.domain = process.env.MAILGUN_DOMAIN;
    this.fromEmail = process.env.MAILGUN_FROM_EMAIL;
    this.isConfigured = this.validateConfiguration();
  }

  validateConfiguration() {
    const apiKey = (process.env.MAILGUN_API_KEY || '').trim();
    const domain = (process.env.MAILGUN_DOMAIN || '').trim();
    const fromEmail = (process.env.MAILGUN_FROM_EMAIL || '').trim();
    
    const isValid = apiKey.length > 0 && 
                   domain.length > 0 && 
                   fromEmail.length > 0 &&
                   !apiKey.toLowerCase().includes('placeholder') &&
                   !domain.toLowerCase().includes('placeholder') &&
                   !fromEmail.toLowerCase().includes('placeholder');
    
    if (!isValid) {
      console.warn('⚠️  Mailgun email service not properly configured.');
      console.warn('   Set MAILGUN_API_KEY, MAILGUN_DOMAIN, and MAILGUN_FROM_EMAIL environment variables.');
    }
    
    return isValid;
  }

  async sendVerificationCode(recipientEmail, code, type = 'registration') {
    if (!this.isConfigured) {
      console.error('❌ Mailgun email service not configured. Cannot send email.');
      return false;
    }

    const maxRetries = 3;
    const retryDelayMs = 1000;

    for (let attempt = 1; attempt <= maxRetries; attempt++) {
      try {
        const normalizedEmail = (recipientEmail || '').trim();
        
        if (!normalizedEmail) {
          console.error('❌ Invalid recipient email');
          return false;
        }

        const { subject, body } = this.getEmailTemplate(code, type);

        const mailData = {
          from: this.fromEmail,
          to: normalizedEmail,
          subject: subject,
          html: body
        };

        const result = await this.mg.messages.create(this.domain, mailData);
        
        console.log(`✅ Verification email sent to ${normalizedEmail}`);
        console.log(`   Message ID: ${result.id}`);
        return true;

      } catch (error) {
        console.warn(`⚠️  Email send attempt ${attempt}/${maxRetries} failed: ${error.message}`);
        
        if (error.response?.status === 401 || error.message?.includes('Unauthorized')) {
          console.error('❌ Mailgun authentication failed.');
          console.error(`   API Key: ${process.env.MAILGUN_API_KEY?.substring(0, 10)}...`);
          console.error(`   Domain: ${this.domain}`);
          return false;
        }

        // For transient errors, retry with exponential backoff
        if (attempt < maxRetries) {
          const delayMs = retryDelayMs * attempt;
          console.log(`⏳ Retrying in ${delayMs}ms...`);
          await new Promise(resolve => setTimeout(resolve, delayMs));
        } else {
          console.error('❌ Failed to send verification email after all retries.');
          return false;
        }
      }
    }

    return false;
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
      reset: {
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

export default new MailgunEmailService();
