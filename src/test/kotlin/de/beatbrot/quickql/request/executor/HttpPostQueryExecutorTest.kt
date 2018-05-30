package de.beatbrot.quickql.request.executor

import de.beatbrot.quickql.model.RootQuery
import de.beatbrot.quickql.query
import de.beatbrot.quickql.request.executor.exception.HttpStatusException
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.api.dsl.xon
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

object HttpPostQueryExecutorTest : Spek({
    given("We have a valid Query and want to request it on a GraphQL-Server") {
        xon("We use a very simple query to see if the basics work") {
            val query = RootQuery {
                ql["hello"]
            }
            val executor: QueryExecutor = HttpPostQueryExecutor("https://launchpad.auth0-extend.com/api/run/launchpad/launchpad-starter-code", true)
            it("Should return without exception and should give the expected response") {
                val result = executor.execute(query)
                assertEquals("{\"data\":{\"hello\":\"Hello world!\"}}", result)
            }
        }
        xon("We use a more complex query with parameters") {
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
                val expected = "{\"data\":{\"author\":{\"id\":1,\"firstName\":\"Tom\",\"lastName\":\"Coleman\",\"posts\":[{\"title\":\"Introduction to GraphQL\"}]}}}"
                assertEquals(expected, result)
            }
        }

        on("We receive an error code upon request") {
            val rootQuery = query {
                ql["hello"]
            }

            val srv = MockWebServer()
            val response = MockResponse()
            response.setResponseCode(500)
            for (i: Int in 0..22) {
                srv.enqueue(response)
            }
            srv.start()
            val executor = HttpPostQueryExecutor("http://localhost:${srv.port}")

            it("Should exit with a exception") {
                assertFailsWith(HttpStatusException::class) {
                    executor.execute(rootQuery)
                    srv.shutdown()
                }
            }

        }

    }
})
