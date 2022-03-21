package com.damonkelley.expence.application

import com.damonkelley.expence.application.ports.outgoing.EventStore
import com.damonkelley.expence.domain.budgets.Budget
import com.damonkelley.expence.domain.budgets.Command

class BudgetService(private val eventStore: EventStore) {
    fun send(command: Command, trace: Trace = Trace(command)) {
        eventStore.load(command)
            .let { Budget(it).handle(command) }
            .let { eventStore.add(it, trace) }
    }
}