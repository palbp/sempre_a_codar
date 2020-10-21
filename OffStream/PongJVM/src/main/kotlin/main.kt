import pt.isel.canvas.*

/**
 * The application entry point.
 */
fun main() {

    onStart {

        var arena = initializeArena(width = 800, height = 600)
        var playerBatLocation = arena.human.bat.location

        val canvas = initializeCanvasContext(arena.width, arena.height)

        canvas.onMouseMove {
            playerBatLocation = Location(arena.human.bat.location.x, it.y.toDouble())
        }

        canvas.onMouseDown {
            arena = start(arena)
        }

        canvas.onTimeProgress(25) {
            arena = doStep(arena, playerBatLocation)
            maybePlaySound(arena)
            drawArena(arena, canvas)
        }
    }
}

