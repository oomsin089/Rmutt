package com.example.rmutt.service

import com.example.rmutt.dto.ComplaintDTO
import com.example.rmutt.entities.Complaint

interface ComplaintService {

    fun addComplaint(body: ComplaintDTO): ComplaintDTO?
}