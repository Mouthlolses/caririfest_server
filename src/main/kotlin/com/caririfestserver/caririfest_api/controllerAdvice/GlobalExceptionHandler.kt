package com.caririfestserver.caririfest_api.controllerAdvice

import com.caririfestserver.caririfest_api.exceptions.domainExceptions.InvalidCredentialsException
import com.caririfestserver.caririfest_api.exceptions.domainExceptions.UserBlockedException
import com.caririfestserver.caririfest_api.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCredentials(ex: InvalidCredentialsException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(message = ex.message!!))
    }

    @ExceptionHandler(UserBlockedException::class)
    fun handleUserBlocked(ex: UserBlockedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(ErrorResponse(message = ex.message!!))
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneric(ex: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(message = "Erro interno no servidor"))
    }
}