package com.damonkelley.expence.domain.budgets

import com.damonkelley.expence.domain.BudgetId
import com.damonkelley.expence.domain.Event
import com.damonkelley.expence.domain.Name

sealed interface BudgetEvent: Event

data class BudgetStarted(
    val id: BudgetId,
    val name: Name
): BudgetEvent {
    override fun id() = id.id()
}
