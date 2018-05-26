package de.beatbrot.quickql.model

class InnerQuery(val name: String) : Query() {
    var parameter: MutableMap<String, String> = HashMap()
    var alias: String = ""

    operator fun invoke(vararg param: Pair<String, Any>,alias: String = "", query: Query.() -> Unit = {}) {
        fetchParamsFromFunction(param)
        this.alias = alias
        fetchElementsFromFunction(query)
    }

    private fun fetchParamsFromFunction(param: Array<out Pair<String, Any>>) {
        param.forEach {
            with(it) {
                parameter[first] = if (second is String) "\"$second\"" else "$second"
            }
        }
    }

    override fun toString(): String {
        return "Query: $name, $elements"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is InnerQuery) return false

        if (name != other.name) return false
        if (parameter != other.parameter) return false
        if (elements != other.elements) return false

        return true
    }

    @Suppress("MagicNumber")
    override fun hashCode(): Int {
        var result = 0
        result = 31 * result + name.hashCode()
        result = 31 * result + parameter.hashCode()
        result = 31 * result + elements.hashCode()
        return result
    }

}
