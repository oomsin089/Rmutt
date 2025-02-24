package com.example.rmutt.controller

import com.example.rmutt.service.EncryptAndDecrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/encryption")
class EncryptAndDecryptControler() {

    @Autowired
    private lateinit var encryptAndDecrypt: EncryptAndDecrypt

    @PostMapping("/encrypt")
    fun encrypt(@RequestParam("id") id: String): ResponseEntity<String> {
        return ResponseEntity.ok().body(encryptAndDecrypt.encrypt(id))
    }

    @PostMapping("/decrypt")
    fun decrypt(@RequestParam("id") id: String): ResponseEntity<String> {
        return ResponseEntity.ok().body(encryptAndDecrypt.decrypt(id))
    }
}
