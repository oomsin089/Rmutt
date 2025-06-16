package com.example.rmutt.repository

import com.example.rmutt.entities.ReportProblem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReportProblemRepository: JpaRepository<ReportProblem, Long> {
}