import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

data class Location(val x: Double, val y: Double)
data class Velocity(val dx: Double, val dy: Double)
data class Color(val rgb: String)

data class Ball(
        val center: Location,
        val radius: Double,
        val velocity: Velocity,
        val color: Color
)

fun isInXBounds(ball: Ball, canvas: HTMLCanvasElement) =
        ball.center.x - ball.radius > 0 && ball.center.x + ball.radius < canvas.width

fun isInYBounds(ball: Ball, canvas: HTMLCanvasElement) =
        ball.center.y - ball.radius > 0 &&  ball.center.y + ball.radius < canvas.height

fun isInBounds(ball: Ball, canvas: HTMLCanvasElement) =
        isInXBounds(ball, canvas) && isInYBounds(ball, canvas)


fun move(ball: Ball, canvas: HTMLCanvasElement): Ball {

    val newBall = Ball(
            Location(ball.center.x + ball.velocity.dx, ball.center.y + ball.velocity.dy),
            ball.radius,
            ball.velocity,
            ball.color
        )

    val arenaWidth = canvas.width
    val arenaHeight = canvas.height

    return if (isInBounds(newBall, canvas)) newBall
    else Ball(
            Location(
                if (newBall.velocity.dx < 0) max(0.0, newBall.center.x) else min(arenaWidth - newBall.radius, newBall.center.x),
                if (newBall.velocity.dy < 0) max(0.0, newBall.center.y) else min(arenaHeight - newBall.radius, newBall.center.y)
            ),
            newBall.radius,
            Velocity(
                if (!isInXBounds(newBall, canvas)) -newBall.velocity.dx else newBall.velocity.dx,
                if (!isInYBounds(newBall, canvas)) -newBall.velocity.dy else newBall.velocity.dy
            ),
            newBall.color
        )
}

fun createBall(ballRadius: Double, ballColor: Color, canvas: HTMLCanvasElement) = Ball(
        Location(
            Random.nextDouble(ballRadius, canvas.width - ballRadius),
            Random.nextDouble(ballRadius, canvas.height - ballRadius)),
        ballRadius,
        Velocity(dx = Random.nextDouble(-5.0, 5.0), dy = Random.nextDouble(-5.0, 5.0)),
        ballColor
    )

fun main() {
    var context: CanvasRenderingContext2D = initializeCanvasContext()
    window.onload = { context = initializeCanvasContext(); null }

    var ball = createBall(15.0, Color("#FF0000"), context.canvas)
    window.setInterval({
        clear(context)
        ball = move(ball, context.canvas)
        draw(ball, context)
    }, 25)
}

fun initializeCanvasContext(): CanvasRenderingContext2D {
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    canvas.width  = window.innerWidth
    canvas.height = window.innerHeight
    document.body?.appendChild(canvas)
    return canvas.getContext("2d") as CanvasRenderingContext2D
}

fun clear(context: CanvasRenderingContext2D) {
    context.fillStyle = "#EEEEEE"
    context.fillRect(
        0.0, 0.0, context.canvas.width.toDouble(), context.canvas.height.toDouble()
    )
}

fun draw(ball: Ball, context: CanvasRenderingContext2D) {
    context.fillStyle = ball.color.rgb
    context.beginPath()
    context.arc(ball.center.x, ball.center.y, ball.radius, 0.0, 2 * PI)
    context.fill()
}