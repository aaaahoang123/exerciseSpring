package com.spring.excercise.entity

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.UniqueElements
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.NotBlank

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["email"])])
class Account (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int? = null,
        @Email
        @NotBlank
        var email: String? = null,
        @NotBlank
        @Length(max = 255)
        var password: String? = null,
        var createdAt: Long? = Calendar.getInstance().timeInMillis,
        var updatedAt: Long? = Calendar.getInstance().timeInMillis,
        var status: Int? = -1
)