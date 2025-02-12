package com.example.rmutt.repository

import com.example.rmutt.entities.Complaint
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ComplaintRepository: JpaRepository<Complaint, Long> {
}