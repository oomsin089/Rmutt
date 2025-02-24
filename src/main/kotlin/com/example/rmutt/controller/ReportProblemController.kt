package com.example.rmutt.controller

import com.example.rmutt.dto.ComplaintDTO
import com.example.rmutt.dto.ReportProblemDTO
import com.example.rmutt.service.ReportProblemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class ReportProblemController {

    @Autowired
    lateinit var reportProblemService: ReportProblemService

    @PostMapping("/addreport")
    fun add(@RequestBody body:ReportProblemDTO): String {
        return reportProblemService.addProblem(body)
    }
}