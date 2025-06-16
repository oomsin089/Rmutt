package com.example.rmutt.service.implement

import com.example.rmutt.dto.AdmUserDTO
import com.example.rmutt.entities.AdmUser
import com.example.rmutt.entities.Complaint
import com.example.rmutt.repository.AdmUserRepository
import com.example.rmutt.service.AdmUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdmUserServiceImpl: AdmUserService {

    @Autowired
    lateinit var admUserRepository: AdmUserRepository

    override fun login(emailAddress: String, passWord: String): AdmUser? {
        val user = admUserRepository.findByEmailAddress(emailAddress) ?: return null

        val passwordEncoder = BCryptPasswordEncoder()
        return if (passwordEncoder.matches(passWord, user.passWord)) {
            user
        } else {
            null
        }
    }

    override fun register(body: AdmUserDTO): AdmUser {
        // เช็คก่อนว่ามี email นี้ในระบบหรือยัง
        if (admUserRepository.existsByEmailAddress(body.emailAddress.toString())) {
            throw RuntimeException("Email address ${body.emailAddress} is already registered")
        }

        val passwordEncoder = BCryptPasswordEncoder()
        val hashedPassword = passwordEncoder.encode(body.passWord) // แฮชรหัสผ่าน

        val user = AdmUser(
            emailAddress = body.emailAddress,
            passWord = hashedPassword, // ใช้รหัสผ่านที่ถูกแฮช
            firstName = body.firstName,
            lastName = body.lastName,
            fullName = "${body.firstName} ${body.lastName}",
            gender = body.gender,
            typePersonal = body.typePersonal,
            rank = body.rank
        )
        return admUserRepository.save(user)
    }
    override fun getAll(): List<AdmUser>{
        return admUserRepository.findAll()
    }
}