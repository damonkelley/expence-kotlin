package com.damonkelley.expence.application

import com.damonkelley.expence.application.ports.outgoing.EventStore
import com.damonkelley.expence.application.ports.outgoing.Repository
import com.damonkelley.expence.domain.Budget

class StartBudgetService(private val repository: Repository<Budget>, private val eventStore: EventStore) {
    fun send(command: StartBudget, trace: Trace = Trace(command.id)) {
        if (repository.load(command.id) != null) {
            return
        }

        Budget().handle(command).let { eventStore.add(it, trace) }
    }
}