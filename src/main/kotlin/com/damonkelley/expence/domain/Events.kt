package com.damonkelley.expence.domain

import java.util.*

typealias Events = List<Event>

sealed interface Event {
    val id: UUID
}

data class BudgetStarted(
    override val id: UUID,
    val name: Name
): Event

data class AccountAdded(
    override val id: UUID,
    val budgetId: BudgetId,
    val name: Name
) : Event
