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