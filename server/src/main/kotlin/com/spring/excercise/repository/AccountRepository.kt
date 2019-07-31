package com.spring.excercise.repository

import com.spring.excercise.entity.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository: CrudRepository<Account, Int> {
    fun findAccountByEmailAndPasswordAndStatus(email: String, password: String, status: Int): Optional<Account>
}
