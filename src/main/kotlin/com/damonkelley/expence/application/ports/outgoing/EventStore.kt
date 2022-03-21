package com.damonkelley.expence.application.ports.outgoing

import com.damonkelley.expence.application.Trace
import com.damonkelley.expence.domain.Events
import com.damonkelley.expence.domain.Id

interface EventStore {
    fun load(stream: Id): Events
    fun add(events: Events, trace: Trace): Events
}