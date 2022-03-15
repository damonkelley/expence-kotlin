package com.damonkelley.expence.application

import com.damonkelley.expence.domain.BudgetId
import com.damonkelley.expence.domain.Name
import java.util.*

sealed class Command;
data class StartBudget(val id: UUID, val name: Name): Command()

data class AddAccount(val id: UUID, val budgetId: BudgetId, val name: Name): Command()
