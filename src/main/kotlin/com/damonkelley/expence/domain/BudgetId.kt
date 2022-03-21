package com.damonkelley.expence.domain

import java.util.*

@JvmInline
value class BudgetId(private val value: UUID): Id {
    override fun id(): UUID {
        return value
    }
}