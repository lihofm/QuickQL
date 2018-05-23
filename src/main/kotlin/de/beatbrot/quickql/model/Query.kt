package de.beatbrot.quickql.model

abstract class Query {
    val ql = QueryBuilder(this)
    var elements: List<InnerQuery> = ArrayList()


    operator fun invoke(any: Query.() -> Unit) {
        any.invoke(this)
    }


    operator fun plusAssign(add: InnerQuery) {
        elements += add
    }

    override fun toString(): String {
        return "Query: $elements"
    }
}