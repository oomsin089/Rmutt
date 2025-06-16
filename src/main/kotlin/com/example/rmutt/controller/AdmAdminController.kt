package com.example.rmutt.controller

import com.example.rmutt.dto.AdmUserDTO
import com.example.rmutt.entities.AdmAdmin
import com.example.rmutt.entities.AdmUser
import com.example.rmutt.service.AdmAdminService
import com.example.rmutt.service.AdmUserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AdmAdminController {

    @Autowired
    lateinit var admAdminService: AdmAdminService

    @PostMapping("/loginAdmin")
    fun login(request: HttpServletRequest, @RequestBody admUserDTO: AdmUserDTO): ResponseEntity<String> {
        val admin = admAdminService.login(admUserDTO.emailAddress.toString(),admUserDTO.passWord.toString())
        return if (admin != null) {
            request.session.setAttribute("admin", admin)  // เก็บ user ไว้ใน session
            ResponseEntity.ok("Login successful")
        } else {
            ResponseEntity.status(401).body("Invalid username or password")
        }
    }
    @GetMapping("/profileAdmin")
    fun getProfile(request: HttpServletRequest): ResponseEntity<Any> {
        val admin = request.session.getAttribute("admin") as? AdmAdmin
        return if (admin != null) {
            ResponseEntity.ok(admin)
        } else {
            ResponseEntity.status(401).body("Not logged in")
        }
    }
}