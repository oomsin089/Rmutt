package com.example.rmutt.dto

data class ResponseComplaintDTO<T>(
    var success: Boolean,
    var message: String,
    var data: T? = null,
    var error: String? = null,
)
