package com.example.rmutt.dto

import jakarta.persistence.Column
import java.sql.Timestamp

data class ReportProblemDTO(
    var id: Int? = null,
    var topic: String? = null,
    var problem: String? = null,
    var problemDetail: String? = null,
    var telephone: String? = null,
    var emailAddress: String? = null,
    var createDate: Timestamp? = null,
)
