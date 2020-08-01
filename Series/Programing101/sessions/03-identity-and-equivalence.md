# Identity and Equivalence 

## Script / Outline (25 mins) ([video](https://www.youtube.com/watch?v=UNWxFE18nXc&list=PL8XxoCaL3dBiJ_djQKKbbI4uN081F7Sgw))
1. Values and objects, revisited
2. Identity and equivalence
   1. What distiguishes them
   2. Structural equality (a.k.a equivalence)
      1. Operators `==` and `!=`
   3. Referential equality (a.k.a identify)
      1. Operators `===` and `!==`
3. Why do we care? (preparing for the next subject)

## Exercises (20 mins) ([solution](./solutions/03-identity-and-equivalence.md))
1. Consider the following program:
    ```kotlin
    data class Location(val x: Int, val y: Int)

    fun main() {
        val aValue = 20
        val aLocation = Location(aValue, 20)
        val anotherLocation = Location(20, 20)
        val someLocation = aLocation

        // ... other stuff not relevant to the question
    }
    ```
   1. Draw a diagram of the existing variables, values and objects (the program's state)
   2. What is the value of each of the following expressions? 
      * `aValue == aLocation.x`
      * `aValue === aLocation.x`
      * `aLocation.x != aLocation.y`
      * `aLocation.x !== aLocation.y`   
      * `anotherLocation == aLocation`
      * `anotherLocation === aLocation`
      * `someLocation == aLocation`
      * `someLocation === aLocation`
      
      **Suggestion**: Remember that `println(...)` can be used to display the value of an expression. Experiment with the playground and make sure that your *mental model*, expressed by the diagram drawn in the previous question, is able to explain the results.
  
2. Consider the following program:    
    ```kotlin
    data class Location(val x: Int, val y: Int)
    data class Ball(val center: Location, val radius: Int)

    fun main() {
      val aLocation = Location(20, 20)
        val aBall = Ball(aLocation, 15)
        val anotherBall = Ball(aLocation, 15)
        val yetAnotherBall = Ball(Location(20, 20), 15)

        // ... other stuff not relevant to the question   
    }
    ```    
    1. What is the value of each of the following expressions?
         * `aBall.center == anotherBall.center`
         * `aBall.center === anotherBall.center`
         * `aBall.center == yetAnotherBall.center`
         * `aBall.center === yetAnotherBall.center`   
      
        **Suggestion**: Draw the diagram of the program's state (your mental model) to make sure you get it right. And remember that you can experiment with `println(...)`    

    2. Consider the type definitions `Location` and `Ball` of the previous program. For two arbitrary `Ball` instances, referred by variables `ball1` and `ball2`, what is the expression that evaluates to `true` whenever the two instance's centers have the same coordinates?
 