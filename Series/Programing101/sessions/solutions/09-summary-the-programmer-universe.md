# Summary - The programmer's universe

## Exercises solutions (video)(coming soon)
1. Consider the program [The bouncing ball](https://gist.github.com/palbp/55f8477f232b06f8bfdf7d30f4735d2a) explained during the session. Our goal is to make changes to the program so that it behaves as shown in this [video](../assets/09-goal.mp4). What we see in the video is the ball's behaviour when we apply gravity to it. Cool, right? =)

   1. Modify the program so that the ball remains still at the center of the canvas.    

      __Answer:__    

      Change the `createBall` function to make it look like this:

      ```kotlin
      fun createBall(ballRadius: Double, ballColor: Color, canvas: HTMLCanvasElement) = Ball(
              Location(canvas.width / 2.0, canvas.height / 2.0),  // Changed
              ballRadius,
              Velocity(0.0, 0.0),   // Changed
              ballColor
      )
      ```

   2. Next, we need to represent acceleration. Define the `Acceleration` class so that we are able to represent the ball's velocity variations. Modify the class `Ball` so it includes a new property, `acceleration`, of the newly created type `Acceleration`.
   
      __Answer:__    

      First, define the new type, `Acceleration`, like so:
      ```kotlin
      data class Acceleration(val dvx: Double, val dvy: Double)
      ```

      Afterwards modify the `Ball` class so that all instances also have an `acceleration` property. Do not forget to modify all code locations where `Ball` intances are created.

      ```kotlin
      data class Ball(
              val center: Location,
              val radius: Double,
              val velocity: Velocity,
              val acceleration: Acceleration, // Added
              val color: Color
      )

      fun createBall(ballRadius: Double, ballColor: Color, canvas: HTMLCanvasElement) = Ball(
              Location(canvas.width / 2.0, canvas.height / 2.0),
              ballRadius,
              Velocity(0.0, 0.0),
              Acceleration(0.0, 0.0),   // Added
              ballColor
      )

      fun move(ball: Ball, canvas: HTMLCanvasElement): Ball {

          val newBall = Ball(
                  Location(ball.center.x + ball.velocity.dx, ball.center.y + ball.velocity.dy),
                  ball.radius,
                  ball.velocity,
                	ball.acceleration,  // Added
                  ball.color
          )

          return if (isInBounds(newBall, canvas)) newBall
          else Ball(
                  Location(
                          if (newBall.velocity.dx < 0) max(0.0, newBall.center.x) else min(canvas.width - newBall.radius, newBall.center.x),
                          if (newBall.velocity.dy < 0) max(0.0, newBall.center.y) else min(canvas.height - newBall.radius, newBall.center.y)
                  ),
                  newBall.radius,
                  Velocity(
                          if (!isInXBounds(newBall, canvas)) -newBall.velocity.dx else newBall.velocity.dx,
                          if (!isInYBounds(newBall, canvas)) -newBall.velocity.dy else newBall.velocity.dy
                  ),
                  newBall.acceleration,   // Added
                  newBall.color
          )
      }

      ```


   3. Finally, let's apply acceleration to the ball and see what happens. Change the implementation of `move` function so that the ball's velocity changes over time according to the ball's acceleration. Also change the `createBall` function so that the ball is created with an acceleration simulating gravity towards the bottom of the canvas, like so `Acceleration(0.0, 1.0)`. Once you're done you should see the end result, as shown in this [video](assets/09-goal.mp4). 

      __Answer:__    

      First, change the `createBall` function to make it look like this:

      ```kotlin
      fun createBall(ballRadius: Double, ballColor: Color, canvas: HTMLCanvasElement) = Ball(
              Location(canvas.width / 2.0, canvas.height / 2.0),
              ballRadius,
              Velocity(0.0, 0.0),
              Acceleration(0.0, 1.0),   // Changed
              ballColor
      )
      ```

      Finally, modify the `move` function so that the ball's acceleration is applied to its velocity, like this:

      ```kotlin
      fun move(ball: Ball, canvas: HTMLCanvasElement): Ball {

          val newBall = Ball(
                  Location(ball.center.x + ball.velocity.dx, ball.center.y + ball.velocity.dy),
                  ball.radius,
                  Velocity(ball.velocity.dx + ball.acceleration.dvx, ball.velocity.dy + ball.acceleration.dvy), // Changed
                	ball.acceleration,
                  ball.color
          )

          return if (isInBounds(newBall, canvas)) newBall
          else Ball(
                  Location(
                          if (newBall.velocity.dx < 0) max(0.0, newBall.center.x) else min(canvas.width - newBall.radius, newBall.center.x),
                          if (newBall.velocity.dy < 0) max(0.0, newBall.center.y) else min(canvas.height - newBall.radius, newBall.center.y)
                  ),
                  newBall.radius,
                  Velocity(
                          (if (!isInXBounds(newBall, canvas)) -newBall.velocity.dx else newBall.velocity.dx) + newBall.acceleration.dvx,  // Changed
                          (if (!isInYBounds(newBall, canvas)) -newBall.velocity.dy else newBall.velocity.dy) + newBall.acceleration.dvy   // Changed
                  ),
                  newBall.acceleration,   // Added
                  newBall.color
          )
      }
      ```


   4. Experiment with different acceleration vectors and see what happens. For example, try this one: `Acceleration(0.2, 1.0)`. Cool, right? =)

      __Answer__
      Just have fun with it