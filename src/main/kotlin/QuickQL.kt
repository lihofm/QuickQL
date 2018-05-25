import de.beatbrot.quickql.model.RootQuery
import de.beatbrot.quickql.visitor.GraphQLVisitor


fun main(args: Array<String>) {
    val query = RootQuery("http://graphql.nodaljs.com/graph")
    query {
        ql["users"]
        ql["humans"]("id" to 0.5) {
            ql["name"]
            ql["id"]
            ql["demo"]
        }
    }

    println(GraphQLVisitor(query).generate())
}