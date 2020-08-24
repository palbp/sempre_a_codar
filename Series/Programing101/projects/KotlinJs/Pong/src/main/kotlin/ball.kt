
data class Ball(
        val center: Location,
        val radius: Double,
        val velocity: Velocity
)

fun isBallMoving(ball: Ball) = ball.velocity.dx != 0.0 || ball.velocity.dy != 0.0

fun isInBounds(ball: Ball, width: Double, height: Double) = isInXBounds(ball, width) && isInYBounds(ball, height)

fun isInXBounds(ball: Ball, width: Double) = ball.center.x - ball.radius > 0 && ball.center.x + ball.radius < width

fun isInYBounds(ball: Ball, height: Double) = ball.center.y - ball.radius > 0 && ball.center.y + ball.radius < height

fun moveBall(ball: Ball, width: Double, height: Double): Ball {
    val newBall = Ball(
            add(ball.center, ball.velocity),
            ball.radius,
            ball.velocity
    )

    return if (isInBounds(newBall, width, height) || (newBall.center.x + newBall.radius >= width)) newBall
    else
        if(!isInYBounds(newBall, height)) Ball(
            newBall.center,
            newBall.radius,
            Velocity(newBall.velocity.dx, newBall.velocity.dy * -1)
        )
        else Ball(newBall.center, newBall.radius, Velocity(newBall.velocity.dx * -1, newBall.velocity.dy))
}