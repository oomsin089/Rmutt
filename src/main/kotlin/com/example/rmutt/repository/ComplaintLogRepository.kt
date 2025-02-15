package com.example.rmutt.repository

import com.example.rmutt.entities.Complaint
import com.example.rmutt.entities.ComplaintLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ComplaintLogRepository: JpaRepository<ComplaintLog, Long> {
}