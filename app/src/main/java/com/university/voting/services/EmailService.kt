package com.university.voting.services

import android.util.Log
import com.university.voting.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Properties
import javax.mail.AuthenticationFailedException
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmailService {

    suspend fun sendVerificationCode(recipientEmail: String, code: String, type: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val sender = BuildConfig.SMTP_SENDER_EMAIL.trim()
                val senderPassword = BuildConfig.SMTP_APP_PASSWORD.filterNot { it.isWhitespace() }

                if (isInvalidSmtpValue(sender)) {
                    Log.e(TAG, "SMTP_SENDER_EMAIL is missing. Configure it in local.properties or environment variables.")
                    return@withContext false
                }

                if (isInvalidSmtpValue(senderPassword)) {
                    Log.e(TAG, "SMTP_APP_PASSWORD is missing. Configure it in local.properties or environment variables.")
                    return@withContext false
                }

                val properties = Properties().apply {
                    put("mail.transport.protocol", "smtp")
                    put("mail.smtp.host", "smtp.gmail.com")
                    put("mail.smtp.port", "587")
                    put("mail.smtp.auth", "true")
                    put("mail.smtp.starttls.enable", "true")
                    put("mail.smtp.starttls.required", "true")
                    put("mail.smtp.ssl.protocols", "TLSv1.2")
                    put("mail.smtp.connectiontimeout", "10000")
                    put("mail.smtp.timeout", "10000")
                    put("mail.smtp.writetimeout", "10000")
                }

                val session = Session.getInstance(properties, object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(sender, senderPassword)
                    }
                })

                val subject = when (type) {
                    "registration" -> "SOVS - Email Verification Code"
                    "login" -> "SOVS - Login Verification Code"
                    "password_reset" -> "SOVS - Password Reset Code"
                    else -> "SOVS - Verification Code"
                }

                val body = when (type) {
                    "registration" -> """
                        Welcome to SOVS!
                        
                        Your email verification code is: $code
                        
                        This code is valid for 10 minutes. Please do not share this code with anyone.
                        
                        If you did not register for SOVS, please ignore this email.
                        
                        Best regards,
                        SOVS Team
                    """.trimIndent()
                    "login" -> """
                        Login Verification
                        
                        Your login verification code is: $code
                        
                        This code is valid for 10 minutes. Please do not share this code with anyone.
                        
                        If you did not attempt to login, please change your password immediately.
                        
                        Best regards,
                        SOVS Team
                    """.trimIndent()
                    "password_reset" -> """
                        Password Reset Request
                        
                        Your password reset code is: $code
                        
                        This code is valid for 10 minutes. Please do not share this code with anyone.
                        
                        If you did not request a password reset, please ignore this email.
                        
                        Best regards,
                        SOVS Team
                    """.trimIndent()
                    else -> "Your verification code is: $code"
                }

                val message = MimeMessage(session).apply {
                    setFrom(InternetAddress(sender))
                    setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail))
                    setSubject(subject)
                    setText(body)
                }

                Transport.send(message)
                true
            } catch (e: AuthenticationFailedException) {
                Log.e(TAG, "SMTP authentication failed. Verify SMTP_SENDER_EMAIL and SMTP_APP_PASSWORD.", e)
                false
            } catch (e: Exception) {
                Log.e(TAG, "Failed to send verification email to $recipientEmail", e)
                false
            }
        }
    }

    companion object {
        private const val TAG = "EmailService"
        const val REGISTRATION = "registration"
        const val LOGIN = "login"
        const val PASSWORD_RESET = "password_reset"
    }

    private fun isInvalidSmtpValue(value: String): Boolean {
        val normalized = value.trim()
        return normalized.isBlank() || normalized.contains("placeholder", ignoreCase = true)
    }
}
