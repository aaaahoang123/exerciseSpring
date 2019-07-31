package com.spring.excercise.dto

class VerifyEmailDto() {
    lateinit var token: String
    constructor(token: String): this() {
        this.token = token
    }
}