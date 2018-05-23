import de.beatbrot.quickql.model.RootQuery


fun main(args: Array<String>) {
    val query = RootQuery("http://graphql.nodaljs.com/graph")
    query {
        ql["users"]
        ql["humans"]("id" to "1000") {
            ql["name"]
        }
    }

    println(query)
}