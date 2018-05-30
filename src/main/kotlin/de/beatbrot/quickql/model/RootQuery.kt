package de.beatbrot.quickql.model



class RootQuery(val type: QueryType = QueryType.NONE, val name: String? = null, any: Query.() -> Unit) : Query() {
    init {
        fetchElementsFromFunction(any)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RootQuery) return false

        if (elements != other.elements) return false

        return true
    }

    @Suppress("MagicNumber")
    override fun hashCode(): Int {
        var result = 0
        result = 31 * result + elements.hashCode()
        return result
    }
}
