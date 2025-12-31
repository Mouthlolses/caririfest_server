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
    fun handleInvalidCredentials(ex: InvalidCredentialsException) =
        ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(message = ex.message ?: "Credenciais inválidas"))

    @ExceptionHandler(UserBlockedException::class)
    fun handleUserBlocked(ex: UserBlockedException) =
        ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(ErrorResponse(message = ex.message ?: "Usuário bloqueado"))

    // Novo handler para erros 400 (como email mismatch)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(ex: IllegalArgumentException) =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(message = ex.message ?: "Requisição inválida"))

    // Handler genérico seguro
    @ExceptionHandler(Exception::class)
    fun handleGeneric(ex: Exception) =
        ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(message = "Erro interno no servidor"))
}

data class ErrorResponse(val message: String)