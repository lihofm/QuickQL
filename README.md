# QuickQL
QuickQL tries to implement a subset of the GraphQL query-language as a DSL in Kotlin.

## Support
- Query types: query, mutation, subscription, implicit(none)
- Parameters: Wraps strings automatically in quotation-marks. Numbers don't get wrapped
- Aliases: Aliases can get applied via the ``alias`` infix-function

## Usage
- Simple Queries
    ````kotlin
    fun simpleQuery() {
        val exampleQuery = query {
            ql["Hello"]
        }
        val exampleSubscription = subscription {
            ql["Hello"]
        }
        val exampleMutation = mutation {
            ql["Hello"]
        }
    }
    ````
    This code shows how simple it can be to use QuickQL. Just call ``query`` and insert childs with the ``ql``-property.
- Named queries and parameters
    ````kotlin
    fun namedQuery() {
      val example = subscription("RequestPeter") {
          ql["Human"]("name" to "Peter", "age" to 33)
      }
    }
    ````
    The query above is a bit more complex. Queries can have a name (e.g. "RequestPeter").
    Also, it is possible to use parameters on inner queries by supplying ``Pair``-objects after the ``get``-Block.
    If parameters are not numbers, they get wrapped in quotation-marks.
- Nested queries and aliases
    ````kotlin
    fun parameters() {
        query {
            ql["Human"]{
                ql["name"]
                ql["age"]
          } alias "HumanRequest"
        }
    }
    ````
