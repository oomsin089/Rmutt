package com.example.rmutt.repository

import com.example.rmutt.dto.ComplaintDTO
import com.example.rmutt.entities.Complaint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ComplaintRepository: JpaRepository<Complaint, Int> {

    fun findByEmailAddress(emailAddress: String): List<Complaint>

    @Query(
        """
    SELECT DISTINCT NEW com.example.rmutt.dto.ComplaintDTO(
    CP.id,
    CP.firstName,
    CP.lastName,
    CP.emailAddress,
    CP.topicOfComplaint,
    CP.detailsOfTheTopic,
    CP.status,
    CP.problemDetail,
    CP.telephone,
    CP.fullName,
    CP.createDate
    )
    FROM Complaint CP
    LEFT JOIN ComplaintLog CPL ON CP.id = CPL.complaintId
    WHERE CP.id = :id 
"""
    )
    fun findComplaintById(@Param("id") id: Int?): ComplaintDTO?

}