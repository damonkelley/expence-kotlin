package com.damonkelley.expence.application

import com.damonkelley.expence.application.ports.outgoing.Repository
import com.damonkelley.expence.application.ports.outgoing.Stream
import com.damonkelley.expence.domain.Budget
import com.damonkelley.expence.domain.BudgetStarted
import com.damonkelley.expence.domain.Event
import com.damonkelley.expence.domain.Name
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import java.util.*

class StartBudgetServiceTest : BehaviorSpec({
    Given("A StartBudget command") {
        val budgetId = UUID.randomUUID()
        val command = StartBudget(id = budgetId, name = Name("My first budget"))

        When("an existing budget does not exist") {
            val repository = TestRepository()

            Then("it publishes events") {
                val eventStore = TestEventStore()

                StartBudgetService(repository, eventStore).send(command)

                eventStore.load(Stream(command.id.toString())).map(Event::id) shouldContain command.id
            }
        }

        When("an existing budget exists") {
            val events = listOf(BudgetStarted(id = budgetId, name = Name("This event should be published")))
            val repository = TestRepository(Budget(events))

            Then("nothing happens") {
                val eventStore = TestEventStore()

                StartBudgetService(repository, eventStore).send(command)

                eventStore.load(Stream(command.id.toString())) shouldBe emptyList()
            }
        }
    }
})


class TestRepository(private val budget: Budget? = null) : Repository<Budget> {
    override fun load(id: UUID): Budget? {
        return budget
    }
}