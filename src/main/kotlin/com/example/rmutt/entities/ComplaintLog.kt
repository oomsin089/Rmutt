package com.example.rmutt.entities

import jakarta.persistence.*
import java.sql.Timestamp

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

)
