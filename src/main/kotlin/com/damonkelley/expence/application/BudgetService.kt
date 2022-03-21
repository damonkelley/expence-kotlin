package com.damonkelley.expence.application

import com.damonkelley.expence.application.ports.outgoing.EventStore
import com.damonkelley.expence.application.ports.outgoing.Stream
import com.damonkelley.expence.domain.Budget
import com.damonkelley.expence.domain.Command

class BudgetService(private val eventStore: EventStore) {
    fun send(command: Command, trace: Trace = Trace(command.id())) {
        eventStore.load(Stream(command.id().toString()))
            .let { Budget(it).handle(command) }
            .let { eventStore.add(it, trace) }
    }
}