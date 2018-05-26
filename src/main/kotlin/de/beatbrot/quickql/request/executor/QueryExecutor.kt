package de.beatbrot.quickql.request.executor

import de.beatbrot.quickql.model.RootQuery
import java.io.IOException

interface QueryExecutor {
    @Throws(IOException::class)
    fun execute(rootQuery: RootQuery): String
}
