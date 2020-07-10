# Mutability and Immutability

## Script / Outline (55 mins)([video](https://www.youtube.com/watch?v=U0AcqCyq3TU&list=PL8XxoCaL3dBiJ_djQKKbbI4uN081F7Sgw))
1. Mutating variables
   1. Allowing it at the variable's declaration `var`
   2. Consequences of mutability
2. Execution models: introduction
   1. Substitution model (functional style)
   2. Instruction sequence and program state (imperative style)

## Exercises (30 mins) ([solution - coming soon]())

 1. Consider the following program:
    ```kotlin
    data class Location(var x: Int, var y: Int)

    fun main() {
        val aLocation = Location(20, 20)
        val anotherLocation = Location(20, 20)
        val someLocation = aLocation

        // (A)

        anotherLocation.x = anotherLocation.x + 10
        someLocation.x = someLocation.x + 10

        // (B)

        // ... other stuff not relevant to the question
    }
    ```
   1. Draw a diagram of the the program's state at the line marked with `// (A)`.
   2. What is the value of each of the following expressions at line `// (A)`? 
      * `aLocation.x`
      * `anotherLocation.x`
      * `someLocation.x`
      * `someLocation === aLocation`
      * `someLocation === anotherLocation`
   3. Draw another diagram of the the program's state, this time at the line marked with `// (B)`. Notice the differences between both diagrams.
   4. What is the value of each of the following expressions at line `// (B)`?  
      * `aLocation.x`
      * `anotherLocation.x`
      * `someLocation.x`
      * `someLocation === aLocation`
      * `someLocation === anotherLocation`
      
      **Sugestion**: Remember that `println(...)` can be used to display the value of an expression. Experiment with the playground and make sure that your *mental model*, expressed by the diagrams you draw are able to explain the results.
  
2. Consider the following program:    
    ```kotlin
    data class Location(var x: Int, var y: Int)
    data class Ball(val center: Location, val radius: Int)

    fun main() {
        val aLocation = Location(20, 20)
        val aBall = Ball(aLocation, 15)
        val anotherBall = Ball(aLocation, 15)

        // (A)
        
        aBall.center.x = aBall.center.x + 10

        // (B)

        aBall.center = Location(aBall.center.x + 10, aBall.center.y)

        // (C)

        // ... other stuff not relevant to the question   
    }
    ```    
    1. What is the value of each of the following expressions at each annotated line (e.g. at `// (A)`, `// (B)` and `// (C)`?
         * `aBall.center.x`
         * `anotherBall.center.x`
      
        **Sugestion**: Draw the diagrams of the program's state (your mental model) to make sure you get it right. And remember that you can experiment with `println(...)`   

    2. How many instances of type `Location` are created during the program's execution?
