package com.caririfestserver.caririfest_api.annotations


@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class NotEmpty(val message: String = "Campo obrigátio")