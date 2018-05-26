package de.beatbrot.quickql.request.executor

import de.beatbrot.quickql.model.RootQuery
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

object HttpPostQueryExecutorTest : Spek({
    given("We have a valid Query and want to request it on a GraphQL-Server") {
        on("We use a very simple query to see if the basics work") {
            val query = RootQuery {
                ql["hello"]
            }
            val executor: QueryExecutor = HttpPostQueryExecutor("https://launchpad.auth0-extend.com/api/run/launchpad/launchpad-starter-code", true)
            it("Should return without exception and should give the expected response") {
                val result = executor.execute(query)
                assertEquals("{\"data\":{\"hello\":\"Hello world!\"}}", result)
            }
        }
        on("We use a more complex query with parameters") {
            val query = RootQuery {
                ql["author"]("id" to 1) {
                    ql["id"]
                    ql["firstName"]
                    ql["lastName"]
                    ql["posts"]{
                        ql["title"]
                    }
                }
            }

            val executor: QueryExecutor = HttpPostQueryExecutor("https://1jzxrj179.lp.gql.zone/graphql")
            it("Should return without exception and should give the expected response") {
                val result = executor.execute(query)
                assertEquals("{\"data\":{\"author\":{\"id\":1,\"firstName\":\"Tom\",\"lastName\":\"Coleman\",\"posts\":[{\"title\":\"Introduction to GraphQL\"}]}}}", result)
            }
        }
    }
})