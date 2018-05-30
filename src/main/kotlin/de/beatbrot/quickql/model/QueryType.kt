package de.beatbrot.quickql.model

enum class QueryType {
    QUERY, MUTATION, SUBSCRIPTION, NONE;

    fun toGraphQlString(): String {
        return if (this == NONE) {
            ""
        } else {
            name.toLowerCase() + " "
        }
    }
}
