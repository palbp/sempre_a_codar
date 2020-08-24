import kotlin.random.Random

data class Arena(val bat: Bat, val ball: Ball, val width: Int, val height: Int)

fun initializeArena(width: Int, height: Int): Arena {
    val ball = Ball(
            Location(width / 2.0, height / 2.0),
            5.0,
            Velocity(0.0, 0.0)
    )
    val bat = Bat(Location(width - 15.0, height / 2.0), 7.0, 80.0)
    return Arena(bat, ball, width, height)
}

// TODO: Improve deflection algorithm
fun deflectBallInBat(ball: Ball) = Ball(
        ball.center,
        ball.radius,
        Velocity(ball.velocity.dx * -1, ball.velocity.dy)
    )

fun doStep(arena: Arena, batLocation: Location): Arena {
    val bat = keepBatInArenaBounds(
            Bat(batLocation, 7.0, 80.0),
            arena.height.toDouble(),
            20.0
    )
    val ball = moveBall(arena.ball, arena.width.toDouble(), arena.height.toDouble())
    return Arena(
            bat,
            if(detectCollision(bat, ball)) deflectBallInBat(ball) else ball,
            arena.width,
            arena.height
    )
}



fun start(arena: Arena) =
    if (isBallMoving(arena.ball)) arena
    else Arena(
            arena.bat,
            Ball(
                    arena.ball.center,
                    arena.ball.radius,
                    Velocity(Random.nextInt(0, 10).toDouble(), Random.nextInt(-10, 10).toDouble())
            ),
            arena.width,
            arena.height
    )
