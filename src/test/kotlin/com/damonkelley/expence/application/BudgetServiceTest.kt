package com.damonkelley.expence.application

import com.damonkelley.expence.application.ports.outgoing.Stream
import com.damonkelley.expence.domain.AddAccount
import com.damonkelley.expence.domain.BudgetId
import com.damonkelley.expence.domain.BudgetStarted
import com.damonkelley.expence.domain.Name
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.should
import java.util.*

class BudgetServiceTest : BehaviorSpec({
    Given("a command") {
        val budgetId = UUID.randomUUID()
        val accountId = UUID.randomUUID()
        AddAccount(
            id = accountId,
            budgetId = BudgetId(budgetId),
            name = Name("Savings")
        )

        Then("it works") {
            val budgetStarted = BudgetStarted(id = budgetId, name = Name("My Budget"))
            val eventStore = TestEventStore().apply { add(listOf(budgetStarted), Trace(budgetStarted.id)) }

            BudgetService(eventStore).send(
                AddAccount(
                    id = accountId,
                    budgetId = BudgetId(budgetStarted.id),
                    name = Name("Savings")
                ), Trace(accountId)
            )

            eventStore should havePublishedEvents(Stream(accountId.toString()))
        }
    }
})