package com.example.rmutt.service

import com.example.rmutt.dto.ComplaintDTO
import com.example.rmutt.entities.AdmUser
import com.example.rmutt.entities.Complaint

interface ComplaintService {

    fun addComplaint(body: ComplaintDTO): String

    fun getComplaintsByEmail(emailAddress: String): List<ComplaintDTO>

}