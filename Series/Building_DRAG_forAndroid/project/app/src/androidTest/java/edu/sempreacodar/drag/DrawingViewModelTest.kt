package edu.sempreacodar.drag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.sempreacodar.drag.draw.DrawingViewModel
import edu.sempreacodar.drag.draw.countDownFrom
import edu.sempreacodar.drag.draw.model.GameTimer
import edu.sempreacodar.drag.draw.model.currentTimeSeconds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DrawingViewModelTest {

    private val oneSecond = GameTimer.fromSeconds(1)

    @Test
    fun countDownFrom_publishesToLiveData() = runBlocking(Dispatchers.Main) {

        var publishToCount = false
        val publishedGameTimer = MutableLiveData<GameTimer>()

        publishedGameTimer.observeWithDuring(observer = { publishToCount = true }) {
            val countDown = launch {
               countDownFrom(oneSecond, publishedGameTimer)
            }
            countDown.join()
        }

        assertTrue(publishToCount)
    }

    @Test
    fun countDownFrom_delaysContinuation() = runBlocking(Dispatchers.Main) {

        val publishedGameTimer = MutableLiveData<GameTimer>()
        val secondsToDelay = 2
        val startTime = currentTimeSeconds()
        val countDown = launch {
            countDownFrom(GameTimer.fromSeconds(secondsToDelay), publishedGameTimer)
        }
        countDown.join()
        assertTrue(currentTimeSeconds() - startTime >= secondsToDelay)
    }

    @Test
    fun viewModelStartsInInitialState() {
        val sut = DrawingViewModel()
        assertEquals(DrawingViewModel.State.INITIALIZED, sut.state.value)
    }

    @Test
    fun viewModelMaybeStartTimer_inInitialState_startsTimer() = runBlocking(Dispatchers.Main) {

        val sut = DrawingViewModel()
        var timerStarted = false

        sut.timerValue.observeWithDuring(observer = { timerStarted = true }) {
            val countDown = sut.maybeStartTimer(GameTimer.fromSeconds(1))
            assertNotNull(countDown)
            countDown?.join()
        }

        assertTrue(timerStarted)
    }

    @Test
    fun viewModelMaybeStartTimer_inNonInitialState_doesNotStartTimer(): Unit = runBlocking(Dispatchers.Main) {

        val sut = DrawingViewModel()
        val countDown = sut.maybeStartTimer(oneSecond)
        assertNotNull(countDown)
        val secondCountDown = sut.maybeStartTimer(oneSecond)
        assertNull(secondCountDown)
        countDown?.join()
    }
}

/**
 * Helper function that ensures observation of [this] with [observer] and cleans up once
 * [testCode] finishes execution
 */
private suspend fun <T> LiveData<T>.observeWithDuring(
        observer: ((T) -> Unit),
        testCode: suspend () -> Unit) {

    try {
        observeForever(observer)
        testCode()
    }
    finally {
        removeObserver(observer)
    }
}
