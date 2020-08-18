import kotlin.math.max
import kotlin.math.min

data class Bat(val location: Location, val width: Double, val height: Double)

fun keepBatInArenaBounds(bat: Bat, arenaHeight: Double): Bat {
    val margin = 20.0
    return if (bat.location.y - bat.height / 2.0 - margin >= 0.0 && bat.location.y + bat.height / 2.0 + margin <= arenaHeight)
        bat
    else Bat(
        Location(
            bat.location.x,
            if (bat.location.y - bat.height / 2.0 - margin < 0.0)
                max(bat.location.y, bat.height / 2.0 + margin)
            else
                min(bat.location.y, arenaHeight - bat.height / 2.0 - margin)
        ),
        bat.width,
        bat.height
    )
}

