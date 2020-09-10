/**
 * Defines the representation of the players' bats.
 * @property location   The bat's location (its center).
 * @property width      The bat's width.
 * @property height     The bat's height.
 */
data class Bat(val location: Location, val width: Double, val height: Double)

// BEWARE: This is bad in so many ways. My boss made me do it! =P
// TODO: Fix this!
private val audioHandles: AudioHandles = initializeAudio()

/**
 * Gets the horizontal coordinate of the bat's left edge.
 * @param bat   The bat instance
 * @return The bat's left edge
 */
private fun getBatLeftEdge(bat: Bat) = bat.location.x - bat.width / 2

/**
 * Checks whether the given bat is within the specified bounds.
 * @param bat           The bat instance.
 * @param arenaHeight   The height of the arena.
 * @param margin        The gap to be preserved between the bat and the vertical boundaries.
 * @return A boolean value indicating if [bat] is within the arena's bounds or not.
 */
private fun isBatWithinBounds(bat: Bat, arenaHeight: Double, margin: Double) =
        bat.location.y - bat.height / 2.0 - margin >= 0.0 &&
                bat.location.y + bat.height / 2.0 + margin <= arenaHeight

/**
 * Places the bat within the arena bounds.
 * @param bat           The bat instance.
 * @param arenaHeight   The height of the arena.
 * @param margin        The gap to be preserved between the bat and the vertical boundaries.
 * @return A bat instance located within the arena's bounds.
 */
private fun placeBatWithinBounds(bat: Bat, arenaHeight: Double, margin: Double) = Bat(
        Location(
                bat.location.x,
                if (bat.location.y - bat.height / 2.0 - margin < 0.0) bat.height / 2.0 + margin
                else arenaHeight - bat.height / 2.0 - margin
        ),
        bat.width,
        bat.height
)

/**
 * Keeps the bat within the arena bounds, regardless of its current position.
 * @param bat           The bat instance.
 * @param arenaHeight   The height of the arena.
 * @param margin        The gap to be preserved between the bat and the vertical boundaries.
 * @return a bat instance located inside the arena bounds.
 */
fun keepBatInArenaBounds(bat: Bat, arenaHeight: Double, margin: Double) =
        if (isBatWithinBounds(bat, arenaHeight, margin)) bat
        else placeBatWithinBounds(bat, arenaHeight, margin)

/**
 * Deflects the ball if it has been hit by the bat.
 * @param bat                   The bat instance.
 * @param ball                  The ball instance.
 * @param previousBallLocation  The previous location of the [ball].
 */
fun maybeDeflectBall(bat: Bat, ball: Ball, previousBallLocation: Location): Ball {
    // Using the algorithm described in the links to detect collision between two lines
    // http://paulbourke.net/geometry/pointlineplane/
    // http://www.jeffreythompson.org/collision-detection/line-line.php

    val l1 = Line(previousBallLocation, ball.center)
    val l2 = Line(
            Location(getBatLeftEdge(bat), bat.location.y - bat.height / 2.0),
            Location(getBatLeftEdge(bat), bat.location.y + bat.height / 2.0)
    )

    val denominator = (l2.end.y - l2.start.y) * (l1.end.x - l1.start.x) - (l2.end.x - l2.start.x) * (l1.end.y - l1.start.y)
    val uA = ((l2.end.x - l2.start.x) * (l1.start.y - l2.start.y) - (l2.end.y - l2.start.y) * (l1.start.x - l2.start.x)) /
            denominator
    val uB = ((l1.end.x - l1.start.x) * (l1.start.y - l2.start.y) - (l1.end.y - l1.start.y) * (l1.start.x - l2.start.x)) /
            denominator

    return if (uA !in 0.0..1.0 || uB !in 0.0..1.0) ball
    else {
            audioHandles.batHit.play()
            Ball(
                    Location(
                            l1.start.x + uA * (l1.end.x - l1.start.x) - ball.radius,
                            l1.start.y + uA * (l1.end.y - l1.start.y)
                    ),
                    ball.radius,
                    Velocity(ball.velocity.dx * -1, ball.velocity.dy)
            )
        }
}

