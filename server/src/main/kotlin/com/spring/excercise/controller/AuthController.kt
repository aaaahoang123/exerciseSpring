package com.spring.excercise.controller

import com.spring.excercise.dto.VerifyEmailDto
import com.spring.excercise.entity.Account
import com.spring.excercise.service.AuthService
import com.spring.excercise.util.errorResponse
import com.spring.excercise.util.successResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/_v1"])
@CrossOrigin
class AuthController(
        @Autowired val authService: AuthService
) {
    @PostMapping(value = ["/register"])
    fun register(@RequestBody account: Account, errors: Errors): ResponseEntity<Any> {
        if (errors.hasErrors()) {
            return errorResponse(errors.allErrors[0].defaultMessage as String)
        }
        return successResponse(authService.register(account))
    }

    @PostMapping(value = ["/verify"])
    fun verifyEmail(@RequestBody dto: VerifyEmailDto, errors: Errors): ResponseEntity<Any> {
        if (errors.hasErrors()) {
            return errorResponse(errors.allErrors[0].defaultMessage as String)
        }
        return successResponse(mapOf("verified" to authService.verifyAccount(dto.token)))
    }

    @PostMapping(value = ["/login"])
    fun login(@RequestBody dto: Account, errors: Errors): ResponseEntity<Any> {
        if (errors.hasErrors()) {
            return errorResponse(errors.allErrors[0].defaultMessage as String)
        }
        val account = authService.login(dto)
        return if (account == null)
            errorResponse()
        else
            successResponse(account)
    }
}
