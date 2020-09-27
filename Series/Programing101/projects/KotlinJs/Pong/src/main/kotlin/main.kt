import kotlin.browser.window

/**
 * The application entry point.
 */
fun main() {

    window.onload = {

        var arena = initializeArena(width = 800, height = 600)
        var batLocation = arena.playerBat.location

        window.onmousemove = {
            batLocation = Location(arena.playerBat.location.x, it.offsetY)
            true
        }

        window.onclick = {
            arena = start(arena)
            true
        }

        val context = initializeCanvasContext(arena.width, arena.height)
        fun animationStep() {
            arena = doStep(arena, batLocation)
            maybePlaySound(arena)
            drawArena(arena, context)
        }
        window.setInterval(::animationStep, 25)
    }
}

