package com.example.rmutt.controller

import com.example.rmutt.dto.AdmUserDTO
import com.example.rmutt.entities.AdmUser
import com.example.rmutt.entities.Complaint
import com.example.rmutt.service.AdmUserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AdmUserController {

    @Autowired
    lateinit var admUserService: AdmUserService

    @PostMapping("/login")
    fun login(request: HttpServletRequest, @RequestBody admUserDTO: AdmUserDTO): ResponseEntity<String> {
        val user = admUserService.login(admUserDTO.emailAddress.toString(),admUserDTO.passWord.toString())
        return if (user != null) {
            request.session.setAttribute("user", user)  // เก็บ user ไว้ใน session
            ResponseEntity.ok("Login successful")
        } else {
            ResponseEntity.status(401).body("Invalid username or password")
        }
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<String> {
        request.session.invalidate()  // ล้างข้อมูลทั้งหมดใน session
        return ResponseEntity.ok("Logout successful")
    }

    @GetMapping("/profile")
    fun getProfile(request: HttpServletRequest): ResponseEntity<Any> {
        val user = request.session.getAttribute("user") as? AdmUser
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.status(401).body("Not logged in")
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody admUserDTO: AdmUserDTO): ResponseEntity<String> {
        return try {
            val user = admUserService.register(admUserDTO)
            ResponseEntity.ok("Registration successful for user: ${user.emailAddress}")
        } catch (e: Exception) {
            ResponseEntity.status(500).body("Error registering user: ${e.message}")
        }
    }
    @GetMapping("/getAllUser")
    fun getAllComplaints(): List<AdmUser>{
        return admUserService.getAll()
    }
}