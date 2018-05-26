package de.beatbrot.quickql.visitor

import de.beatbrot.quickql.model.InnerQuery
import de.beatbrot.quickql.model.RootQuery

abstract class QueryVisitor(private val rootQuery: RootQuery) {

    fun visitAll() {
        visitRootQuery(rootQuery)
    }

    protected open fun visitRootQuery(rootQuery: RootQuery) {
        rootQuery.elements.forEach {
            visitQuery(it, 1)
        }
    }

    protected open fun visitQuery(query: InnerQuery, level: Int) {
        query.elements.forEach {
            visitQuery(it, level + 1)
        }
    }
}
