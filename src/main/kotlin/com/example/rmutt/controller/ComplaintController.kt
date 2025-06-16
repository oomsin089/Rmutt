package com.example.rmutt.controller

import com.example.rmutt.dto.ComplaintActivityDTO
import com.example.rmutt.dto.ComplaintDTO
import com.example.rmutt.dto.ComplaintStatusDTO
import com.example.rmutt.dto.ResponseComplaintDTO
import com.example.rmutt.entities.Complaint
import com.example.rmutt.service.ComplaintService
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
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

    @GetMapping("/getById")
    fun getComplaintById(@RequestParam("id") id: Int): ResponseEntity<ResponseComplaintDTO<ComplaintStatusDTO>> {
        return complaintService.getComplaintById(id)
    }

    @GetMapping("/getAll")
    fun getAllComplaints(): List<Complaint>{
        return complaintService.getAll()
    }
    @PutMapping("/update/{id}")
    fun updateComplaint(@PathVariable id: Int, @RequestBody updateRequest: ComplaintActivityDTO): ResponseEntity<String> {
        return try {
            val result = complaintService.updateManage(id, updateRequest)
            ResponseEntity.ok("อัปเดตข้อมูลสำเร็จ. ID: $result")
        } catch (e: EntityNotFoundException) {
            // เมื่อไม่พบข้อมูล complaint ตามที่ id
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("ไม่พบข้อมูลปัญหาที่ต้องการอัปเดต: ${e.message}")
        } catch (e: Exception) {
            // จับข้อผิดพลาดทั่วไป
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("เกิดข้อผิดพลาดในการอัปเดตข้อมูล: ${e.message}")
        }
    }


}