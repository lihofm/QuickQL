package de.beatbrot.quickql.request.executor

import de.beatbrot.quickql.model.RootQuery
import de.beatbrot.quickql.visitor.GraphQLVisitor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.apache.commons.text.StringEscapeUtils
import java.io.IOException

abstract class OkHttpQueryExecutor(protected val url: String, private val wrapInJson: Boolean = false) : QueryExecutor {

    @Throws(IOException::class)
    override fun execute(rootQuery: RootQuery): String {
        val string = GraphQLVisitor(rootQuery).generate()

        val client = OkHttpClient()

        val request = createRequest(if (wrapInJson) wrapInJson(string) else string)

        val response = client.newCall(request).execute()
        return response.body()!!.string()
    }

    protected abstract fun createRequest(string: String): Request

    private fun wrapInJson(input: String): String {
        return "{\n" +
                "\"query\": \"${StringEscapeUtils.escapeJson(input)}\"" +
                "\n}"
    }

}
