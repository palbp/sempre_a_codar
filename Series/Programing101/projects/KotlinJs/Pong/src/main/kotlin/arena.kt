/**
 * Defines the representation of the game's arena.
 * @property playerBat      The player's bat.
 * @property opponentBat    The opponent's bat.
 * @property ball           The ball.
 * @property width          The width of the arena.
 * @property height         The height of the arena.
 * @property opponentScore          The current score.
 */
data class Arena(
        val playerBat: Bat,
        val opponentBat: Bat,
        val ball: Ball,
        val width: Int,
        val height: Int,
        val opponentScore: Int
        // TODO: add player score
)

private const val MARGIN = 20.0

/**
 * Representation used to express the possible results of an animation step in the arena.
 */
private enum class MoveResult {
    OK,
    PLAYER_LOSS,
    OPPONENT_LOSS
}


/**
 * Creates an arena instance with the specified dimension.
 * @param width     The width of the arena.
 * @param height    The height of the arena.
 * @return The newly created arena instance.
 */
fun initializeArena(width: Int, height: Int): Arena {
    val ball = createStationaryBall(width, height)
    val batMargin = 15.0
    val batWidth = 7.0
    val batHeight = 80.0
    val playerBat = Bat(Location(width - batMargin, height / 2.0), batWidth, batHeight)
    val opponentBat = Bat(Location(batMargin, height / 2.0), batWidth, batHeight)
    return Arena(playerBat, opponentBat, ball, width, height, 0)
}

/**
 * Checks if the ball either one of the protected areas, that is, if a loss should be
 * accounted for.
 * @param ball      The ball instance.
 * @param width     The width of the arena.
 */
private fun checkMoveResult(ball: Ball, width: Int): MoveResult = when {
    ball.center.x + ball.radius >= width -> MoveResult.PLAYER_LOSS
    ball.center.x - ball.radius <= 0 -> MoveResult.OPPONENT_LOSS
    else -> MoveResult.OK
}

/**
 * Corrects the ball's position to account for deflections on the given bats.
 */
private fun maybeDeflectBall(leftBat: Bat, rightBat: Bat, previousBall: Ball, ball: Ball): Ball {
    return maybeDeflectBall(getBatRightEdge(leftBat), ball, previousBall.center) ?:
    (maybeDeflectBall(getBatLeftEdge(rightBat), ball, previousBall.center) ?: ball)
}

/**
 * Computes the new arena according to its current state and the new location for the player's bat.
 * @param arena                 The current arena instance.
 * @param playerBatLocation     The new (tentative) location of the player's bat.
 */
fun doStep(arena: Arena, playerBatLocation: Location): Arena {
    val playerBat = keepBatInVerticalBounds(
            Bat(playerBatLocation, arena.playerBat.width, arena.playerBat.height),
            arena.height.toDouble(),
            MARGIN
    )
    val ball = moveBall(arena.ball, arena.height.toDouble())

    // TODO: Slow down the opponent bat's movement
    val opponentBat = keepBatInVerticalBounds(Bat(
            Location(arena.opponentBat.location.x, ball.center.y),
            arena.opponentBat.width,
            arena.opponentBat.height
    ), arena.height.toDouble(), MARGIN)

    return when (val result = checkMoveResult(ball, arena.width)) {
        MoveResult.OK -> Arena(
                playerBat,
                opponentBat,
                maybeDeflectBall(opponentBat, playerBat, arena.ball, ball),
                arena.width,
                arena.height,
                arena.opponentScore
        )
        else -> Arena(
                playerBat,
                opponentBat,
                createStationaryBall(arena.width, arena.height),
                arena.width,
                arena.height,
                if (result == MoveResult.PLAYER_LOSS) arena.opponentScore + 1 else arena.opponentScore
        )
    }
}

/**
 * Starts the movement of the ball if it has not started yet.
 * @param arena The arena instance.
 * @return The arena instance bearing a moving ball.
 */
fun start(arena: Arena) =
        if (isBallMoving(arena.ball)) arena
        else Arena(
                arena.playerBat,
                arena.opponentBat,
                Ball(arena.ball.center, arena.ball.radius, getInitialVelocity()),
                arena.width,
                arena.height,
                arena.opponentScore
        )
