package com.damonkelley.expence.domain

import com.damonkelley.expence.application.AddAccount
import com.damonkelley.expence.application.StartBudget

interface HandleStartBudget {
    fun handle(command: StartBudget): Events
}

interface HandleAddAccount {
    fun handle(command: AddAccount): Events
}

class Budget(val events: Events = emptyList()): HandleStartBudget, HandleAddAccount {
    override fun handle(command: StartBudget): Events {
        return listOf(
            BudgetStarted(
                id = command.id,
                name = command.name
            )
        )
    }

    override fun handle(command: AddAccount): Events {
        return listOf(
            AccountAdded(
                id = command.id,
                budgetId = command.budgetId,
                name = command.name
            )
        )
    }
}