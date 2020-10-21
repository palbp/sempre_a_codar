import pt.isel.canvas.playSound

/**
 * Singleton object used to play the game's sounds.
 */
private object Sounds {
    val batHit = "bat_hit_1.wav"
    val defaultHit = "hit.wav"
}

/**
 * Plays a sound if one should be played.
 * @param arena The arena instance
 */
fun maybePlaySound(arena: Arena) {
    when (arena.ball.deflection) {
        Deflection.BY_BAT -> playSound(Sounds.batHit)
        Deflection.OTHER -> playSound(Sounds.defaultHit)
    }
}

