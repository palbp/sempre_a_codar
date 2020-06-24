# Types and Operations

## Exercises solutions
1. Which of the following instructions cause type verification errors?
   * `val aValue = "25"` 
   * `val aValue: Int = "25"` <-- Error: `Type mismatch: inferred type is String but Int was expected`
   * `val aValue: Int = 25.0` <-- Error: `The floating-point literal does not conform to the expected type Int`
   * `val aValue: Double = 25.0`
  
2. Consider the following _incomplete_ program:    
    ```kotlin
    fun main() {
        val aLocation = Location(x = 50, y = 20)
        println(aLocation)
    }
    ```
    1. When the _verifier_ analyses the previous program, it produces the error `Unresolved reference: Location`. Why?    

        __Answer:__    
        The symbol `Location` is not defined. In this case the symbol corresponds to a type name.

    2. Complete the program so that the verifier accepts it as a _correct program_.
        
        __Answer:__
        ```kotlin
        data class Location(val x: Int, val y: Int)

        fun main() {
            val aLocation = Location(x = 50, y = 20)
            println(aLocation)
        }
        ```
    3. Once corrected, what is the result of the program's execution?

        __Answer:__   
        The execution displays `Location(x=50, y=20)` on the console.

        _Additional note:_   
        If you forgot the `data` qualifier in the data class definition, the result was something like this: `Location@5451c3a8`. Not very useful, right? Don't fret on it for now. We will get back on this later. 

