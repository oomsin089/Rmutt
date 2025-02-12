package com.example.rmutt.service.implement

import com.example.rmutt.dto.ComplaintDTO
import com.example.rmutt.entities.Complaint
import com.example.rmutt.repository.ComplaintRepository
import com.example.rmutt.service.ComplaintService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class ComplaintServiceImpl: ComplaintService {

    @Autowired
    lateinit var complaintRepository: ComplaintRepository

    override fun addComplaint(body: ComplaintDTO): ComplaintDTO? {
        val currentDateTime = LocalDateTime.now()
        val currentTimestamp = Timestamp.valueOf(currentDateTime)
        try {
            val addComplaint = Complaint().apply {
                name = body.name
                surName = body.surName
                emailAddress = body.emailAddress
                topicOfComplaint = body.topicOfComplaint
                detailsOfTheTopic = body.detailsOfTheTopic
                problemDetail = body.problemDetail
                telephone = body.telephone
                status = "OPEN"
                createDate = currentTimestamp
                fullName = body.fullName
            }
            complaintRepository.save(addComplaint)
            return body
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}