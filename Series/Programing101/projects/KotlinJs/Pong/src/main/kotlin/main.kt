import kotlin.browser.window


fun main() {

    window.onload = {

        val arenaWidth = 800
        val arenaHeight = 600

        var arena = initializeArena(arenaWidth, arenaHeight)
        var batLocation = arena.bat.location

        window.onmousemove = {
            batLocation = Location(arena.bat.location.x, it.clientY.toDouble())
            true
        }

        window.onclick = {
            arena = start(arena)
            true
        }

        val context = initializeCanvasContext(arenaWidth, arenaHeight)
        window.setInterval({
            arena = doStep(arena, batLocation)
            drawArena(arena, context)
        }, 25)
    }
}

