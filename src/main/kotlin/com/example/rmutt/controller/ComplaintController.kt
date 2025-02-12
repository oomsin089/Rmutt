package com.example.rmutt.controller

import com.example.rmutt.dto.ComplaintDTO
import com.example.rmutt.entities.Complaint
import com.example.rmutt.service.ComplaintService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/complaint")
class ComplaintController {

    @Autowired
    lateinit var complaintService: ComplaintService

    @PostMapping("/add")
    fun add(@RequestBody complaint: ComplaintDTO): ComplaintDTO? {
        return complaintService.addComplaint(complaint)
    }

}