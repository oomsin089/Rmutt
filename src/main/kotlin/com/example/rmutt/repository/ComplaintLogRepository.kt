package com.example.rmutt.repository

import com.example.rmutt.entities.Complaint
import com.example.rmutt.entities.ComplaintLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ComplaintLogRepository: JpaRepository<ComplaintLog, Long> {

    @Query("SELECT CPL  FROM ComplaintLog CPL WHERE CPL.complaintId = :complaintId")
    fun findStageStatusByComplaintId(@Param("complaintId") complaintId: Int?): List<ComplaintLog>

}