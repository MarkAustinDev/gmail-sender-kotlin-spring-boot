package com.kotlin.mailsender.controller

import com.kotlin.mailsender.dto.EmailRequest
import com.kotlin.mailsender.service.EmailSenderService
import com.kotlin.mailsender.exception.InvalidMessageException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/email")
class MailController(private val emailSenderService: EmailSenderService) {

    @PostMapping
    fun sendEmail(@RequestBody request: EmailRequest): ResponseEntity<String> {

        return if (isValidMessage(request)) {
            ResponseEntity.accepted().body(emailSenderService.sendEmail(request))
        } else {
            throw InvalidMessageException("Request is missing data. Cannot send message.")
        }
    }

    private fun isValidMessage(request: EmailRequest) = !request.name.isNullOrEmpty() && !request.email.isNullOrEmpty()
            && !request.message.isNullOrEmpty()
}
