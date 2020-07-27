# Functions and effects

## Script / Outline (75 mins) ([video](https://www.youtube.com/watch?v=4PokvOrVI4o&list=PL8XxoCaL3dBiJ_djQKKbbI4uN081F7Sgw&index=9&t=0s))
1. Elements of code reuse and code organization
   1. Functions
   2. Effects
2. Mental models for execution (revisited)
   1. Substitution model: data flow
   2. Execution control: state machine

## Exercises (20 mins) - (solution coming soon)
1. Consider the following __incomplete__ program. The program should display a diagnostic given the patient's temnperature (expressed in degrees Celsius).
   
    ```kotlin
    import kotlin.random.Random

    fun main() {
        val bodyTemperature = measurePatientTemperature()
        val diagnostic = getDiagnostic(bodyTemperature)
        println("Your temperature is ${bodyTemperature} --> ${diagnostic}")
        println(if (diagnostic == "you are ok") "Let's rock! 🤘" else "🤒")
    }
    ```   
   1. Define the function (or is it an effect?) `measurePatientTemperature` so that it produces a random number in the interval `[31.0, 42.0[` each time it's evaluated. The returned value has, at most, one decimal place.

      **Suggestion**: [Check out the previous exercises](solutions/06-evaluation-and-execution.md)

   2. Define the function (or is it an effect?) `getDiagnostic` so that it produces one of the following strings: 
      * `"you are ok"`, if the received temperature is between `36.0` and `37.5`
      * `"you have a fever"`, if the received temperature is between `37.5` and `41.0`
      * `"you have hypothermia"`, if the received temperature is between `32.0` and `35.0`
      * `"get a doctor, now!"`, if the received temperature is over `41.0` or below `32.0`

   3. How would you classify each of the following blocks of code? Function or effect?
      * `measurePatientTemperature`
      * `getDiagnostic`
      
      **Hints**: Remember that a block of code is said to be a function if it does not break the substitution principle. If it does, we name it an effect. To check if the substitution principle is broken, substitute all occurences of `bodyTemperature` by `measurePatientTemperature()`. Has the program behaviour been affected? What if, instead, we substitute all occurences of `diagnostice` by `getDiagnostic(bodyTemperature)`. Has the program behaviour been affected this time?

  
