package edu.sempreacodar.drag.draw

/**
 * Represents points in the drawing.
 */
data class Point(val x: Int = 0, val y: Int = 0)

/**
 * Represents lines in the drawing. A line is sequence of [Point].
 */
data class Line(val points: List<Point> = listOf())

/**
 * Extension function that adds [point] to this line.
 * @return the new line instance
 */
operator fun Line.plus(point: Point) = Line(points + point)

/**
 * Represents the drawing, which is comprised of a set of [Line].
 */
data class Drawing(val lines: List<Line> = listOf())

/**
 * Extension function that adds [line] to the current drawing.
 * @return the new drawing instance
 */
operator fun Drawing.plus(line: Line) = Drawing(lines +line)
