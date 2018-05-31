package de.beatbrot.quickql

import de.beatbrot.quickql.model.OperationType
import de.beatbrot.quickql.model.RootOperation
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object QuickQueryTest : Spek({
    given("We want a even quicker way to create a query") {
        on("We want to use query") {
            val first = query {
                ql["Hello"]
            }
            val second = RootOperation(OperationType.QUERY) {
                ql["Hello"]
            }

            it("Should result in the same object") {
                assertDeepEquals(first, second)
            }
        }

        on("We want to use subscription") {
            val first = subscription {
                ql["hello"]
            }
            val second = RootOperation(OperationType.SUBSCRIPTION) {
                ql["hello"]
            }
            it("Should give the same object") {
                assertDeepEquals(first, second)
            }
        }
        on("We want to use mutation") {
            val first = mutation {
                ql["hello"]
            }
            val second = RootOperation(OperationType.MUTATION) {
                ql["hello"]
            }
            it("Should be the same") {
                assertDeepEquals(first, second)
            }
        }
        on("We want to give it a name") {
            val first = query("demo") {
                ql["hello"]
            }
            val second = RootOperation(OperationType.QUERY, "demo") {
                ql["hello"]
            }

            it("Should give the same object") {
                assertDeepEquals(first, second)
            }
        }
    }
})
