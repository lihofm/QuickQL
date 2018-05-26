import de.beatbrot.quickql.model.RootQuery
import de.beatbrot.quickql.request.executor.HttpPostQueryExecutor


fun main(args: Array<String>) {
    val query = RootQuery {
        ql["author"]("id" to "1", "ide" to "949", alias = "Hello") {
            ql["firstName"]
        }
    }

    val result = HttpPostQueryExecutor("https://1jzxrj179.lp.gql.zone/graphql", true).execute(query)
    println(result)
}
