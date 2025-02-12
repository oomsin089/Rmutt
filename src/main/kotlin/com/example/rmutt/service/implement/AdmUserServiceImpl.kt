package com.example.rmutt.service.implement

import com.example.rmutt.entities.AdmUser
import com.example.rmutt.repository.AdmUserRepository
import com.example.rmutt.service.AdmUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdmUserServiceImpl: AdmUserService {

    @Autowired
    lateinit var admUserRepository: AdmUserRepository

    override fun login(emailAddress: String, passWord: String): AdmUser? {
       try {

           val user = admUserRepository.findByEmailAddress(emailAddress)
           return if (user.passWord == passWord) user else null
       }catch (ex:Exception){
       throw ex
       }
    }

    override fun register(emailAddress: String, passWord: String): AdmUser {
        // เช็คก่อนว่ามี email นี้ในระบบหรือยัง
        if (admUserRepository.existsByEmailAddress(emailAddress)) {
            throw RuntimeException("Email address $emailAddress is already registered")
        }

        val user = AdmUser(emailAddress = emailAddress, passWord = passWord)
        return admUserRepository.save(user)
    }
}