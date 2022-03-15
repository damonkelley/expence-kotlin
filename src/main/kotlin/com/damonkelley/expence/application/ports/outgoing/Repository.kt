package com.damonkelley.expence.application.ports.outgoing

import java.util.*

interface Repository<T> {
    fun load(id: UUID): T?
}