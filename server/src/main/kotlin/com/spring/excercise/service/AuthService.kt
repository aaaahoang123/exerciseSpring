package com.spring.excercise.service

import com.spring.excercise.entity.Account
import com.spring.excercise.entity.VerifyToken
import com.spring.excercise.repository.AccountRepository
import com.spring.excercise.repository.VerifyTokenRepository
import com.spring.excercise.util.randomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
        @Autowired private val accountRepo: AccountRepository,
        @Autowired private val tokenRepo: VerifyTokenRepository,
        @Autowired private val mailService: MailService
) {
    fun register(account: Account): Account {
        val createdAccount = accountRepo.save(account)
        val expireTime = Calendar.getInstance();
        expireTime.add(Calendar.MINUTE, 15)
        val token = VerifyToken(
                account = account,
                token = generateNewVerifyToken(),
                expired = expireTime.timeInMillis
        )
        val verifyToken = tokenRepo.save(token)
        mailService.sendVerifyMail(createdAccount.email, verifyToken.token);
        return createdAccount
    }

    private fun generateNewVerifyToken(): String {
        val token = randomString(25)
        if (tokenRepo.findVerifyTokenByToken(token).isPresent) {
            return generateNewVerifyToken()
        }
        return token
    }

    fun verifyAccount(token: String): Boolean {
        val verifyTokenOptional = tokenRepo.findVerifyTokenByToken(token)
        if (verifyTokenOptional.isPresent) {
            val verifyToken = verifyTokenOptional.get()
            val account = verifyToken.account
            account?.status = 1
            accountRepo.save(account as Account)
            tokenRepo.delete(verifyToken)
            return true
        }
        return false
    }

    fun login(account: Account): Account? {
        val optional = accountRepo.findAccountByEmailAndPasswordAndStatus(account.email as String, account.password as String, 1)
        if (optional.isPresent) {
            return optional.get()
        }
        return null
    }
}
