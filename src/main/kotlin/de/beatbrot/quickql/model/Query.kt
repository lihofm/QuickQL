package de.beatbrot.quickql.model

abstract class Query {
    val ql = QueryBuilder()
    var elements: List<InnerQuery> = emptyList()

    protected fun fetchElementsFromFunction(query: Query.() -> Unit) {
        query.invoke(this)
        elements = ql.queries
    }

    override fun toString(): String {
        return "Query: $elements"
    }
}
