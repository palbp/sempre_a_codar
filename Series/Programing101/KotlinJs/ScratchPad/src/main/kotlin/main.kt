import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.math.PI

data class Location(val x: Double, val y: Double)
data class Velocity(val dx: Double, val dy: Double)
data class Ball(val location: Location, val radius: Double, val velocity: Velocity)

fun doStep(ball: Ball, context: CanvasRenderingContext2D): Ball {

    val newBall = Ball(
            Location(ball.location.x + ball.velocity.dx, ball.location.y + ball.velocity.dy),
            ball.radius,
            ball.velocity
    )

    return when {
        newBall.velocity.dx < 0.0 && ball.location.x < ball.radius -> Ball(
                Location(ball.radius, ball.location.y), ball.radius, Velocity(ball.velocity.dx * -1, ball.velocity.dy)
        )
        newBall.velocity.dx > 0.0 && ball.location.x + ball.radius > context.canvas.width -> Ball(
                Location(context.canvas.width - ball.radius, ball.location.y),
                ball.radius,
                Velocity(ball.velocity.dx * -1, ball.velocity.dy)
        )
        else -> newBall
    }
}

fun main() {
    var context: CanvasRenderingContext2D = initializeCanvasContext()
    window.onload = { context = initializeCanvasContext(); null }
    val ballRadius = 15.0
    var ball = Ball(Location(x = ballRadius, y = 60.0), ballRadius, Velocity(dx = 10.0, dy = 0.0))
    window.setInterval({
        ball = doStep(ball, context)
        clear(context)
        draw(ball.location.x, ball.location.y, ball.radius, context)
    }, 25)
}

fun initializeCanvasContext(): CanvasRenderingContext2D {
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    canvas.width  = window.innerWidth
    canvas.height = window.innerHeight
    document.body?.appendChild(canvas)
    return canvas.getContext("2d") as CanvasRenderingContext2D
}

fun clear(context: CanvasRenderingContext2D) =
        context.clearRect(0.0, 0.0, context.canvas.width.toDouble(), context.canvas.height.toDouble())

fun draw(x: Double, y: Double, radius: Double, context: CanvasRenderingContext2D) {
    context.fillStyle = "#FF0000"
    context.beginPath()
    context.arc(x, y, radius, 0.0, 2 * PI)
    context.fill()
}