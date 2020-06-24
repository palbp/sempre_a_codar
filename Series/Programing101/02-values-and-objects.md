# Values and Objects 

## Script / Outline (20 mins)
1. Variable declaration and initiation
2. Values: Integer, Double, Char, String, Boolean
3. Operations with values (e.g. arithmetic, logic)
4. Demo: show the behaviour of [moving ball](https://gist.github.com/palbp/ad003b75b36d2d4f1b828bf695c81820)
5. Composing values: objects
6. Objects:
   1. Values vs objects
   2. Composing data (values and objects)
7. A mental model for data representation

## Exercises (20 mins) ([solution](./solutions/02-values-and-objects.md))
1. Consider the following program:
    ```kotlin
    fun main() {
      val aRadius = 15
      val aBall = object { 
        val x = 20
        val y = 20
        val radius = aRadius 
      }
      // ... other stuff not relevant for the question
    }
    ```
   1. What is the value of the expression `aBall.radius` ?
   2. Draw a diagram of the existing variables, values and objects (the program's state)


2. Consider the next program:    
    ```kotlin
    fun main() {
      val aPosition = object { val x = 5; val y = 20 }
      val aBall = object { 
        val position = aPosition
        val radius = 15 
      }
      // ... other stuff not relevant for the question
    }
    ```
    1. Which of the following expressions evaluate to `5` ?
        * `aPosition`
        * `aPosition.x`
        * `aBall`
        * `aBall.x`
        * `aBall.position.x`
    2. Draw a diagram of the existing variables, values and objects (the program's state)

3. Consider the following diagram of a program's state. Write the code that creates the depicted elements. 

    ![Diagram](./images/02-diagram.png "Question 3 diagram")
