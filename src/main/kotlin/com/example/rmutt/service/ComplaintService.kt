package com.example.rmutt.service

import com.example.rmutt.dto.ComplaintActivityDTO
import com.example.rmutt.dto.ComplaintDTO
import com.example.rmutt.dto.ComplaintStatusDTO
import com.example.rmutt.dto.ResponseComplaintDTO
import com.example.rmutt.entities.Complaint
import org.springframework.http.ResponseEntity

interface ComplaintService {

    fun addComplaint(body: ComplaintDTO): String

    fun getComplaintsByEmail(emailAddress: String): List<ComplaintDTO>

    fun getComplaintById(id: Int): ResponseEntity<ResponseComplaintDTO<ComplaintStatusDTO>>

    fun getAll(): List<Complaint>

    fun updateManage(problemId: Int, updateRequest: ComplaintActivityDTO): String?
}