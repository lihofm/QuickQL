package de.beatbrot.quickql

import de.beatbrot.quickql.antlr.GraphQLLexer
import de.beatbrot.quickql.antlr.GraphQLParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream


object GraphQLVerifier {
    fun verify(input: String): Boolean {
        return try {
            val inStream = CharStreams.fromString(input)
            val lexer = GraphQLLexer(inStream)
            val tokenStream = CommonTokenStream(lexer)
            val parser = GraphQLParser(tokenStream)
            parser.document()
            parser.numberOfSyntaxErrors == 0
        } catch (e: Throwable) {
            e.printStackTrace()
            false
        }
    }

    fun assertValid(input: String) {
        if (!verify(input)) {
            throw AssertionError("The provided input is not valid GraphQL:\n$input")
        }
    }
}
