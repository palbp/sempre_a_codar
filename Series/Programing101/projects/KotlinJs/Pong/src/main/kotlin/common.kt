import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Defines the representation for locations in the game arena.
 * @property x  The horizontal coordinate.
 * @property y  The vertical coordinate.
 */
data class Location(val x: Double, val y: Double)

/**
 * Magnitude of the vector represented by [location].
 * @param location  The location.
 * @return The [location] vector's magnitude.
 */
fun magnitude(location: Location) = sqrt(location.x.pow(2) + location.y.pow(2))

/**
 * Computes the dot product between two vectors.
 * @param loc1  The first vector (represented by a [Location] instance).
 * @param loc2  The second vector (represented by a [Location] instance).
 * @return the dot product value.
 */
fun dotProduct(loc1: Location, loc2: Location) = loc1.x * loc2.x + loc1.y * loc2.y

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
 * Computes the midpoint of the given line segment.
 * @param line  The line segment.
 * @return the line's midpoint.
 */
fun midpoint(line: Line) = Location((line.start.x + line.end.x) / 2, (line.start.y + line.end.y) / 2)

/**
 * Computes the distance between the given point and the line's midpoint.
 * @param line      The line.
 * @param point     The point.
 * @return the distance between the [point] and the [line]'s midpoint.
 */
fun distanceToMidpoint(line: Line, point: Location): Double {
    val midpoint = midpoint(line)
    return sqrt((point.x - midpoint.x).pow(2) + (point.y - midpoint.y).pow(2))
}

/**
 * Function used to compute the intersection point between two lines.
 * @param line1     The first line.
 * @param line2     The second line.
 * @return the intersection point, if one exists, or null if the two lines do not intersect each other.
 */
fun intersection(line1: Line, line2: Line): Location? {

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