package com.damonkelley.expence.application

import com.damonkelley.expence.domain.Id
import java.util.*

data class Trace(
    val id: UUID,
    val correlationId: UUID,
    val causationId: UUID,
) {
    constructor(id: Id): this(id=id.id(), correlationId = id.id(), causationId=id.id())
}