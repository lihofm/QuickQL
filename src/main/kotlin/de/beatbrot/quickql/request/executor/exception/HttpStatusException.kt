package de.beatbrot.quickql.request.executor.exception

class HttpStatusException(statusCode: Int, cause: Throwable? = null) :
        RuntimeException("Server returned status code: $statusCode", cause)
