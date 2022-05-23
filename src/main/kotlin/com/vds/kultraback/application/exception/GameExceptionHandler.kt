package com.vds.kultraback.application.exception

import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GameExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleTypeMismatch(
        ex: TypeMismatchException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val response = ExceptionResponse(
            errorCode = ex.errorCode,
            cause = ex.mostSpecificCause.toString(),
            className = ex.javaClass.simpleName,
            statusCode = status.value(),
            statusName = status.name
        )
        return handleExceptionInternal(ex, response, headers, status, request)
    }
}