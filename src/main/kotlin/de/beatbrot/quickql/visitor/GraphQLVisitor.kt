package de.beatbrot.quickql.visitor

import de.beatbrot.quickql.model.InnerQuery
import de.beatbrot.quickql.model.RootQuery
import java.util.*

class GraphQLVisitor(rootQuery: RootQuery, private val indent: String = "    ") : QueryVisitor(rootQuery) {
    private val builder: StringBuilder = StringBuilder()

    fun generate(): String {
        visitAll()
        return builder.toString()
    }

    override fun visitRootQuery(rootQuery: RootQuery) {
        builder += rootQuery.type.toGraphQlString()
        if (rootQuery.name.isNotEmpty())
            builder += rootQuery.name + " "
        builder += "{\n"
        super.visitRootQuery(rootQuery)
        builder += "}\n"
    }

    override fun visitQuery(query: InnerQuery, level: Int) {
        builder += getIndent(level)
        if (query.alias.isNotEmpty()) {
            builder += "${query.alias}: "
        }
        builder += query.name
        builder += buildParameterString(query.parameter)
        if (query.elements.isNotEmpty()) {
            appendChilds(query, level)
        }
        builder += "\n"
    }

    private fun buildParameterString(parameters: Map<String, String>): String {
        return if (parameters.isNotEmpty()) {
            val joiner = StringJoiner(", ", "(", ")")
            parameters.forEach {
                joiner.add("${it.key}: ${it.value}")
            }
            joiner.toString()
        } else {
            ""
        }

    }

    private fun appendChilds(query: InnerQuery, level: Int) {
        builder += " {\n"
        super.visitQuery(query, level)
        builder += "${getIndent(level)}}\n"
    }

    private fun getIndent(level: Int): String {
        val myBuilder = StringBuilder(level * indent.length)
        for (i in 1..level) {
            myBuilder += indent
        }
        return myBuilder.toString()
    }

    private operator fun StringBuilder.plusAssign(other: CharSequence) {
        this.append(other)
    }
}
