package com.spring.excercise.repository

import com.spring.excercise.entity.VerifyToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VerifyTokenRepository: CrudRepository<VerifyToken, Int> {
    fun findVerifyTokenByToken(token: String): Optional<VerifyToken>
}