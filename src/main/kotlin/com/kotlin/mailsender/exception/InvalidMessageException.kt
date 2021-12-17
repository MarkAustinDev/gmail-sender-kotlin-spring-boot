package com.kotlin.mailsender.exception

class InvalidMessageException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}
