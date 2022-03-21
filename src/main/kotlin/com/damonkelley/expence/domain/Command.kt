package com.damonkelley.expence.domain

import java.util.*

sealed interface Command {
    fun id(): UUID
}
data class StartBudget(val id: UUID, val name: Name): Command {
    override fun id(): UUID = id
}

data class AddAccount(val id: UUID, val budgetId: BudgetId, val name: Name): Command {
    override fun id(): UUID = id
}
