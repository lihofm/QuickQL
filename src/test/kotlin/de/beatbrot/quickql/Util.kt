package de.beatbrot.quickql

import de.beatbrot.quickql.model.RootOperation
import de.beatbrot.quickql.visitor.GraphQLVisitor
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


fun <T> assertDeepEquals(first: T, second: T) {
    assertEquals(first, second)
    assertEquals(first!!.hashCode(), second!!.hashCode())
    assertEquals(first.toString(), second.toString())
    if (first is RootOperation) {
        GraphQLVerifier.assertValid(first.toString())
    }
}

fun <T> assertDeepNotEquals(first: T, second: T) {
    assertNotEquals(first, second)
    assertNotEquals(first!!.hashCode(), second!!.hashCode())
    assertNotEquals(first.toString(), second.toString())
}

fun checkCorrectSerialization(query: RootOperation, expected: String) {
    val result = GraphQLVisitor().visitAll(query)
    GraphQLVerifier.assertValid(result)
    assertEquals(expected, result)
}
