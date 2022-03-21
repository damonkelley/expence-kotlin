package com.damonkelley.expence.domain.budgets

import com.damonkelley.expence.domain.*
import com.damonkelley.expence.domain.accounts.AccountAdded

class Budget(val events: Events = emptyList()) {
    fun handle(command: Command): Events {
        return when(command) {
            is StartBudget -> handle(command)
            is AddAccount -> handle(command)
        }
    }

   private fun handle(command: StartBudget): Events {
        return listOf(
            BudgetStarted(
                id = command.id,
                name = command.name
            )
        )
    }

    private fun handle(command: AddAccount): Events {
        return listOf(
            AccountAdded(
                id = command.id,
                budgetId = command.budgetId,
                name = command.name
            )
        )
    }
}