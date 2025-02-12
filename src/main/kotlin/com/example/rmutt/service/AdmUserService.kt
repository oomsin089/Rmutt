package com.example.rmutt.service

import com.example.rmutt.entities.AdmUser

interface AdmUserService {
    fun login(emailAddress: String, passWord: String): AdmUser?
    fun register(emailAddress: String, passWord: String): AdmUser
}