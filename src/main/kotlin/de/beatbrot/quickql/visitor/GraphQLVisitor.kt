package de.beatbrot.quickql.visitor

import de.beatbrot.quickql.model.Field
import de.beatbrot.quickql.model.RootOperation
import java.util.*

class GraphQLVisitor(private val indent: String = defaultIndent) : OperationVisitor<String>() {
    companion object {
        const val defaultIndent = "    "// 4 spaces
    }

    private val builder: StringBuilder = StringBuilder()

    override fun visitAll(rootQuery: RootOperation): String {
        super.visitAll(rootQuery)
        return builder.toString()
    }

    override fun visitRootQuery(rootQuery: RootOperation) {
        if (rootQuery.name != null) {
            builder += rootQuery.type.toGraphQlString()
            builder += rootQuery.name + " "
        }
        builder += "{\n"
        super.visitRootQuery(rootQuery)
        builder += "}\n"
    }

    override fun visitQuery(query: Field, level: Int) {
        builder += getIndent(level)
        if (query.alias != null) {
            builder += "${query.alias}: "
        }
        builder += query.name
        builder += buildParameterString(query.arguments)
        if (query.childs.isNotEmpty()) {
            appendChilds(query, level)
        } else {
            builder += "\n"
        }
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

    private fun appendChilds(query: Field, level: Int) {
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
