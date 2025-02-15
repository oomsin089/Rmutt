package com.example.rmutt.service

import com.example.rmutt.dto.AdmUserDTO
import com.example.rmutt.entities.AdmUser

interface AdmUserService {
    fun login(emailAddress: String, passWord: String): AdmUser?
    fun register(body: AdmUserDTO): AdmUser
}