package com.example.rmutt.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "report_problem")
data class ReportProblem (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null,

    @Column(name = "report_topic")
    var topic: String? = null,

    @Column(name = "report_problem")
    var problem: String? = null,

    @Column(name = "report_problem_detail")
    var problemDetail: String? = null,

    @Column(name = "telephone")
    var telephone: String? = null,

    @Column(name = "emailaddress")
    var emailAddress: String? = null,

    @Column(name = "create_date")
    var createDate: Timestamp? = null,
)