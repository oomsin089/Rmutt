package com.example.rmutt.dto

data class ComplaintDTO(
    var name: String? = null,
    var surName: String? = null,
    var emailAddress: String? = null,
    var topicOfComplaint: String? = null,
    var detailsOfTheTopic: String? = null,
    var problemDetail: String? = null,
    var telephone: String? = null,
    var fullName: String? = null,
)
