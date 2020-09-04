import org.w3c.dom.Audio
import org.w3c.dom.HTMLAudioElement

data class AudioHandles(val batHit: HTMLAudioElement, val hit: HTMLAudioElement)

fun initializeAudio() = AudioHandles(
        Audio("bat_hit_1.wav"),
        Audio("hit.wav")
)
