/**
 * Defines the representation of the players' bats.
 * @property location   The bat's location (its center).
 * @property width      The bat's width.
 * @property height     The bat's height.
 */
data class Bat(val location: Location, val width: Double, val height: Double)

/**
 * Gets the horizontal coordinate of the bat's left edge.
 * @param bat   The bat instance
 * @return The bat's left edge
 */
fun getBatLeftEdge(bat: Bat) = bat.location.x - bat.width / 2

/**
 * Gets the horizontal coordinate of the bat's right edge.
 * @param bat   The bat instance
 * @return The bat's right edge
 */
fun getBatRightEdge(bat: Bat) = bat.location.x + bat.width / 2

/**
 * Checks whether the given bat is within the specified bounds.
 * @param bat           The bat instance.
 * @param arenaHeight   The height of the arena.
 * @param margin        The gap to be preserved between the bat and the vertical boundaries.
 * @return A boolean value indicating if [bat] is within the arena's bounds or not.
 */
fun isBatWithinBounds(bat: Bat, arenaHeight: Double, margin: Double) =
        bat.location.y - bat.height / 2.0 - margin >= 0.0 &&
                bat.location.y + bat.height / 2.0 + margin <= arenaHeight

/**
 * Places the bat within the arena bounds.
 * @param bat           The bat instance.
 * @param arenaHeight   The height of the arena.
 * @param margin        The gap to be preserved between the bat and the vertical boundaries.
 * @return A bat instance located within the arena's bounds.
 */
fun placeBatWithinBounds(bat: Bat, arenaHeight: Double, margin: Double) = Bat(
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
 * Checks if the ball is within the bat's vertical bounds.
 */
fun isBallWithinBatVerticalBounds(bat: Bat, ball: Ball) =
        ball.center.y >= bat.location.y - bat.height / 2.0 && ball.center.y <= bat.location.y + bat.height / 2.0

/**
 * Checks if one of the bat's vertical edges is inside the ball.
 */
fun isBatIntersectingBall(bat: Bat, ball: Ball) =
        if (ball.velocity.dx > 0)
            getBatLeftEdge(bat) >= ball.center.x - ball.radius && getBatLeftEdge(bat) <= ball.center.x + ball.radius
        else
            getBatRightEdge(bat) >= ball.center.x - ball.radius && getBatLeftEdge(bat) <= ball.center.x + ball.radius

/**
 * Checks whether the bat is hitting the ball.
 * @param bat   The bat instance.
 * @param ball  The ball instance.
 */
fun isBatHittingBall(bat: Bat, ball: Ball): Boolean =
        isBallWithinBatVerticalBounds(bat, ball) && isBatIntersectingBall(bat, ball)

/**
 * Deflects the ball on the bat.
 * @param bat   The bat instance.
 * @param ball  The ball instance.
 * @return The deflected ball.
 */
// TODO: Fix bug. If the ball's velocity is large enough, the collision may not be detected
fun deflectBall(bat: Bat, ball: Ball) = Ball(
        Location(
                if (ball.velocity.dx > 0) getBatLeftEdge(bat) - ball.radius else getBatRightEdge(bat) + ball.radius,
                ball.center.y),
        ball.radius,
        Velocity(ball.velocity.dx * -1, ball.velocity.dy)
)

