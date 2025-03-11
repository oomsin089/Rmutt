package com.example.rmutt.service

import com.example.rmutt.dto.ReportProblemDTO
import com.example.rmutt.entities.ReportProblem
import org.springframework.beans.factory.parsing.Problem

interface ReportProblemService {
    fun addProblem(body: ReportProblemDTO): String
    fun getAll(): List<ReportProblem>
}