@file:Suppress("ClassName")

package de.beatbrot.quickql

import de.beatbrot.quickql.model.Operation
import de.beatbrot.quickql.model.OperationType
import de.beatbrot.quickql.model.RootOperation

sealed class QuickQuery {
    abstract val type: OperationType

    operator fun invoke(name: String? = null, operation: Operation.() -> Unit): RootOperation {
        return RootOperation(type, name, operation)
    }

}

object query : QuickQuery() {
    override val type = OperationType.QUERY
}

object mutation : QuickQuery() {
    override val type = OperationType.MUTATION
}

object subscription : QuickQuery() {
    override val type = OperationType.SUBSCRIPTION
}
