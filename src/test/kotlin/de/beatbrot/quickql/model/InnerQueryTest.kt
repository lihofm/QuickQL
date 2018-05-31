package de.beatbrot.quickql.model

import de.beatbrot.quickql.assertDeepEquals
import de.beatbrot.quickql.assertDeepNotEquals
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertFalse

object InnerQueryTest : Spek({
    given("We have two inner queries and want to compare them to each other") {
        on("Both queries are the same") {
            var builder = OperationBuilder()
            builder["demo"]{
                ql["world"]
            }
            val first: Field = builder.queries[0]
            builder = OperationBuilder()
            builder["demo"]{
                ql["world"]
            }
            val second: Field = builder.queries[0]

            it("should return true for equals, hashCode and toString-comparison") {
                assertDeepEquals(first, second)
            }
        }
        on("The queries are not the same") {
            var builder = OperationBuilder()
            builder["demo"]
            val first = builder.queries[0]

            builder = OperationBuilder()

            builder["demo"]{
                ql["child"]
            }

            val second = builder.queries[0]
            it("Should return false for equals, hashCode and to-String-comparison") {
                assertDeepNotEquals(first, second)
            }
        }
        on("We try to compare inner and RootQueries") {
            val builder = OperationBuilder()
            builder["demo"]
            val first = builder.queries[0]

            val second = RootOperation {
            }

            it("Should return false because we compare two completely different objects") {
                @Suppress("ReplaceCallWithBinaryOperator")
                assertFalse(first.equals(second))
            }
        }
    }
})
