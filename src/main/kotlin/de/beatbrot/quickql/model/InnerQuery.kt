package de.beatbrot.quickql.model

class InnerQuery(private val name: String) : Query() {
    var parameter: MutableMap<String, String> = HashMap()

    operator fun get(name: String) {
        ql[name]
    }

    operator fun invoke(vararg param: Pair<String, String>, any: Query.() -> Unit) {
        param.forEach {
            with(it) {
                parameter[first] = second
            }
        }
        invoke(any)
    }

    override fun toString(): String {
        return "Query: $name, $elements"
    }
}