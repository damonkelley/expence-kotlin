package com.damonkelley.expence.application

import java.util.*

data class Trace(
    val id: UUID,
    val correlationId: UUID,
    val causationId: UUID,
) {
    constructor(id: UUID): this(id=id, correlationId = id, causationId=id)
    constructor(id: UUID, trace: Trace): this(id=id, correlationId = trace.correlationId, causationId=trace.id)
}