package com.example.rmutt.dto

import java.sql.Timestamp
import java.time.LocalDate

data class ComplaintDTO(
    var id: Int? = null,
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


data class ComplaintStatusDTO(
    var id: Int? = null,
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
    var stageStatus: List<SimplifiedComplaintLogDTO>?,
    )


data class SimplifiedComplaintLogDTO(
    var id : Int? = null,
    var state  : String? = null,
    var createDate: Timestamp? = null,
    var complaintId : Int? = null,
    var comment: String? = null,
    var dueDate: Timestamp? = null,
)

data class ComplaintActivityDTO(
    var id: Int? = null,
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
    var state  : String? = null,
    var comment : String?= null,
    var complaintId : Int? = null,
    var dueDate : Timestamp? = null,
    val dueTime: String?, // เพิ่มฟิลด์นี้

)
