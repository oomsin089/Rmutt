package com.example.rmutt.entities

import jakarta.persistence.*

@Entity
@Table(name = "admuser")
data class AdmUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null,

    @Column(name = "emailaddress")
    var emailAddress: String? = null,

    @Column(name = "password")
    var passWord: String? = null
)
