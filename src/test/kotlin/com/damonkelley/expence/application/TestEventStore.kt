package com.damonkelley.expence.application

import com.damonkelley.expence.application.ports.outgoing.EventStore
import com.damonkelley.expence.domain.Events
import com.damonkelley.expence.domain.Id
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import java.util.*

class TestEventStore: EventStore {
    val events: MutableMap<UUID, Events> = mutableMapOf()

    override fun load(stream: Id): Events {
        return this.events.getOrDefault(stream.id(), emptyList())
    }

    override fun add(events: Events, trace: Trace): Events {
        events.forEach {
            this.events[it.id()] = load(it) + events
        }
        return events
    }
}

fun havePublishedEvents(stream: Id) = Matcher<TestEventStore> {
    MatcherResult(
        it.load(stream).isNotEmpty(),
        {"The event store did not publish events for stream $stream.\n ${it.events}"},
        {"The event store should not have published events for stream $stream but did"}
    )
}
