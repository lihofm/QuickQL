package de.beatbrot.quickql.model

import de.beatbrot.quickql.visitor.GraphQLVisitor
import de.beatbrot.quickql.visitor.OperationVisitor


class RootOperation(val type: OperationType = OperationType.QUERY, val name: String? = null, any: Operation.() -> Unit) : Operation() {
    init {
        fetchElementsFromFunction(any)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RootOperation) return false
        if (childs != other.childs) return false
        return true
    }

    @Suppress("MagicNumber")
    override fun hashCode(): Int {
        var result = 0
        result = 31 * result + childs.hashCode()
        return result
    }

    override fun toString(): String = visit(GraphQLVisitor())!!

    fun <T> visit(visitor: OperationVisitor<T>): T? {
        return visitor.visitAll(this)
    }
}
