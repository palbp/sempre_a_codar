----   
## Milestones 
1. "Bouncing Ball" demo 
    * Use `SurfaceView` for the rendering

----   
## Goals per milestone
### Bouncing Ball
1. The model-view-controller approach
2. Present the physics behind the movement (i.e. position, velocity and basic reflection)
3. Present `SurfaceView` fundamentals
4. Present and discuss the threading model to be used in the game   

----   
## Planning
### Bouncing Ball (1h30m)
1. Draft implementation of the main participants and respective dependencies (40m)   
   * Goal: Display a still ball at the center of the arena
   * `MainActivity`, `Engine`, `ArenaView` and `BouncingBall`
   * `Location` (immutable)
   * The engine's loop: `start`, `stop`, `initializeWorld` and `doStep`
2. Draft implementation of the physics engine (30m)   
   * Goal: Making the ball bounce around the arena's walls
   * `Velocity` (immutable) and movement (changes to `Location` implementation) 
3. Wrap up and prepare for what's coming (20m)   
   * Goal: Discuss the mental model and explore the animation limits
   * Class diagram (for dependency analysis)
   * Object diagram and threading model (for correctness analysis)
   * Experiment: change the ball's velocity and observe the results