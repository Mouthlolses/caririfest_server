package com.caririfestserver.caririfest_api.exceptions.domainExceptions

class InvalidTokenException : RuntimeException("Token inválido")
class TokenExpiredException : RuntimeException("Token expirado")