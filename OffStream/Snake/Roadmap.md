----   
## Milestones 
1. "Bouncing Ball" demo 
    * Use `SurfaceView` for the rendering

2. "Snake v0.1.0"
    * The snake is comprised of only its head
    * It moves along the screen according to the user actions
    
3. "Snake v0.2.0"
   * Snake moves automatically and changes direction according to the user actions
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
1. Make the application full screen (i.e. immersive) and with landscape orientation
2. Implement the user input component (i.e. get snake direction changes)
3. Animate snake movement according to user input

Documentation: 
* [Enable immersive mode](https://developer.android.com/training/system-ui/immersive)
* [Control de system UI visibility](https://developer.android.com/training/system-ui)
* [Custom view components](https://developer.android.com/guide/topics/ui/custom-components)
* [Creating a View Class](https://developer.android.com/training/custom-views/create-view)
 
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

### Snake v0.1.0
1. Draft implementation of the snake game based on the Bouncing Ball demo (15m)   
   * Goal 1: Display a still snake head at the center of the screen, which is now in landscape mode
   * Goal 2: Create the initial code base for the snake game and revisit the mental model
   * Create the project and adapt the elements from the bouncing ball
   * Make the application full screen (i.e. immersive) and with landscape orientation
   * Make room in the UI for the upcoming control pad
   * Revisit the mental model: MVC and threading
2. Add support for collecting user input (40m)
   * Goal: Implement first draft of the user input component `ControlPad` and add it to the application
   * Android's rendering algorithm: `onMeasure`, `onLayout` and `onDraw`
   * Handling touch events: `onTouch`
   * Color resources
   * Comparison of drawing approaches: for `SurfaceView` and for regular `View`
     * Calls to `invalidate` and consequences
3. Animate snake movement according to the user input (40m)
   * Goal: Make the snake head move with a velocity proporcional to the input collected from the `ControlPad`
   * Refine `ControlPad` implementation so that the pad (inner circle) remains inside the outer circle
     * Present the required math concepts: vector and basic vector arithmetic
     * Implementation of `Vector2D` (immutable)
       * Operator overloading and infix notation
   * Producing direction events from `ControlPad` to be handled by the game engine by converting them to snake velocity changes
     * Defining custom events: `onChangeDirection`
     * Revisit the threading model

