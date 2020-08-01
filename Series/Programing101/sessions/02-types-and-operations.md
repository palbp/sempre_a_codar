# Types and Operations

## Script / Outline (45 mins)([video](https://www.youtube.com/watch?v=jJjUX7meSag&list=PL8XxoCaL3dBiJ_djQKKbbI4uN081F7Sgw))
1. Type system: introduction
   1. Its purpose, revisited
   2. Type inference
   3. Intrinsic types and operations, revisited
   4. Custom data types: data classes
2. Demo: revisiting the [moving ball](https://gist.github.com/palbp/ad003b75b36d2d4f1b828bf695c81820)

## Exercises (10 mins) ([solution](./solutions/02-types-and-operations.md))
1. Which of the following instructions cause type verification errors?
   * `val aValue = "25"`
   * `val aValue: Int = "25"`
   * `val aValue: Int = 25.0`
   * `val aValue: Double = 25.0`
  
2. Consider the following _incomplete_ program:    
    ```kotlin
    fun main() {
        val aLocation = Location(x = 50, y = 20)
        println(aLocation)
    }
    ```
    1. When the _verifier_ analyses the previous program, it produces the error `Unresolved reference: Location`. Why?
    2. Complete the program so that the verifier accepts it as a _correct program_.
    3. Once corrected, what is the result of the program's execution?
 