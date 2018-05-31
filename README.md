# QuickQL
QuickQL tries to implement a subset of the GraphQL operation-language as a DSL in Kotlin.

## Support
- Query types: operation, mutation, subscription, implicit(none)
- Parameters: Strings are automatically wrapped in quotation-marks. Numbers don't get wrapped
- Aliases: Aliases can get applied via the ``alias`` infix-function

## Usage
### Simple Queries
````kotlin
fun simpleQuery() {
    val exampleQuery = operation {
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

This code shows how simple it can be to use QuickQL. Just call ``operation`` and insert childs with the ``ql``-property.
### Named queries and arguments
````kotlin
fun namedQuery() {
  val example = subscription("RequestPeter") {
      ql["Human"]("name" to "Peter", "age" to 33)
  }
}
````
The operation above is a bit more complex. Queries can have a name (e.g. "RequestPeter").
Also, it is possible to use parameters on inner queries by supplying ``Pair``-objects after the ``get``-Block.
If parameters are not numbers, they get wrapped in quotation-marks.
### Nested queries and aliases
````kotlin
fun parameters() {
    operation {
        ql["Human"]{
            ql["name"]
            ql["age"]
      } alias "HumanRequest"
    }
}
````
QuickQL of course supports nested queries and aliases. The element "Human" has two childs: "name" and "age".

An alias can be specified with the ``alias`` infix-function. Just append alias "Name" to your inner operation

## To-Do
- Implement Fragments
- Add support for parsing Strings
- Add a HTTP Client

## Used Libraries
- [spek](https://github.com/spekframework/spek) - Modified BSD
