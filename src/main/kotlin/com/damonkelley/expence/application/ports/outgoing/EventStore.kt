package com.damonkelley.expence.application.ports.outgoing

import com.damonkelley.expence.application.Trace
import com.damonkelley.expence.domain.Events

@JvmInline
value class Stream(val value: String)

interface EventStore {
    fun load(stream: Stream): Events
    fun add(events: Events, trace: Trace): Events
}