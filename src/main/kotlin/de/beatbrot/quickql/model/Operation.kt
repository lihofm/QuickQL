package de.beatbrot.quickql.model

abstract class Operation {
    val ql = OperationBuilder()
    var childs: List<Field> = emptyList()

    protected fun fetchElementsFromFunction(operation: Operation.() -> Unit) {
        operation.invoke(this)
        childs = ql.queries
    }

    override fun toString(): String {
        return "Operation: $childs"
    }
}
