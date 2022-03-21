package com.damonkelley.expence.application

import com.damonkelley.expence.domain.AccountId
import com.damonkelley.expence.domain.BudgetId
import com.damonkelley.expence.domain.Name
import com.damonkelley.expence.domain.budgets.AddAccount
import com.damonkelley.expence.domain.budgets.BudgetStarted
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.should
import java.util.*

class BudgetServiceTest : BehaviorSpec({
    Given("a command") {
        val budgetId = BudgetId(UUID.randomUUID())
        val accountId = AccountId(UUID.randomUUID())
        AddAccount(
            id = accountId,
            budgetId = budgetId,
            name = Name("Savings")
        )

        Then("it works") {
            val budgetStarted = BudgetStarted(id = budgetId, name = Name("My Budget"))
            val eventStore = TestEventStore().apply { add(listOf(budgetStarted), Trace(budgetStarted)) }

            BudgetService(eventStore).send(
                AddAccount(
                    id = accountId,
                    budgetId = budgetId,
                    name = Name("Savings")
                ), Trace(accountId)
            )

            eventStore should havePublishedEvents(accountId)
        }
    }
})