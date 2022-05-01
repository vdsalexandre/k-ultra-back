package com.vds.kultraback.adapters.exceptions

import com.vds.kultraback.utils.Util.emptyGame
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GameExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(
        value = [
            MethodArgumentTypeMismatchException::class,
            IllegalStateException::class,
            IllegalArgumentException::class
        ]
    )
    fun exceptionHandler(exception: Exception, request: WebRequest): ResponseEntity<Any> {
        val body = when (exception) {
            is MethodArgumentTypeMismatchException -> emptyGame
            else -> "Error in request"
        }
        return handleExceptionInternal(exception, body, HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }
}