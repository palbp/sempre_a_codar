import kotlin.browser.window

/**
 * The application entry point.
 */
fun main() {

    window.onload = {

        var arena = initializeArena(width = 800, height = 600)
        var batLocation = arena.bat.location

        val audioHandles = initializeAudio()

        window.onmousemove = {
            batLocation = Location(arena.bat.location.x, it.offsetY)
            true
        }

        window.onclick = {
            arena = start(arena)
            audioHandles.batHit.play()
            true
        }

        val context = initializeCanvasContext(arena.width, arena.height)
        window.setInterval({
            arena = doStep(arena, batLocation)
            drawArena(arena, context)
        }, 25)
    }
}

