package de.beatbrot.quickql.model

class Field(val name: String) : Operation() {
    var arguments: MutableMap<String, String> = HashMap()
    var alias: String? = null

    operator fun invoke(vararg args: Pair<String, Any>, operation: Operation.() -> Unit = {}): Field {
        fetchParamsFromFunction(args)
        fetchElementsFromFunction(operation)
        return this
    }

    private fun fetchParamsFromFunction(param: Array<out Pair<String, Any>>) {
        param.forEach {
            with(it) {
                arguments[first] = (second as? Number)?.toString() ?: "\"$second\""
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Field) return false

        if (name != other.name) return false
        if (arguments != other.arguments) return false
        if (childs != other.childs) return false

        return true
    }

    @Suppress("MagicNumber")
    override fun hashCode(): Int {
        var result = 0
        result = 31 * result + name.hashCode()
        result = 31 * result + arguments.hashCode()
        result = 31 * result + childs.hashCode()
        return result
    }

    infix fun alias(name: String) {
        this.alias = name
    }
}
