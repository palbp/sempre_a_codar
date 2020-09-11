import org.w3c.dom.Audio

/**
 * Singleton object used to play the game's sounds.
 */
private object Sounds {
    val batHit = Audio("bat_hit_1.wav")
    val defaultHit = Audio("hit.wav")
}

/**
 * Plays a sound if one should be played.
 * @param arena The arena instance
 */
fun maybePlaySound(arena: Arena) {
    when (arena.ball.deflection) {
        Deflection.BY_BAT -> Sounds.batHit.play()
        Deflection.OTHER -> Sounds.defaultHit.play()
    }
}

