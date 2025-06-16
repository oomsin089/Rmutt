package com.example.rmutt.dto

data class AdmUserDTO(
    val emailAddress: String? = null,
    val passWord: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val fullName: String? = null,
    val gender: String? = null,
    val typePersonal: String? = null,
    val rank: String? = null
)
