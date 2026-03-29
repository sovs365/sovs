import nodemailer from 'nodemailer';

class EmailService {
  constructor() {
    // Initialize transporter with Gmail SMTP
    this.transporter = nodemailer.createTransport({
      service: 'gmail',
      auth: {
        user: process.env.SMTP_SENDER_EMAIL,
        pass: process.env.SMTP_APP_PASSWORD
      }
    });

    this.senderEmail = process.env.SMTP_SENDER_EMAIL;
    this.isConfigured = this.validateConfiguration();
  }

  validateConfiguration() {
    const email = (process.env.SMTP_SENDER_EMAIL || '').trim();
    const password = (process.env.SMTP_APP_PASSWORD || '').trim();
    
    const isValid = email.length > 0 && 
                   password.length > 0 && 
                   !email.toLowerCase().includes('placeholder') &&
                   !password.toLowerCase().includes('placeholder');
    
    if (!isValid) {
      console.warn('⚠️  Email service not properly configured. Set SMTP_SENDER_EMAIL and SMTP_APP_PASSWORD environment variables.');
    }
    
    return isValid;
  }

  async sendVerificationCode(recipientEmail, code, type = 'registration') {
    if (!this.isConfigured) {
      console.error('❌ Email service not configured. Cannot send email.');
      return false;
    }

    const maxRetries = 3;
    const retryDelayMs = 1000;
    const emailTimeoutMs = 5000; // 5 second timeout per attempt

    for (let attempt = 1; attempt <= maxRetries; attempt++) {
      try {
        const normalizedEmail = (recipientEmail || '').trim();
        
        if (!normalizedEmail) {
          console.error('❌ Invalid recipient email');
          return false;
        }

        const { subject, body } = this.getEmailTemplate(code, type);

        const mailOptions = {
          from: this.senderEmail,
          to: normalizedEmail,
          subject: subject,
          html: body
        };

        // Send with timeout
        const sendPromise = this.transporter.sendMail(mailOptions);
        const timeoutPromise = new Promise((_, reject) => 
          setTimeout(() => reject(new Error(`Email send timeout after ${emailTimeoutMs}ms`)), emailTimeoutMs)
        );

        const info = await Promise.race([sendPromise, timeoutPromise]);
        console.log(`✅ Verification email sent to ${normalizedEmail}. MessageId: ${info.messageId}`);
        return true;

      } catch (error) {
        console.warn(`⚠️  Email send attempt ${attempt}/${maxRetries} failed: ${error.message}`);
        console.error(`   Error Code: ${error.code}`);
        console.error(`   Error Response: ${error.response}`);
        console.error(`   More details: `, error);
        
        // If it's a configuration error, don't retry
        if (error.message?.includes('SMTP') || error.message?.includes('auth') || error.code === 'EAUTH') {
          console.error('❌ Email service authentication failed.');
          console.error(`   SMTP Sender: ${this.senderEmail}`);
          console.error(`   Verify environment variables SMTP_SENDER_EMAIL and SMTP_APP_PASSWORD are set correctly');
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
              <hr style="border: none; border-top: 1px solid #ecf0f1; margin: 20px 0;">
              <p style="color: #999; text-align: center; font-size: 11px;">
                Best regards,<br><strong>SOVS Team</strong>
              </p>
            </div>
          </div>
        `
      },
      login: {
        subject: 'SOVS - Login Verification Code',
        body: `
          <div style="font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px;">
            <div style="background-color: white; padding: 30px; border-radius: 8px; max-width: 500px; margin: 0 auto;">
              <h2 style="color: #2c3e50; text-align: center;">Login Verification</h2>
              <p style="color: #555; text-align: center; font-size: 16px;">
                Your login verification code is:
              </p>
              <div style="background-color: #ecf0f1; padding: 20px; border-radius: 5px; text-align: center; margin: 20px 0;">
                <h1 style="color: #3498db; letter-spacing: 5px; margin: 0;">${code}</h1>
              </div>
              <p style="color: #999; text-align: center; font-size: 12px;">
                This code is valid for 10 minutes. Please do not share this code with anyone.
              </p>
              <p style="color: #f39c12; text-align: center; font-size: 12px; font-weight: bold;">
                If you did not attempt to login, please change your password immediately.
              </p>
              <hr style="border: none; border-top: 1px solid #ecf0f1; margin: 20px 0;">
              <p style="color: #999; text-align: center; font-size: 11px;">
                Best regards,<br><strong>SOVS Team</strong>
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
              <h2 style="color: #2c3e50; text-align: center;">Password Reset Request</h2>
              <p style="color: #555; text-align: center; font-size: 16px;">
                Your password reset code is:
              </p>
              <div style="background-color: #ecf0f1; padding: 20px; border-radius: 5px; text-align: center; margin: 20px 0;">
                <h1 style="color: #3498db; letter-spacing: 5px; margin: 0;">${code}</h1>
              </div>
              <p style="color: #999; text-align: center; font-size: 12px;">
                This code is valid for 10 minutes. Please do not share this code with anyone.
              </p>
              <p style="color: #f39c12; text-align: center; font-size: 12px;">
                If you did not request a password reset, please ignore this email.
              </p>
              <hr style="border: none; border-top: 1px solid #ecf0f1; margin: 20px 0;">
              <p style="color: #999; text-align: center; font-size: 11px;">
                Best regards,<br><strong>SOVS Team</strong>
              </p>
            </div>
          </div>
        `
      }
    };

    return templates[type] || templates.registration;
  }
}

export default new EmailService();
