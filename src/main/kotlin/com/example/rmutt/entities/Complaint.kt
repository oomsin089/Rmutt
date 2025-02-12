package com.example.rmutt.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "complaint_system")
data class Complaint(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "surname")
    var surName: String? = null,

    @Column(name = "emailaddress")
    var emailAddress: String? = null,

    @Column(name = "topic_of_complaint")
    var topicOfComplaint: String? = null,

    @Column(name = "details_of_the_topic")
    var detailsOfTheTopic: String? = null,

    @Column(name = "status")
    var status: String? = null,

    @Column(name = "problem_detail")
    var problemDetail: String? = null,

    @Column(name = "telephone")
    var telephone: String? = null,

    @Column(name = "create_date")
    var createDate: Timestamp? = null,

    @Column(name = "full_name")
    var fullName: String? = null,
)
