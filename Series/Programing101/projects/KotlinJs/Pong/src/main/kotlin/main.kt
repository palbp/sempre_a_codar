import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Math.random
import kotlin.math.PI
import kotlin.random.Random.Default.nextInt

fun initializeCanvasContext(): CanvasRenderingContext2D {
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    canvas.width  = 800
    canvas.height = 600
    document.body?.appendChild(canvas)
    return canvas.getContext("2d") as CanvasRenderingContext2D
}

fun drawArena(context: CanvasRenderingContext2D) {
    context.fillStyle = "#100000"
    context.fillRect(
        0.0, 0.0, context.canvas.width.toDouble(), context.canvas.height.toDouble()
    )
}


fun drawBat(bat: Bat, context: CanvasRenderingContext2D) {
    context.fillStyle = "#575353"
    context.fillRect(
        x = bat.location.x - bat.width / 2,
        y = bat.location.y - bat.height / 2,
        w = bat.width,
        h = bat.height
    )
}

fun drawBall(ball: Ball, context: CanvasRenderingContext2D) {
    context.fillStyle = "#B3B3B3"
    context.beginPath()
    context.arc(ball.center.x, ball.center.y, ball.radius, 0.0, 2 * PI)
    context.fill()
}

fun main() {

    window.onload = {

        val context = initializeCanvasContext()

        val batX = context.canvas.width - 15.0
        var batLocation = Location(batX, context.canvas.height / 2.0)
        var ball = Ball(
                Location(context.canvas.width/2.0, context.canvas.height/2.0),
                5.0,
                Velocity(0.0, 0.0)
        )

        window.onmousemove = {
            batLocation = Location(batX, it.clientY.toDouble())
            true
        }

        window.onclick = {
            ball = if (isBallMoving(ball)) ball
            else Ball(ball.center, ball.radius,
                    Velocity(nextInt(0, 10).toDouble(), nextInt(-10, 10).toDouble())
            )
            null
        }

        window.setInterval({

            val bat = keepBatInArenaBounds(
                    Bat(batLocation, 7.0, 80.0),
                    context.canvas.height.toDouble()
            )

            ball = moveBall(ball)

            drawArena(context)
            drawBat(bat, context)
            drawBall(ball, context)

        }, 25)
    }
}

