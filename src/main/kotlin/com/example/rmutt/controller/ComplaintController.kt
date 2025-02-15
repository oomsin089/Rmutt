package com.example.rmutt.controller

import com.example.rmutt.dto.ComplaintDTO
import com.example.rmutt.entities.AdmUser
import com.example.rmutt.entities.Complaint
import com.example.rmutt.service.AdmUserService
import com.example.rmutt.service.ComplaintService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class ComplaintController {

    @Autowired
    lateinit var complaintService: ComplaintService

    @PostMapping("/add")
    fun add(@RequestBody complaint: ComplaintDTO): String {
        return complaintService.addComplaint(complaint)
    }

    @GetMapping("/email")
    fun getComplaintsByEmail(@RequestParam emailAddress: String): ResponseEntity<List<ComplaintDTO>> {
        val complaints = complaintService.getComplaintsByEmail(emailAddress)
        return if (complaints.isNotEmpty()) ResponseEntity.ok(complaints) else ResponseEntity.notFound().build()
    }


}