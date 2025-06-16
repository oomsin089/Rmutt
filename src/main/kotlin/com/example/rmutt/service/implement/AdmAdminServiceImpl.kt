package com.example.rmutt.service.implement

import com.example.rmutt.entities.AdmAdmin
import com.example.rmutt.entities.AdmUser
import com.example.rmutt.repository.AdmAdminRepository
import com.example.rmutt.repository.AdmUserRepository
import com.example.rmutt.service.AdmAdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdmAdminServiceImpl: AdmAdminService {

    @Autowired
    lateinit var admAdminRepository: AdmAdminRepository

    override fun login(emailAddress: String, passWord: String): AdmAdmin? {
        try {

            val admin = admAdminRepository.findByEmailAddress(emailAddress)
            return if (admin.passWord == passWord) admin else null
        }catch (ex:Exception){
            throw ex
        }
    }
}