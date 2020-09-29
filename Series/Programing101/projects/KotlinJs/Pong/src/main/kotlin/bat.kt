/**
 * Defines the representation of the players' bats.
 * @property location   The bat's location (its center).
 * @property width      The bat's width.
 * @property height     The bat's height.
 */
data class Bat(val location: Location, val width: Double, val height: Double)

/**
 * Gets the horizontal coordinate of the bat's left edge.
 * @param bat   The bat instance
 * @return The bat's left edge
 */
fun getBatLeftEdge(bat: Bat) = Line(
        Location(bat.location.x - bat.width / 2, bat.location.y - bat.height / 2),
        Location(bat.location.x - bat.width / 2, bat.location.y + bat.height / 2)
)

/**
 * Gets the horizontal coordinate of the bat's right edge.
 * @param bat   The bat instance
 * @return The bat's right edge
 */
fun getBatRightEdge(bat: Bat) = Line(
        Location(bat.location.x + bat.width / 2, bat.location.y - bat.height / 2),
        Location(bat.location.x + bat.width / 2, bat.location.y + bat.height / 2)
)

/**
 * Checks whether the given bat is within the specified bounds.
 * @param bat           The bat instance.
 * @param height        The height of the admissible vertical bounds.
 * @param margin        The gap to be preserved between the bat and the vertical boundaries.
 * @return A boolean value indicating if [bat] is within the arena's bounds or not.
 */
private fun isBatWithinBounds(bat: Bat, height: Double, margin: Double) =
        bat.location.y - bat.height / 2.0 - margin >= 0.0 &&
                bat.location.y + bat.height / 2.0 + margin <= height

/**
 * Places the bat within the arena bounds.
 * @param bat           The bat instance.
 * @param height        The height of the admissible vertical bounds.
 * @param margin        The gap to be preserved between the bat and the vertical boundaries.
 * @return A bat instance located within the arena's bounds.
 */
private fun placeBatWithinBounds(bat: Bat, height: Double, margin: Double) = buildBatWith(
        bat,
        Location(
                bat.location.x,
                if (bat.location.y - bat.height / 2.0 - margin < 0.0) bat.height / 2.0 + margin
                else height - bat.height / 2.0 - margin
        )
)

/**
 * Builds a new bat instance from the given one ([bat]) and with a new location [newLocation].
 * @param bat           The bat instance to be used as a prototype for the new instance.
 * @param newLocation   The location for the new instance.
 * @return A [Bat] ]instance with all its properties copied from [bat] and with [newLocation] as its
 * location.
 */
fun buildBatWith(bat: Bat, newLocation: Location) = Bat(newLocation, bat.width, bat.height)

/**
 * Keeps the bat within the arena bounds, regardless of its current position.
 * @param bat           The bat instance.
 * @param height        The height of the admissible vertical bounds.
 * @param margin        The gap to be preserved between the bat and the vertical boundaries.
 * @return a bat instance located inside the arena bounds.
 */
fun keepBatInVerticalBounds(bat: Bat, height: Double, margin: Double) =
        if (isBatWithinBounds(bat, height, margin)) bat
        else placeBatWithinBounds(bat, height, margin)
