package de.beatbrot.quickql.request.executor

import okhttp3.HttpUrl
import okhttp3.Request

class HttpGetQueryExecutor(url: String, private val parameterName: String, wrapInJson: Boolean = false) :
        OkHttpQueryExecutor(url, wrapInJson) {
    override fun createRequest(string: String): Request {
        val builder: HttpUrl.Builder = HttpUrl.parse(url)!!.newBuilder()
        builder.addQueryParameter(parameterName, string)
        val url = builder.build()
        return Request.Builder()
                .url(url)
                .build()
    }
}
