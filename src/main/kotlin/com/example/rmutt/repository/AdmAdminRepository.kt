package com.example.rmutt.repository

import com.example.rmutt.entities.AdmAdmin
import com.example.rmutt.entities.AdmUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdmAdminRepository: JpaRepository<AdmAdmin, Long>  {
    fun findByEmailAddress(emailAddress: String): AdmAdmin

    fun existsByEmailAddress(emailAddress: String): Boolean
}