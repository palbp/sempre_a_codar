# Summary

## Script / Outline (30 mins) ([video](https://www.youtube.com/watch?v=QsI1yOpmXss&list=PL8XxoCaL3dBiJ_djQKKbbI4uN081F7Sgw))
1. Revisiting the elements of the programmer´s universe
   
   1. Data representations, a cornerstone of our mental model
      1. Representations are built through **composition** of primitive values into aggregates (objects) which, in turn, can be further composed into other aggregates
      2. The type system is used to define those representations and to enforce the rules that those representations must abide by
  
   2. Behaviour (what a program does)
      1. Simple expressions (values and variables) and their **composition** through operations into composite expressions which, in turn, can be further composed into other composite expressions
      2. Execution models: Substitution model (data flow) and execution control (program flow) 
      3. Basic elements of code reuse and code organization: functions and effects
   
   3. Leveraging existing code
      1. We do not reinvent the wheel: we undersand it! We could build it, but most of the times we simply use it just because its there. And with it, we build wonderful contraptions. 🧙🔮

          "The magic of myth and legend has come true in our time. One types the correct incantation on a keyboard, and a display screen comes to life, showing things that never were nor could be." - [Frederick P. Brooks Jr., The Mythical Man-Month: Essays on Software Engineering](https://en.wikipedia.org/wiki/The_Mythical_Man-Month)    

      2. Identifying the targets of our program
         1. Where will it run?
         2. Which are the minimal requirements?

      3. The programming model: 
         1. It's the set of laws that govern the programmer's universe (for a particular technology stack)
         2. It determines the programmer's experience because those rules cannot be ignored. If they are, we find ourselves where ["Here be dragons"](https://en.wikipedia.org/wiki/Here_be_dragons)
         3. By leveraging existing code (e.g. frameworks) we usually also choose a programming model.

2. Demo: [The bouncing ball](https://gist.github.com/palbp/55f8477f232b06f8bfdf7d30f4735d2a)
   1. Can we understand it a little better now?


## Exercises (30 mins) - ([solution](solutions/09-summary-the-programmer-universe.md))
1. Consider the program [The bouncing ball](https://gist.github.com/palbp/55f8477f232b06f8bfdf7d30f4735d2a) explained during the session. Our goal is to make changes to the program so that it behaves as shown in this [video](assets/09-goal.mp4). What we see in the video is the ball's behaviour when we apply gravity to it. Cool, right? =)

   1. Modify the program so that the ball remains still at the center of the canvas.    
      
      __Hints__    
      Look carefully at the `createBall` function. Modify it so that the behaviour is as expected and yes, **you only need to change this function**. Remember that a still ball is represented by a ball with zero velocity.

   2. Next, we need to represent acceleration. Define the `Acceleration` class so that we are able to represent the ball's velocity variations. Modify the class `Ball` so it includes a new property, `acceleration`, of the newly created type `Acceleration`.
   
      __Hints__    
      Acceleration is represented by a vector with two coordinates, `dvx` and `dvy`, which represent the variation to be applied to the ball's velocity. You will need to modify all the code that creates new ball instances so that it includes the new ball property. Once you complete those changes, the verifier should accept the new program version. The execution result didn't change, though.

   3. Finally, let's apply acceleration to the ball and see what happens. Change the implementation of `move` function so that the ball's velocity changes over time according to the ball's acceleration. Also change the `createBall` function so that the ball is created with an acceleration simulating gravity towards the bottom of the canvas, like so `Acceleration(0.0, 1.0)`. Once you're done you should see the end result, as shown in this [video](assets/09-goal.mp4). 

      __Hints__    
      Remember that velocity is a vector representing location's variation over time. Similarly, acceleration is a vector representing velocity's variation over time. Applying acceleration to a ball's velocity is analogous to applying velocity to the ball's location.

   4. Experiment with different acceleration vectors and see what happens. For example, try this one: `Acceleration(0.2, 1.0)`. Cool, right? =)