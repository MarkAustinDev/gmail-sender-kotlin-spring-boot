package com.kotlin.mailsender.dto

data class EmailRequest(
    val name: String?,
    val email: String?,
    val message: String?,
)
