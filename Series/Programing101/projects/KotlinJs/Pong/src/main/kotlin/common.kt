/**
 * Defines the representation for locations in the game arena.
 * @property x  The horizontal coordinate.
 * @property y  The vertical coordinate.
 */
data class Location(val x: Double, val y: Double)

/**
 * Defines the representation for velocities in the game arena.
 * @property dx  The horizontal coordinate variation.
 * @property dy  The vertical coordinate variation.
 */
data class Velocity(val dx: Double, val dy: Double)

/**
 * Adds to the given location a location variation (velocity).
 * @param location  The original location.
 * @param velocity  The variation to be added.
 * @return The new location.
 */
fun add(location: Location, velocity: Velocity) = Location(location.x + velocity.dx, location.y + velocity.dy)

/**
 * Defines the representation for lines used for collision detection. A line is defined by two points, represented
 * here by [Location] instances.
 * @property start  The first point.
 * @property end    The second point.
 */
data class Line(val start: Location, val end: Location)

/**
 * Function used to compute the intersection point between two lines.
 */
fun computeIntersection(line1: Line, line2: Line): Location? {

    // Using the algorithm described in the links to detect collision between two lines
    // http://paulbourke.net/geometry/pointlineplane/
    // http://www.jeffreythompson.org/collision-detection/line-line.php

    val denominator = (line2.end.y - line2.start.y) * (line1.end.x - line1.start.x) - (line2.end.x - line2.start.x) * (line1.end.y - line1.start.y)
    val uA = ((line2.end.x - line2.start.x) * (line1.start.y - line2.start.y) - (line2.end.y - line2.start.y) * (line1.start.x - line2.start.x)) /
            denominator
    val uB = ((line1.end.x - line1.start.x) * (line1.start.y - line2.start.y) - (line1.end.y - line1.start.y) * (line1.start.x - line2.start.x)) /
            denominator

    return if (uA !in 0.0..1.0 || uB !in 0.0..1.0) null
    else Location(
            line1.start.x + uA * (line1.end.x - line1.start.x),
            line1.start.y + uA * (line1.end.y - line1.start.y)
    )
}