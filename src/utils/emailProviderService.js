import brevoEmailService from './brevoEmailService.js';
import emailService from './emailService.js';
import mailgunEmailService from './mailgunEmailService.js';

class EmailProviderService {
  constructor() {
    this.lastSendError = null;
    this.providers = [
      {
        name: 'brevo',
        service: brevoEmailService,
        requiredEnv: ['BREVO_API_KEY', 'BREVO_FROM_EMAIL']
      },
      {
        name: 'smtp',
        service: emailService,
        requiredEnv: ['SMTP_SENDER_EMAIL', 'SMTP_APP_PASSWORD']
      },
      {
        name: 'mailgun',
        service: mailgunEmailService,
        requiredEnv: ['MAILGUN_API_KEY', 'MAILGUN_DOMAIN', 'MAILGUN_FROM_EMAIL']
      }
    ];
  }

  getMissingEnv(requiredEnv) {
    return requiredEnv.filter((envName) => {
      const value = (process.env[envName] || '').trim();
      return !value || value.toLowerCase().includes('placeholder');
    });
  }

  getProviderStatus() {
    return this.providers.map((provider) => ({
      name: provider.name,
      configured: Boolean(provider.service?.isConfigured),
      requiredEnv: provider.requiredEnv,
      missingEnv: this.getMissingEnv(provider.requiredEnv)
    }));
  }

  getActiveProviderNames() {
    return this.getProviderStatus()
      .filter((provider) => provider.configured)
      .map((provider) => provider.name);
  }

  getConfigurationHint() {
    if (this.getActiveProviderNames().length > 0) {
      return null;
    }

    return 'No email provider configured. Set BREVO_API_KEY + BREVO_FROM_EMAIL or SMTP_SENDER_EMAIL + SMTP_APP_PASSWORD.';
  }

  getLastSendError() {
    return this.lastSendError;
  }

  async sendVerificationCode(recipientEmail, code, type = 'registration') {
    const configuredProviders = this.providers.filter((provider) => {
      return Boolean(provider.service?.isConfigured) &&
        typeof provider.service.sendVerificationCode === 'function';
    });

    if (configuredProviders.length === 0) {
      this.lastSendError = this.getConfigurationHint();
      console.error('No configured email providers available.');
      console.error(`   ${this.getConfigurationHint()}`);
      return false;
    }

    for (const provider of configuredProviders) {
      try {
        const sent = await provider.service.sendVerificationCode(recipientEmail, code, type);
        if (sent) {
          this.lastSendError = null;
          console.log(`Email delivered via provider: ${provider.name}`);
          return true;
        }

        const providerError = typeof provider.service?.getLastError === 'function'
          ? provider.service.getLastError()
          : null;
        this.lastSendError = providerError
          ? `${provider.name}: ${providerError}`
          : `${provider.name}: provider returned false`;
        console.warn(`Provider ${provider.name} failed to send verification code.`);
      } catch (error) {
        this.lastSendError = `${provider.name}: ${error.message}`;
        console.error(`Provider ${provider.name} error: ${error.message}`);
      }
    }

    if (!this.lastSendError) {
      this.lastSendError = 'All configured email providers failed to send verification code.';
    }
    console.error('All configured email providers failed to send verification code.');
    return false;
  }
}

export default new EmailProviderService();
