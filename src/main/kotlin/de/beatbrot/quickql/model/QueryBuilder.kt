package de.beatbrot.quickql.model

class QueryBuilder(private val parent: Query) {
    operator fun get(name: String): InnerQuery {
        val obj = InnerQuery(name)
        parent += obj
        return obj
    }
}