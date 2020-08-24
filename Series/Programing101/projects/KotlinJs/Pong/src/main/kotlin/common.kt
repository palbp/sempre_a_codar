
data class Location(val x: Double, val y: Double)

data class Velocity(val dx: Double, val dy: Double)

fun add(location: Location, velocity: Velocity)
        = Location(location.x + velocity.dx, location.y + velocity.dy)