package com.example.rmutt.service.implement

import com.example.rmutt.dto.AdmUserDTO
import com.example.rmutt.entities.AdmUser
import com.example.rmutt.entities.Complaint
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

    override fun register(body: AdmUserDTO): AdmUser {
        // เช็คก่อนว่ามี email นี้ในระบบหรือยัง
        if (admUserRepository.existsByEmailAddress(body.emailAddress.toString())) {
            throw RuntimeException("Email address ${body.emailAddress} is already registered")
        }

        val user = AdmUser(
            emailAddress = body.emailAddress,
            passWord = body.passWord,
            firstName = body.firstName,
            lastName = body.lastName,
            fullName = body.firstName + " " + body.lastName,
            title = body.title,
            typePersonal = body.typePersonal
        )
        return admUserRepository.save(user)
    }
    override fun getAll(): List<AdmUser>{
        return admUserRepository.findAll()
    }
}