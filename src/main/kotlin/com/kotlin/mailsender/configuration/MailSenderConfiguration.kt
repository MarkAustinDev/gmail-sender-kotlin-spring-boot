package com.kotlin.mailsender.configuration

import java.util.Properties
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class MailSenderConfiguration {

    @Value("\${spring.mail.host}")
    lateinit var host: String

    @Value("\${spring.mail.port}")
    lateinit var port: String

    @Value("\${spring.mail.username}")
    lateinit var username: String

    @Value("\${spring.mail.password}")
    lateinit var password: String

    @Value("\${spring.mail.protocol}")
    lateinit var protocol: String

    @Value("\${spring.mail.protocol}")
    lateinit var auth: String

    @Value("\${spring.mail.starttls}")
    lateinit var startTls: String

    @Value("\${spring.mail.debug}")
    lateinit var debug: String

    @Bean
    fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = host
        mailSender.port = port.toInt()
        mailSender.username = username
        mailSender.password = password
        configureJavaMailProperties(mailSender.javaMailProperties)
        return mailSender
    }

    private fun configureJavaMailProperties(properties: Properties) {
        properties["mail.transport.protocol"] = protocol
        properties["mail.smtp.auth"] = auth
        properties["mail.smtp.starttls.enable"] = startTls
        properties["mail.debug"] = debug
    }
}
