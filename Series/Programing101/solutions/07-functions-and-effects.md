# Functions and effects

## Exercises solutions

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

      __Answer:__    

      ```kotlin
      fun measurePatientTemperature(): Double = (Random.nextDouble(31.0, 42.0) * 10).toInt() / 10.0
      ```

   2. Define the function (or is it an effect?) `getDiagnostic` so that it produces one of the following strings: 
      * `"you are ok"`, if the received temperature is between `36.0` and `37.5`
      * `"you have a fever"`, if the received temperature is between `37.5` and `41.0`
      * `"you have hypothermia"`, if the received temperature is between `32.0` and `35.0`
      * `"get a doctor, now!"`, if the received temperature is over `41.0` or below `32.0`

      __Answer:__    

      ```kotlin
      fun getDiagnostic(temperature: Double): String = when {
              temperature >= 36.0 && temperature < 37.5 -> "you are ok"
              temperature >= 37.5 && temperature < 41.0 -> "you have a fever"
              temperature >= 32.0 && temperature < 35.0 -> "you have hypothermia"
              else -> "get a doctor, now!"
      }
      ```

   3. How would you classify each of the following blocks of code? Function or effect?
   
      __Answer:__    

      * `measurePatientTemperature` is an effect because it breaks the substitution principle
      * `getDiagnostic` is a function because it **does not** break the substitution principle
      
  
