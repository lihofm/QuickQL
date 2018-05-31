package de.beatbrot.quickql.model

enum class OperationType {
    QUERY, MUTATION, SUBSCRIPTION;

    fun toGraphQlString() = name.toLowerCase() + " "
}
