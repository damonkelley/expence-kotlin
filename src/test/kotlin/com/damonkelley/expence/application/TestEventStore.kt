package com.damonkelley.expence.application

import com.damonkelley.expence.application.ports.outgoing.EventStore
import com.damonkelley.expence.application.ports.outgoing.Stream
import com.damonkelley.expence.domain.Event
import com.damonkelley.expence.domain.Events

class TestEventStore: EventStore {
    private val events: MutableMap<Stream, Events> = mutableMapOf()

    override fun load(stream: Stream): Events {
        return this.events.getOrDefault(stream, emptyList())
    }

    override fun add(events: Events, trace: Trace): Events {
        events.forEach {
            val stream = Stream(it.id.toString())
            this.events[stream] = load(stream) + events
        }
        return events
    }
}