package edu.sempreacodar.drag.model

/**
 * Represents points in the drawing.
 */
data class Point(val x: Float = 0F, val y: Float = 0F)

/**
 * Represents lines in the drawing. A line is sequence of [Point].
 */
data class Line(val points: List<Point> = listOf())

/**
 * Creates a line starting at the given point
 * @param [point]   the point where the line starts at
 * @return the new line
 */
fun startNewLineAt(point: Point) = Line(listOf(point))

/**
 * Extension function that adds [point] to this line.
 * @return the new line instance
 */
operator fun Line.plus(point: Point) = Line(points + point)

/**
 * Represents the drawing, which is comprised of a set of [Line].
 */
class Drawing private constructor(val lines: List<Line> = listOf(), val lastLine: Line? = null) {
    companion object {
        fun from(lines: List<Line> = listOf()) = when (lines.size) {
            0 -> Drawing()
            1 -> Drawing(lastLine = lines.last())
            else -> Drawing(lines = lines.dropLast(1), lines.last())
        }
        fun from(lines: List<Line>, lastLine: Line) = Drawing(lines, lastLine)
        val EMPTY = Drawing()
    }
}

/**
 * Iterates over the drawing's lines applying [action] to each line of the drawing.
 * @param [action]  the action to be applied to each line
 */
fun Drawing.forEach(action: (Line) -> Unit) {
    lines.forEach(action)
    if (lastLine != null) action(lastLine)
}

/**
 * Extension function that adds [line] to the current drawing.
 * @return the new drawing instance
 */
operator fun Drawing.plus(line: Line) = Drawing.from(
    if (lastLine == null) lines else lines + lastLine,
    line
)

/**
 * Extension function that adds a point to the drawing's last line.
 * @param [point] the point to be added
 * @return the new [Drawing] instance
 */
fun Drawing.addToLastLine(point: Point): Drawing =
    Drawing.from(lines, if (lastLine == null) startNewLineAt(point) else lastLine + point)
