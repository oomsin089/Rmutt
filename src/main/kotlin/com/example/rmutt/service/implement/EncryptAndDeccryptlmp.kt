package com.example.rmutt.service.implement

import com.example.rmutt.log.Log
import com.example.rmutt.service.EncryptAndDecrypt
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@Service
class EncryptAndDecryptImpl: EncryptAndDecrypt {

    companion object : Log()
    private val algorithm = "AES"
    private val privateKey = "Secret_Key"
    private val key: SecretKeySpec
    private val idMap = ConcurrentHashMap<String, String>()

    init {
        val keyBytes = privateKey.toByteArray(Charsets.UTF_8)
        val sha = MessageDigest.getInstance("SHA-256")
        var keyDigest = sha.digest(keyBytes)
        keyDigest = keyDigest.copyOf(16)
        this.key = SecretKeySpec(keyDigest, algorithm)
    }

    override fun encrypt(data: String): String {
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptedData = cipher.doFinal(data.toByteArray())
        val encryptedString = Base64.getUrlEncoder().withoutPadding().encodeToString(encryptedData)
        val shortId = generateShortId(encryptedString)
        idMap[shortId] = encryptedString
        return shortId
    }

    override fun decrypt(shortId: String): String {
        val encryptedData = idMap[shortId] ?: throw IllegalArgumentException("Invalid short ID")
        val decodedData = Base64.getUrlDecoder().decode(encryptedData)
        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.DECRYPT_MODE, key)
        val decryptedData = cipher.doFinal(decodedData)
        return String(decryptedData)
    }

    private fun generateShortId(data: String): String {
        val digest = MessageDigest.getInstance("SHA-256").digest(data.toByteArray())
        val base32Hash = Base64.getUrlEncoder().withoutPadding().encodeToString(digest)
        return base32Hash.take(5)
    }
}