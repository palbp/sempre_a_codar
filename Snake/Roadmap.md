----   
## Milestones 
1. "Bouncing Ball" demo 
    * Use `SurfaceView` for the rendering

2. "Snake v0.1.0"
    * The snake is comprised of only its head
    * It moves along the screen according to the user actions
    
3. "Snake v0.2.0"
   * Snake dies when it collides with the arena's boundaries
   * The arena bounds are easily identifiable: e.g. its background has a distinct look
   * Arena background and moving actors implemented in different layered views
   
4. "Snake v0.3.0"
   * Apples appear randomly in the arena, one at a time
   * Apples are eaten by the Snake when it collides with them. Whenever an apple is eaten, it 
   disappears
   * The Snake does not grow yet 

5. "Snake v0.4.0"
   * The Snake grows whenever it eats an apple
    
----   
## Goals per milestone
### Bouncing Ball
1. The model-view-controller approach
2. Present the physics behind the movement (i.e. position, velocity and basic reflection)
3. Present `SurfaceView` fundamentals
4. Present and discuss the threading model to be used in the game   

### Snake v0.1.0
1. Make the application full screen and with landscape orientation
2. Implement the user input component (i.e. get snake direction changes)
3. Animate snake movement according to user input
 
### Snake v0.2.0

### Snake v0.3.0
 
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