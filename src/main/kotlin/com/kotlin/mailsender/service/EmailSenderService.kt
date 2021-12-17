package com.kotlin.mailsender.service

import com.kotlin.mailsender.dto.EmailRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailSenderService(private val emailSender: JavaMailSender) {

    @Value("\${spring.mail.username}")
    lateinit var username: String

    @Value("\${front.end.url}")
    lateinit var url: String

    fun sendEmail(request: EmailRequest): String {
        val message = SimpleMailMessage()
        val formattedMessage = formatMessage(request.email!!, request.name!!, request.message!!)

        message.setSubject("${request.name}: $url")
        message.setText(formattedMessage)
        message.setTo(username)

        emailSender.send(message)

        return formattedMessage
    }

    private fun formatMessage(email: String, name: String, text: String) =
        "\nFrom: $email \nDate: ${java.util.Date(System.currentTimeMillis())} \nName: $name \nMessage: $text"
}
