import kotlin.browser.window

/**
 * The application entry point.
 */
fun main() {

    window.onload = {

        var arena = initializeArena(width = 800, height = 600)
        var playerBatLocation = arena.human.bat.location

        window.onmousemove = {
            playerBatLocation = Location(arena.human.bat.location.x, it.offsetY)
            true
        }

        window.onclick = {
            arena = start(arena)
            true
        }

        val context = initializeCanvasContext(arena.width, arena.height)
        fun animationStep() {
            arena = doStep(arena, playerBatLocation)
            maybePlaySound(arena)
            drawArena(arena, context)
        }
        window.setInterval(::animationStep, 25)
    }
}

