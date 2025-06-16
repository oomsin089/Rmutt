package com.example.rmutt.entities

import jakarta.persistence.*

@Entity
@Table(name = "admadmin")
data class AdmAdmin(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int? = null,

    @Column(name = "emailaddress")
    var emailAddress: String? = null,

    @Column(name = "password")
    var passWord: String? = null,

    @Column(name = "first_name")
    var firstName: String? = null,

    @Column(name = "last_name")
    var lastName: String? = null,

    @Column(name = "full_name")
    var fullName: String? = null,
)
