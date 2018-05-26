package de.beatbrot.quickql.visitor

import de.beatbrot.quickql.checkCorrectSerialization
import de.beatbrot.quickql.model.QueryType
import de.beatbrot.quickql.model.RootQuery
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object GraphQLVisitorTest : Spek({
    given("We have Query-Objects and use the Visitor to generate valid GraphQL") {
        on("We use a very simple object") {
            val query = RootQuery {
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
            val query = RootQuery {
                ql["human"]{
                    ql["name"]
                }
            }
            it("Should equal to the expected String") {
                checkCorrectSerialization(query, "{\n" +
                        "    human {\n" +
                        "        name\n" +
                        "    }\n" +
                        "\n" +
                        "}\n")
            }
        }
        on("We use parameters on our object") {
            val query = RootQuery {
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
            val query = RootQuery {
                ql["demo"](alias = "First")
                ql["demo"](alias = "Second")
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
            val query = RootQuery(name = "demo") {
                ql["demo"]
            }

            it("Should begin with \"demo {") {
                val expected = "demo {\n" +
                        "    demo\n" +
                        "}\n"
                checkCorrectSerialization(query, expected)
            }
        }

        on("Specify query type") {
            val query = RootQuery(QueryType.QUERY, "demo") {
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
