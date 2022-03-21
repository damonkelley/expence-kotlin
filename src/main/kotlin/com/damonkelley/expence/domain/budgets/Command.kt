package com.damonkelley.expence.domain.budgets

import com.damonkelley.expence.domain.AccountId
import com.damonkelley.expence.domain.BudgetId
import com.damonkelley.expence.domain.Id
import com.damonkelley.expence.domain.Name
import java.util.*

sealed interface Command: Id

data class StartBudget(val id: BudgetId, val name: Name): Command {
    override fun id(): UUID = id.id()
}

data class AddAccount(val id: AccountId, val budgetId: BudgetId, val name: Name): Command {
    override fun id(): UUID = id.id()
}
