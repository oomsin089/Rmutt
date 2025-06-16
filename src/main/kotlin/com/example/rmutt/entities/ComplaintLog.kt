package com.example.rmutt.entities

import jakarta.persistence.*
import java.sql.Timestamp
import java.time.LocalDate

@Entity
@Table(name = "complaint_system_log")
data class ComplaintLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null,

    @Column(name = "state")
    var state: String? = null,

    @Column(name = "create_date")
    var createDate: Timestamp? = null,

    @Column(name = "complaint_id")
    var complaintId: Int? = null,

    @Column(name = "first_name")
    var firstName: String? = null,

    @Column(name = "last_name")
    var lastName: String? = null,

    @Column(name = "emailaddress")
    var emailAddress: String? = null,

    @Column(name = "full_name")
    var fullName: String? = null,

    @Column(name = "comment")
    var comment: String? = null,

    @Column(name = "due_date")
    var dueDate: Timestamp? = null,
)
