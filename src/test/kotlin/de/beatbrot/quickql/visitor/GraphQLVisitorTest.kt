package de.beatbrot.quickql.visitor

import de.beatbrot.quickql.checkCorrectSerialization
import de.beatbrot.quickql.model.OperationType
import de.beatbrot.quickql.model.RootOperation
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object GraphQLVisitorTest : Spek({
    given("We have Operation-Objects and use the Visitor to generate valid GraphQL") {
        on("We use a very simple object") {
            val query = RootOperation {
                ql["human"]
            }
            it("Should equal to the expected String") {
                val expected = "{\n" +
                        "    human\n" +
                        "}\n"
                checkCorrectSerialization(query, expected)
            }
        }
        on("We try to nest objects in the query") {
            val query = RootOperation {
                ql["human"]{
                    ql["name"]
                }
            }
            it("Should equal to the expected String") {
                checkCorrectSerialization(query, "{\n" +
                        "    human {\n" +
                        "        name\n" +
                        "    }\n" +
                        "}\n")
            }
        }
        on("We use parameters on our object") {
            val query = RootOperation {
                ql["intValue"]("id" to 123)
                ql["doubleValue"]("id" to 40.3)
                ql["stringValue"]("id" to "Hello")
            }
            it("Should apply the quotation-marks according to the data-type and equal the expected String") {
                val expected = "{\n" +
                        "    intValue(id: 123)\n" +
                        "    doubleValue(id: 40.3)\n" +
                        "    stringValue(id: \"Hello\")\n" +
                        "}\n"
                checkCorrectSerialization(query, expected)
            }
        }
        on("We specify an alias for two identical queries") {
            val query = RootOperation {
                ql["demo"] alias "First"
                ql["demo"] alias "Second"
            }
            it("Should prepend the queries with \"First: \"/\"Second: \"") {
                val expected = "{\n" +
                        "    First: demo\n" +
                        "    Second: demo\n" +
                        "}\n"
                checkCorrectSerialization(query, expected)
            }
        }
        on("Specify a name for the query") {
            val query = RootOperation(name = "demo") {
                ql["demo"]{
                    ql["child"]
                }
            }

            it("Should begin with \"demo {") {
                val expected = "query demo {\n" +
                        "    demo {\n" +
                        "        child\n" +
                        "    }\n" +
                        "}\n"
                checkCorrectSerialization(query, expected)
            }
        }

        on("Specify query type") {
            val query = RootOperation(OperationType.QUERY, "demo") {
                ql["demo"]
            }
            it("Should begin with \"query demo\" {") {
                val expected = "query demo {\n" +
                        "    demo\n" +
                        "}\n"
                checkCorrectSerialization(query, expected)
            }
        }
    }
})
