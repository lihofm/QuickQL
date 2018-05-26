package de.beatbrot.quickql.model

class QueryBuilder {
    internal val queries: MutableList<InnerQuery> = ArrayList()

    operator fun get(name: String): InnerQuery {
        val query = InnerQuery(name)
        queries.add(query)
        return query
    }
}
