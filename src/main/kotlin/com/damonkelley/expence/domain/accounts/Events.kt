package com.damonkelley.expence.domain.accounts

import com.damonkelley.expence.domain.AccountId
import com.damonkelley.expence.domain.BudgetId
import com.damonkelley.expence.domain.Event
import com.damonkelley.expence.domain.Name


sealed interface AccountEvent: Event

data class AccountAdded(
    val id: AccountId,
    val budgetId: BudgetId,
    val name: Name
) : AccountEvent {
    override fun id() = id.id()
}
