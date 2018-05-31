import de.beatbrot.quickql.query

fun main(args: Array<String>) {
    val example = query("name") {
        ql["name"]("id" to 12) alias "Hello"
    }
    println(example)
}
