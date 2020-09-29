/**
 * Defines the representation of the game's arena.
 * @property human          The human player.
 * @property computer       The computer player.
 * @property ball           The ball.
 * @property width          The width of the arena.
 * @property height         The height of the arena.
 */
data class Arena(
        val human: Player,
        val computer: Player,
        val ball: Ball,
        val width: Int,
        val height: Int
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
    val player = Player(Bat(Location(width - batMargin, height / 2.0), batWidth, batHeight))
    val computer = Player(Bat(Location(batMargin, height / 2.0), batWidth, batHeight))
    return Arena(player, computer, ball, width, height)
}

/**
 * Builds a new arena instance from the given one ([arena]) and with a new ball [ball].
 * @param arena     The arena instance to be used as a prototype for the new one.
 * @param ball      The ball for the new instance.
 * @return A [Arena] instance with all its properties copied from [arena] and with a new ball [ball].
 */
fun buildArenaWith(arena: Arena, ball: Ball) = Arena(
        arena.human,
        arena.computer,
        ball,
        arena.width,
        arena.height
)

/**
 * Builds a new arena instance from the given one ([arena]) and with a new ball [ball] and bat instances.
 * @param arena         The arena instance to be used as a prototype for the new one.
 * @param ball          The ball for the new instance.
 * @param playerBat     The bat for the human player.
 * @param computerBat   The bat for the computer player.
 * @return A [Arena] instance with all its properties copied from [arena] and with the given arguments.
 */
fun buildArenaWith(arena: Arena, ball: Ball, playerBat: Bat, computerBat: Bat) = Arena(
        Player(playerBat, arena.human.score),
        Player(computerBat, arena.computer.score),
        ball,
        arena.width,
        arena.height
)

/**
 * Builds a new arena instance from the given one ([arena]), with new player instances and a stationary ball.
 * @param arena      The arena instance to be used as a prototype for the new one.
 * @param human      The human player instance.
 * @param computer   The computer player instance.
 * @return A [Arena] instance with all its properties copied from [arena] and with the given arguments.
 */
fun buildArenaWith(arena: Arena, human: Player, computer: Player) = Arena(
        human,
        computer,
        createStationaryBall(arena.width, arena.height),
        arena.width,
        arena.height
)

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
    return maybeDeflectBall(getBatRightEdge(leftBat), ball, previousBall.center)
            ?: (maybeDeflectBall(getBatLeftEdge(rightBat), ball, previousBall.center) ?: ball)
}

/**
 * Computes the new arena according to its current state and the new location for the player's bat.
 * @param arena                 The current arena instance.
 * @param playerBatLocation     The new (tentative) location of the player's bat.
 */
fun doStep(arena: Arena, playerBatLocation: Location): Arena {
    val playerBat = keepBatInVerticalBounds(
            Bat(playerBatLocation, arena.human.bat.width, arena.human.bat.height),
            arena.height.toDouble(),
            MARGIN
    )
    val ball = moveBall(arena.ball, arena.height.toDouble())

    // TODO: Slow down the opponent bat's movement
    val opponentBat = buildBatWith(arena.computer.bat, Location(arena.computer.bat.location.x, ball.center.y))

    return when (val result = checkMoveResult(ball, arena.width)) {
        MoveResult.OK -> buildArenaWith(
                arena, maybeDeflectBall(opponentBat, playerBat, arena.ball, ball), playerBat, opponentBat
        )
        else -> buildArenaWith(
                arena,
                Player(playerBat, if (result == MoveResult.OPPONENT_LOSS) arena.human.score + 1 else arena.human.score),
                Player(opponentBat, if (result == MoveResult.PLAYER_LOSS) arena.computer.score + 1 else arena.computer.score)
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
        else buildArenaWith(arena, Ball(arena.ball.center, arena.ball.radius, getInitialVelocity()))
