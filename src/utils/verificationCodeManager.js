/**
 * In-memory verification code manager
 * Stores verification codes with expiration (10 minutes)
 * In production with multiple servers, use Redis instead
 */

class VerificationCodeManager {
  constructor() {
    this.codes = new Map(); // Key: `${email}:${type}`, Value: { code, expiresAt }
    this.verifiedRegistrations = new Map(); // Key: normalized email, Value: expiresAt
    this.CODE_EXPIRY_MS = 10 * 60 * 1000; // 10 minutes
    this.VERIFIED_REGISTRATION_EXPIRY_MS = 30 * 60 * 1000; // 30 minutes
    
    // Cleanup expired codes every 5 minutes
    setInterval(() => this.cleanupExpiredCodes(), 5 * 60 * 1000);
  }

  normalizeEmail(email) {
    return (email || '').trim().toLowerCase();
  }

  /**
   * Store a verification code
   */
  storeCode(email, code, type = 'registration') {
    const normalizedEmail = this.normalizeEmail(email);
    const sanitizedCode = (code || '').toString().trim();

    if (!normalizedEmail || !sanitizedCode) {
      return false;
    }

    const key = `${normalizedEmail}:${type}`;
    this.codes.set(key, {
      code: sanitizedCode,
      expiresAt: Date.now() + this.CODE_EXPIRY_MS
    });
    return true;
  }

  /**
   * Retrieve and verify a code
   */
  verifyCode(email, code, type = 'registration') {
    const normalizedEmail = this.normalizeEmail(email);
    const sanitizedCode = (code || '').toString().trim();

    if (!normalizedEmail || !sanitizedCode) {
      return false;
    }

    const key = `${normalizedEmail}:${type}`;
    const stored = this.codes.get(key);

    if (!stored) {
      return false;
    }

    // Check if expired
    if (Date.now() > stored.expiresAt) {
      this.codes.delete(key);
      return false;
    }

    // Check if code matches
    return stored.code === sanitizedCode;
  }

  /**
   * Get remaining time for a code (in seconds)
   */
  getTimeRemaining(email, type = 'registration') {
    const normalizedEmail = this.normalizeEmail(email);
    if (!normalizedEmail) {
      return 0;
    }

    const key = `${normalizedEmail}:${type}`;
    const stored = this.codes.get(key);

    if (!stored) {
      return 0;
    }

    const remaining = Math.max(0, stored.expiresAt - Date.now());
    return Math.ceil(remaining / 1000);
  }

  /**
   * Delete a code after successful verification
   */
  deleteCode(email, type = 'registration') {
    const normalizedEmail = this.normalizeEmail(email);
    if (!normalizedEmail) {
      return;
    }

    const key = `${normalizedEmail}:${type}`;
    this.codes.delete(key);
  }

  markRegistrationVerified(email) {
    const normalizedEmail = this.normalizeEmail(email);
    if (!normalizedEmail) {
      return;
    }

    this.verifiedRegistrations.set(
      normalizedEmail,
      Date.now() + this.VERIFIED_REGISTRATION_EXPIRY_MS
    );
  }

  isRegistrationVerified(email) {
    const normalizedEmail = this.normalizeEmail(email);
    if (!normalizedEmail) {
      return false;
    }

    const expiresAt = this.verifiedRegistrations.get(normalizedEmail);
    if (!expiresAt) {
      return false;
    }

    if (Date.now() > expiresAt) {
      this.verifiedRegistrations.delete(normalizedEmail);
      return false;
    }

    return true;
  }

  clearRegistrationVerified(email) {
    const normalizedEmail = this.normalizeEmail(email);
    if (!normalizedEmail) {
      return;
    }

    this.verifiedRegistrations.delete(normalizedEmail);
  }

  /**
   * Clean up expired codes
   */
  cleanupExpiredCodes() {
    const now = Date.now();
    for (const [key, value] of this.codes.entries()) {
      if (now > value.expiresAt) {
        this.codes.delete(key);
      }
    }

    for (const [email, expiresAt] of this.verifiedRegistrations.entries()) {
      if (now > expiresAt) {
        this.verifiedRegistrations.delete(email);
      }
    }
  }

  /**
   * Clear all codes for testing
   */
  clearAll() {
    this.codes.clear();
    this.verifiedRegistrations.clear();
  }
}

export default new VerificationCodeManager();
