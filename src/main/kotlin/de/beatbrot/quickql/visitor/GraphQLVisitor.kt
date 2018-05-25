package de.beatbrot.quickql.visitor

import de.beatbrot.quickql.model.InnerQuery
import de.beatbrot.quickql.model.RootQuery
import java.util.*

class GraphQLVisitor(rootQuery: RootQuery) : QueryVisitor(rootQuery) {
    private val builder: StringBuilder = StringBuilder()

    companion object {
        private const val indent = "    " // 4 Spaces
    }

    fun generate(): String {
        visitAll()
        return builder.toString()
    }

    override fun visitRootQuery(rootQuery: RootQuery) {
        builder += "{\n"
        super.visitRootQuery(rootQuery)
        builder += "}\n"
    }

    override fun visitQuery(query: InnerQuery, level: Int) {
        builder += "${getIndent(level)}${query.name}"
        if (query.parameter.isNotEmpty()) {
            val joiner = StringJoiner(", ", "(", ")")
            query.parameter.forEach {
                joiner.add("${it.key}: ${it.value}")
            }
            builder += joiner.toString()
        }
        if (query.elements.isNotEmpty()) {
            appendChilds(query, level)
        } else {
        }
        builder += "\n"
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