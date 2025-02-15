package com.example.rmutt.dto

import java.sql.Timestamp

data class ComplaintDTO(
    var firstName: String? = null,
    var lastName: String? = null,
    var emailAddress: String? = null,
    var topicOfComplaint: String? = null,
    var detailsOfTheTopic: String? = null,
    var status: String? = null,
    var problemDetail: String? = null,
    var telephone: String? = null,
    var fullName: String? = null,
    var createDate: Timestamp? = null,
)
