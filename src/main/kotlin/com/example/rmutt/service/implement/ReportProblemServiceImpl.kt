package com.example.rmutt.service.implement

import com.example.rmutt.dto.ReportProblemDTO
import com.example.rmutt.entities.Complaint
import com.example.rmutt.entities.ReportProblem
import com.example.rmutt.repository.ReportProblemRepository
import com.example.rmutt.service.ReportProblemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class ReportProblemServiceImpl: ReportProblemService {

    @Autowired
    lateinit var reportProblemRepository: ReportProblemRepository

    override fun addProblem(body: ReportProblemDTO): String {
        val currentDateTime = LocalDateTime.now()
        val currentTimestamp = Timestamp.valueOf(currentDateTime)
        try {
            val addReportProblem = ReportProblem().apply {
                topic = body.topic
                problem = body.problem
                problemDetail = body.problemDetail
                telephone = body.telephone
                emailAddress = body.emailAddress
                createDate = currentTimestamp
            }

            // บันทึกข้อมูลลงฐานข้อมูล
            val savedReport = reportProblemRepository.save(addReportProblem)

            return "Report saved with ID: ${savedReport.id}"
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
    override fun getAll(): List<ReportProblem>{
        return reportProblemRepository.findAll()
    }
}
