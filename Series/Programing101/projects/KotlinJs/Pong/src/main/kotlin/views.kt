import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.math.PI

fun initializeCanvasContext(width: Int, height: Int): CanvasRenderingContext2D {
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    canvas.width  = width
    canvas.height = height
    canvas.setAttribute("class", "arena")
    document.body?.appendChild(canvas)
    return canvas.getContext("2d") as CanvasRenderingContext2D
}

fun drawBackground(context: CanvasRenderingContext2D) {
    context.fillStyle = "#100000"
    context.fillRect(
            0.0, 0.0, context.canvas.width.toDouble(), context.canvas.height.toDouble()
    )

    context.beginPath()
    context.strokeStyle = "#575353"
    context.lineWidth = 3.0
    context.setLineDash(arrayOf(3.0, 8.0))
    context.moveTo(context.canvas.width / 2.0, 0.0)
    context.lineTo(context.canvas.width / 2.0, context.canvas.height.toDouble())
    context.stroke()
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

fun drawArena(arena: Arena, context: CanvasRenderingContext2D) {
    drawBackground(context)
    drawBat(arena.bat, context)
    drawBall(arena.ball, context)
}
