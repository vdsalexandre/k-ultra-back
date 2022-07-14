package com.vds.kultraback.exception

data class ExceptionResponse(
    var errorCode: String,
    val cause: String,
    val className: String,
    val statusCode: Int,
    val statusName: String
)
