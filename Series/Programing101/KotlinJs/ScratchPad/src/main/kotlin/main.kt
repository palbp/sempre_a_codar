import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.browser.window
import kotlin.math.PI

fun initializeCanvasContext(): CanvasRenderingContext2D {
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    canvas.width  = window.innerWidth
    canvas.height = window.innerHeight
    document.body?.appendChild(canvas)
    return canvas.getContext("2d") as CanvasRenderingContext2D
}

fun clear(context: CanvasRenderingContext2D) {
    context.clearRect(0.0, 0.0, context.canvas.width.toDouble(), context.canvas.height.toDouble())
}

fun drawAt(x: Double, y: Double, radius: Double, context: CanvasRenderingContext2D) {
    context.fillStyle = "#DE412A"
    context.beginPath()
    context.arc(x, y, radius, 0.0, 2 * PI)
    context.fill()
}

fun main() {
    var context: CanvasRenderingContext2D = initializeCanvasContext()
    window.onload = { context = initializeCanvasContext(); null }
    val ball = object { var x = 20.0; var y = 20.0; var radius = 15.0 }
    window.setInterval({
        ball.x = ball.x + 10
        clear(context)
        drawAt(ball.x, ball.y, ball.radius, context)
    }, 25)
}