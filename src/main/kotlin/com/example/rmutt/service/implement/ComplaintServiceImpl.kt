package com.example.rmutt.service.implement

import com.example.rmutt.dto.ComplaintDTO
import com.example.rmutt.entities.AdmUser
import com.example.rmutt.entities.Complaint
import com.example.rmutt.entities.ComplaintLog
import com.example.rmutt.repository.AdmUserRepository
import com.example.rmutt.repository.ComplaintLogRepository
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

    @Autowired
    lateinit var complaintLogRepository: ComplaintLogRepository

    @Autowired
    lateinit var admUserRepository: AdmUserRepository

    override fun addComplaint(body: ComplaintDTO): String {
        val currentDateTime = LocalDateTime.now()
        val currentTimestamp = Timestamp.valueOf(currentDateTime)
        try {
            val addComplaint = Complaint().apply {
                firstName = body.firstName
                lastName = body.lastName
                emailAddress = body.emailAddress
                topicOfComplaint = body.topicOfComplaint
                detailsOfTheTopic = body.detailsOfTheTopic
                problemDetail = body.problemDetail
                telephone = body.telephone
                status = "รอดำเนินการ"
                createDate = currentTimestamp
                fullName = body.fullName
            }
            val saveComplaint = complaintRepository.save(addComplaint)
            val saveComplaintLog = listOf(
                ComplaintLog(
                    createDate = currentTimestamp,
                    state = "รอดำเนินการ",
                )
            )
            complaintLogRepository.saveAll(saveComplaintLog)

            return saveComplaint.id.toString()

        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun getComplaintsByEmail(emailAddress: String): List<ComplaintDTO> {
        val complaints = complaintRepository.findByEmailAddress(emailAddress)
        return complaints.map { complaint ->
            ComplaintDTO(
                firstName = complaint.firstName,
                lastName = complaint.lastName,
                emailAddress = complaint.emailAddress,
                topicOfComplaint = complaint.topicOfComplaint,
                detailsOfTheTopic = complaint.detailsOfTheTopic,
                status = complaint.status,
                problemDetail = complaint.problemDetail,
                telephone = complaint.telephone,
                fullName = complaint.fullName,
                createDate = complaint.createDate
            )
        }
    }
}