package edu.sempreacodar.drag.draw.model

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

// TODO: Reevaluate the need to change the Drawing representation for making the operation of
//  adding a point to the last line of the drawing cheaper

/**
 * Represents the drawing, which is comprised of a set of [Line].
 */
data class Drawing(val lines: List<Line> = listOf())

/**
 * Extension function that adds [line] to the current drawing.
 * @return the new drawing instance
 */
operator fun Drawing.plus(line: Line) = Drawing(lines +line)

/**
 * Extension function that adds a point to the drawing's last line.
 * @param [point] the point to be added
 * @return the new [Drawing] instance
 */
fun Drawing.addToLastLine(point: Point): Drawing =
        Drawing(lines.dropLast(1) + (lines.last() + point))
