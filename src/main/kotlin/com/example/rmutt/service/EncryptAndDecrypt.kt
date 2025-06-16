package com.example.rmutt.service

interface EncryptAndDecrypt {
    fun encrypt(plainText: String): String
    fun decrypt(cipherText: String): String
}