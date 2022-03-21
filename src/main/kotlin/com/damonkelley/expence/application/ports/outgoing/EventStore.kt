package com.damonkelley.expence.application.ports.outgoing

import com.damonkelley.expence.application.Trace
import com.damonkelley.expence.domain.Events
import java.util.*

interface EventStore {
    fun load(stream: UUID): Events
    fun add(events: Events, trace: Trace): Events
}