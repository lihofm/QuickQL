package de.beatbrot.quickql

import de.beatbrot.quickql.model.RootQuery
import de.beatbrot.quickql.visitor.GraphQLVisitor
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals


fun <T> assertDeepEquals(first: T, second: T) {
    assertEquals(first, second)
    assertEquals(first!!.hashCode(), second!!.hashCode())
    assertEquals(first.toString(), second.toString())
}

fun <T> assertDeepNotEquals(first: T, second: T) {
    assertNotEquals(first, second)
    assertNotEquals(first!!.hashCode(), second!!.hashCode())
    assertNotEquals(first.toString(), second.toString())
}

fun checkCorrectSerialization(query: RootQuery, expected: String) {
    val result = GraphQLVisitor(rootQuery = query).generate()
    assertEquals(result, expected)
}