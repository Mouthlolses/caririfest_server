package com.caririfestserver.caririfest_api.exceptions.domainExceptions

class InvalidCredentialsException : RuntimeException("E-mail ou senha inválidos")
class UserBlockedException : RuntimeException("Usuário bloqueado")