package edu.sempreacodar.drag.draw

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.sempreacodar.drag.draw.model.GameTimer
import edu.sempreacodar.drag.draw.model.currentTimeSeconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun countDownFrom(value: GameTimer, publishTo: MutableLiveData<GameTimer>) {
        val startTimeInSeconds = currentTimeSeconds()
        val endTimeInSeconds = startTimeInSeconds + value.toSeconds()

        while(currentTimeSeconds() < endTimeInSeconds) {
            delay(250)
            publishTo.value = GameTimer.fromSeconds((endTimeInSeconds - currentTimeSeconds()).toInt())
        }
}

class DrawingViewModel : ViewModel() {

    private val _timerValue = MutableLiveData<GameTimer>()
    val timerValue: LiveData<GameTimer> = _timerValue

    val model = Drawing()

    fun maybeStartTimer(theTimerValue: GameTimer) {

        // TODO: make this conditional. Only start if not started
        _timerValue.value = theTimerValue
        viewModelScope.launch { countDownFrom(theTimerValue, _timerValue) }
    }

    // TODO: Extend view model with drawing operations
}