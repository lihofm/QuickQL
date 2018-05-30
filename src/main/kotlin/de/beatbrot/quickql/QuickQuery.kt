@file:Suppress("ClassName")

package de.beatbrot.quickql

import de.beatbrot.quickql.model.Query
import de.beatbrot.quickql.model.QueryType
import de.beatbrot.quickql.model.RootQuery

sealed class QuickQuery {
    abstract val type: QueryType

    operator fun invoke(name: String = "", query: Query.() -> Unit): RootQuery {
        return RootQuery(type, name, query)
    }

}

object query : QuickQuery() {
    override val type = QueryType.QUERY
}

object mutation : QuickQuery() {
    override val type = QueryType.MUTATION
}

object subscription : QuickQuery() {
    override val type = QueryType.SUBSCRIPTION
}
