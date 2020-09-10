import org.w3c.dom.Audio
import org.w3c.dom.HTMLAudioElement

/**
 * Defines the representation for holding the two instances that are used to play sound effects (a.k.a sfx).
 * @property batHit     The instance used to play the sfx of the ball hitting the bat.
 * @property hit        The instance used to play the sfx of all other ball collisions.
 */
data class AudioHandles(val batHit: HTMLAudioElement, val hit: HTMLAudioElement)

/**
 * Initializes the instances used to play sound effects, returning them.
 * @return  An aggregate of the existing sfx handles.
 */
fun initializeAudio() = AudioHandles(
        Audio("bat_hit_1.wav"),
        Audio("hit.wav")
)

