package com.example.rmutt.service.implement

import com.example.rmutt.dto.*
import com.example.rmutt.entities.AdmUser
import com.example.rmutt.entities.Complaint
import com.example.rmutt.entities.ComplaintLog
import com.example.rmutt.repository.AdmUserRepository
import com.example.rmutt.repository.ComplaintLogRepository
import com.example.rmutt.repository.ComplaintRepository
import com.example.rmutt.service.ComplaintService
import com.example.rmutt.service.implement.EncryptAndDecryptImpl.Companion.logger
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class ComplaintServiceImpl: ComplaintService {

    @Autowired
    lateinit var complaintRepository: ComplaintRepository

    @Autowired
    lateinit var complaintLogRepository: ComplaintLogRepository

    override fun getAll(): List<Complaint>{
        return complaintRepository.findAll()
    }

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
                    firstName = body.firstName ,
                    lastName = body.lastName,
                    emailAddress = body.emailAddress,
                    complaintId = saveComplaint.id,
                    createDate = currentTimestamp,
                    state = "รอดำเนินการ",
                    fullName = body.fullName
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
                id = complaint.id,
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

    override fun getComplaintById(id: Int): ResponseEntity<ResponseComplaintDTO<ComplaintStatusDTO>> {
        try {
            val complaint = complaintRepository.findComplaintById(id)
            val stageStatus = complaintLogRepository.findStageStatusByComplaintId(id)

            if (complaint != null) {
                val defaultDate = complaint.createDate?.toLocalDateTime()
                    ?.atOffset(ZoneOffset.UTC)
                    ?.withOffsetSameInstant(ZoneOffset.ofHours(0))
                    ?.toLocalDateTime()
                    ?.let { Timestamp.valueOf(it) }

                val simplifiedStageStatus = stageStatus.map { activity ->
                    SimplifiedComplaintLogDTO(
                        id = activity.id,
                        state = activity.state,
                        createDate = activity.createDate,
                        complaintId = activity.complaintId,

                    )
                }

                val combinedProblemservice = ComplaintStatusDTO(
                    id = complaint.id,
                    firstName = complaint.firstName,
                    lastName = complaint.lastName,
                    emailAddress = complaint.emailAddress,
                    topicOfComplaint = complaint.topicOfComplaint,
                    detailsOfTheTopic = complaint.detailsOfTheTopic,
                    status = complaint.status,
                    problemDetail = complaint.problemDetail,
                    telephone = complaint.telephone,
                    fullName = complaint.fullName,
                    createDate = complaint.createDate,
                    stageStatus = simplifiedStageStatus,
                )
                return ResponseEntity
                    .status(HttpStatus.OK).body(
                        ResponseComplaintDTO(
                            success = true,
                            message = "Get Application By Id Success",
                            data = combinedProblemservice
                        )
                    )
            } else {
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).body(
                        ResponseComplaintDTO(
                            success = false,
                            message = "Data not found",
                            data = null
                        )
                    )
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ResponseComplaintDTO(
                        success = false,
                        message = "Get data Failed",
                        data = null
                    )
                )
        }
    }
    override fun updateManage(problemId: Int, updateRequest: ComplaintActivityDTO): String? {
        try {
            val complaint = complaintRepository.findById(problemId)
                .orElseThrow { EntityNotFoundException("ไม่พบปัญหา") }

            val currentDateTime = LocalDateTime.now()
            val currentTimestamp = Timestamp.valueOf(currentDateTime)

            complaint.apply {
                status = updateRequest.status
            }

            complaintRepository.save(complaint)

            // Log complaint state changes
            logComplaintState(
                updateRequest.state.toString(),
                complaint.id!!, currentTimestamp,
                updateRequest.firstName.toString(),
                updateRequest.lastName.toString(),
                updateRequest.fullName.toString(),
                updateRequest.emailAddress.toString()
            )

            return complaint.id.toString()
        } catch (e: Exception) {
            logger.error("Error occurred while updating manage: ${e.message}")
            throw RuntimeException("ไม่สามารถอัปเดตข้อมูลได้", e)
        }
    }
    private fun logComplaintState(
        state: String,
        complaintId: Int,
        createDate: Timestamp,
        firstName: String,
        lastName: String,
        fullName: String,
        emailAddress: String) {
        if (state in listOf("รอดำเนินการ", "กำลังดำเนินการ", "เสร็จสิ้น")) {
            val complaintLog = ComplaintLog(
                complaintId = complaintId,
                state = state,
                createDate = createDate,
                fullName = fullName,
                lastName = lastName,
                firstName = firstName,
                emailAddress = emailAddress
            )
            complaintLogRepository.save(complaintLog)
        }
    }
}