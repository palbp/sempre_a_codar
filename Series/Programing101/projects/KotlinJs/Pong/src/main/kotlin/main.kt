import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.browser.window

fun initializeCanvasContext(): CanvasRenderingContext2D {
    val canvas = document.createElement("canvas") as HTMLCanvasElement
    canvas.width  = window.innerWidth - 20
    canvas.height = window.innerHeight - 20
    document.body?.appendChild(canvas)
    return canvas.getContext("2d") as CanvasRenderingContext2D
}

fun clear(context: CanvasRenderingContext2D) {
    context.fillStyle = "#EEEEEE"
    context.fillRect(
        0.0, 0.0, context.canvas.width.toDouble(), context.canvas.height.toDouble()
    )
}


fun draw(bat: Bat, context: CanvasRenderingContext2D) {
    context.fillStyle = "#FF0000"
    context.fillRect(
        x = bat.location.x - bat.width / 2,
        y = bat.location.y - bat.height / 2,
        w = bat.width,
        h = bat.height
    )
}

fun main() {

    var context: CanvasRenderingContext2D = initializeCanvasContext()
    window.onload = { context = initializeCanvasContext(); null }

    var batLocation: Location = Location(0.0, 0.0)
    window.onmousemove = {
        batLocation = Location(
            context.canvas.width - 100.0,
            it.clientY.toDouble()
        )
        true
    }

    window.setInterval({
        val bat = getBatInArenaBounds(Bat(batLocation, 20.0, 100.0), context.canvas.height.toDouble())
        clear(context)
        draw(bat, context)
    }, 25)
}