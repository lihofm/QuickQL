package de.beatbrot.quickql.model

class OperationBuilder {
    internal val queries: MutableList<Field> = ArrayList()

    operator fun get(name: String): Field {
        val query = Field(name)
        queries.add(query)
        return query
    }
}
