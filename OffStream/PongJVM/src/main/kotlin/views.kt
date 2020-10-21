import kotlin.math.PI
import pt.isel.canvas.*

const val ARENA_COLOR = 0x100000
const val TEXT_COLOR = 0xB3B3B3
const val BAT_COLOR = 0x575353
const val BALL_COLOR = 0xB3B3B3

const val TEXT_SIZE = 52

/**
 * Extension method that draws a filled rectangle in the canvas.
 */
private fun Canvas.fillRect(x: Int, y: Int, width: Int, height: Int, color: Int) {
    drawRect(x, y, width, height, color)
}

/**
 * Extension method that draws a filled circle in the canvas.
 */
private fun Canvas.fillCircle(xCenter: Int, yCenter: Int, radius: Int, color: Int = BLACK) {
    drawCircle(xCenter, yCenter, radius, color)
}

/**
 * Initializes the drawing area.
 * @param width     The drawing area width.
 * @param height    The drawing area height.
 * @return The drawing area.
 */
fun initializeCanvasContext(width: Int, height: Int) = Canvas(width, height, BLACK)

/**
 * Draws the arena background on the given drawing area.
 * @param canvas   The drawing arena.
 */
fun drawBackground(canvas: Canvas) {
    canvas.fillRect(0, 0, canvas.width, canvas.height, ARENA_COLOR)

    /*
    context.beginPath()
    context.strokeStyle = "#575353"
    context.lineWidth = 3.0
    context.setLineDash(arrayOf(3.0, 8.0))
    context.moveTo(context.canvas.width / 2.0, 0.0)
    context.lineTo(context.canvas.width / 2.0, context.canvas.height.toDouble())
    context.stroke()
     */
}

/**
 * Draws the computer's score on the given drawing area.
 * @param arena     The arena instance.
 * @param canvas    The drawing canvas.
 */
fun drawComputerScore(arena: Arena, canvas: Canvas) {
    canvas.drawText(canvas.width / 2 - 50, 100, arena.computer.score.toString(), TEXT_COLOR, TEXT_SIZE)
}

/**
 * Draws the computer's score on the given drawing area.
 * @param arena     The arena instance.
 * @param canvas    The drawing canvas.
 */
fun drawPlayerScore(arena: Arena, canvas: Canvas) {
    canvas.drawText(canvas.width / 2 + 50, 100, arena.human.score.toString(), TEXT_COLOR, TEXT_SIZE)
}

/**
 * Draws the bat on the given drawing area.
 * @param bat       The bat instance.
 * @param canvas    The drawing canvas.
 */
fun drawBat(bat: Bat, canvas: Canvas) {
    canvas.fillRect(
            x = (bat.location.x - bat.width / 2).toInt(),
            y = (bat.location.y - bat.height / 2).toInt(),
            width = bat.width.toInt(),
            height = bat.height.toInt(),
            BAT_COLOR
    )
}

/**
 * Draws the ball on the given drawing area.
 * @param ball      The ball instance.
 * @param canvas    The drawing canvas.
 */
fun drawBall(ball: Ball, canvas: Canvas) {
    canvas.fillCircle(ball.center.x.toInt(), ball.center.y.toInt(), ball.radius.toInt(), BALL_COLOR)
}

/**
 * Draws the arena and its contents on the given drawing area.
 * @param arena     The arena instance.
 * @param canvas    The drawing canvas.
 */
fun drawArena(arena: Arena, canvas: Canvas) {
    drawBackground(canvas)
    drawBat(arena.human.bat, canvas)
    drawBat(arena.computer.bat, canvas)
    drawBall(arena.ball, canvas)
    drawComputerScore(arena, canvas)
    drawPlayerScore(arena, canvas)
}
