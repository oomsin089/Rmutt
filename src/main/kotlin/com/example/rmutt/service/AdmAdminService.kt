package com.example.rmutt.service

import com.example.rmutt.entities.AdmAdmin
import com.example.rmutt.entities.AdmUser

interface AdmAdminService {
    fun login(emailAddress: String, passWord: String): AdmAdmin?

}