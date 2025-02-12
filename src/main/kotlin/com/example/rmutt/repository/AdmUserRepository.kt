package com.example.rmutt.repository

import com.example.rmutt.entities.AdmUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AdmUserRepository: JpaRepository<AdmUser, Long> {
    fun findByEmailAddress(emailAddress: String): AdmUser

    fun existsByEmailAddress(emailAddress: String): Boolean

}