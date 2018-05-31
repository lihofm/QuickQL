package de.beatbrot.quickql.visitor

import de.beatbrot.quickql.model.Field
import de.beatbrot.quickql.model.RootOperation

abstract class OperationVisitor<out T>() {

    open fun visitAll(rootQuery: RootOperation): T? {
        visitRootQuery(rootQuery)
        return null
    }

    protected open fun visitRootQuery(rootQuery: RootOperation) {
        rootQuery.childs.forEach {
            visitQuery(it, 1)
        }
    }

    protected open fun visitQuery(query: Field, level: Int) {
        query.childs.forEach {
            visitQuery(it, level + 1)
        }
    }
}
