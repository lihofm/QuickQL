package de.beatbrot.quickql.request.executor

import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody

class HttpPostQueryExecutor(url: String, private val wrapInJson: Boolean = true) :
        OkHttpQueryExecutor(url, wrapInJson) {
    companion object {
        private val jsonMediaType = MediaType.parse("application/json; charset=utf-8")
        private val graphQlMediaType = MediaType.parse("application/graphql; charset=utf-8")
    }

    override fun createRequest(string: String): Request {
        return Request.Builder()
                .url(url)
                .post(generateBody(string))
                .build()
    }

    private fun generateBody(string: String): RequestBody {
        return if (wrapInJson) {
            RequestBody.create(jsonMediaType, string)
        } else {
            RequestBody.create(graphQlMediaType, string)
        }
    }
}
