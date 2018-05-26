package de.beatbrot.quickql.model

import de.beatbrot.quickql.assertDeepEquals
import de.beatbrot.quickql.assertDeepNotEquals
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertFalse

object RootQueryTest : Spek({
    given("We have root-queries that we want to compare") {
        val first = RootQuery {
            ql["inner"]
        }
        on("The root-queries are the same") {
            val second = RootQuery {
                ql["inner"]
            }
            it("Should return true for equals, hashCode and toString") {
                assertDeepEquals(first, second)
            }
        }
        on("The root-queries are not the same") {
            val second = RootQuery {
                ql["outer"]
            }
            it("Should return false for equals, hashCode and toString") {
                assertDeepNotEquals(first, second)
            }
        }
        on("The objects we are comparing are completely different") {
            val firstRt = RootQuery {

            }
            val builder = QueryBuilder()
            builder["demo"]
            val second = builder.queries[0]

            it("Must return false") {
                assertFalse(firstRt.equals(second))
            }
        }
    }
})
