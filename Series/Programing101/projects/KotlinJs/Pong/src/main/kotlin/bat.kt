import kotlin.math.min

data class Bat(val location: Location, val width: Double, val height: Double)

fun getBatInArenaBounds(bat: Bat, arenaHeight: Double): Bat {
    val margin = 20.0
    return if (bat.location.y - bat.height / 2.0 - margin >= 0.0 && bat.location.y + bat.height / 2.0 + margin <= arenaHeight)
        bat
    else Bat(
        Location(
            bat.location.x,
            min(bat.location.y, arenaHeight - bat.height / 2.0 - margin)
        ),
        bat.width,
        bat.height
    )
}

