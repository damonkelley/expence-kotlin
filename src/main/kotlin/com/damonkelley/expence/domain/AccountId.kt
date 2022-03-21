package com.damonkelley.expence.domain

import java.util.*

@JvmInline
value class AccountId(private val value: UUID): Id {
    override fun id() = value
}
