package com.damonkelley.expence.application

import com.damonkelley.expence.application.ports.outgoing.EventStore
import com.damonkelley.expence.domain.Events
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import java.util.*

class TestEventStore: EventStore {
    val events: MutableMap<UUID, Events> = mutableMapOf()

    override fun load(stream: UUID): Events {
        return this.events.getOrDefault(stream, emptyList())
    }

    override fun add(events: Events, trace: Trace): Events {
        events.forEach {
            this.events[it.id()] = load(it.id()) + events
        }
        return events
    }
}

fun havePublishedEvents(stream: UUID) = Matcher<TestEventStore> {
    MatcherResult(
        it.load(stream).isNotEmpty(),
        {"The event store did not publish events for stream $stream.\n ${it.events}"},
        {"The event store should not have published events for stream $stream but did"}
    )
}
