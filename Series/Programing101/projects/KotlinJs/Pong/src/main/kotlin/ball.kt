
data class Ball(
        val center: Location,
        val radius: Double,
        val velocity: Velocity
)

fun isBallMoving(ball: Ball) = ball.velocity.dx != 0.0 || ball.velocity.dy != 0.0

fun moveBall(ball: Ball) = Ball(
    Location(ball.center.x + ball.velocity.dx, ball.center.y + ball.velocity.dy),
    ball.radius,
    ball.velocity
)