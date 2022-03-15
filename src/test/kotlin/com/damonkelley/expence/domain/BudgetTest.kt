package com.damonkelley.expence.domain

import com.damonkelley.expence.application.AddAccount
import com.damonkelley.expence.application.StartBudget
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.util.*

class BudgetTest : BehaviorSpec({
    Given("A StartBudgetCommand") {
        val command = StartBudget(id = UUID.randomUUID(), name = Name("My first budget"))

        When("the command is handled") {
            Then("it will have a BudgetStarted event") {
                val events = Budget().handle(command)

                events shouldBe listOf(
                    BudgetStarted(
                        id = command.id,
                        name = command.name
                    )
                )
            }
        }
    }

    Given("An AddAccount command") {
        val command = AddAccount(
            id = UUID.randomUUID(),
            budgetId = BudgetId(UUID.randomUUID()),
            name = Name("My first budget")
        )

        When("the command is handled") {
            Then("it will have a BudgetStarted event") {
                val events = Budget().handle(command)

                events shouldBe listOf(
                    AccountAdded(
                        id = command.id,
                        budgetId = command.budgetId,
                        name = command.name
                    )
                )
            }
        }
    }
})