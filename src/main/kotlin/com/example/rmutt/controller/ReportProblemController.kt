package com.example.rmutt.controller

import com.example.rmutt.dto.ComplaintDTO
import com.example.rmutt.dto.ReportProblemDTO
import com.example.rmutt.entities.Complaint
import com.example.rmutt.entities.ReportProblem
import com.example.rmutt.service.ReportProblemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class ReportProblemController {

    @Autowired
    lateinit var reportProblemService: ReportProblemService

    @PostMapping("/addreport")
    fun add(@RequestBody body:ReportProblemDTO): String {
        return reportProblemService.addProblem(body)
    }
    @GetMapping("/getAllReport")
    fun getAllReportProblem(): List<ReportProblem>{
        return reportProblemService.getAll()
    }
}