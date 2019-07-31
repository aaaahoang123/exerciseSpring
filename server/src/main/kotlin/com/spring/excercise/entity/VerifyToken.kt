package com.spring.excercise.entity

import javax.persistence.*

@Entity
class VerifyToken (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int? = null,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.DETACH])
        @JoinColumn(name = "accountId")
        var account: Account? = null,
        @Column(unique = true)
        var token: String? = null,
        var expired: Long? = null
)