package edu.sempreacodar.drag.draw

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.sempreacodar.drag.model.Drawing
import edu.sempreacodar.drag.model.GameTimer
import edu.sempreacodar.drag.model.Point
import edu.sempreacodar.drag.model.currentTimeSeconds
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

        publishedGameTimer.observeWithDuringAsync(observer = { publishToCount = true }) {
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
    fun viewModelStartsWithAnEmptyDrawing() {
        val sut = DrawingViewModel()
        assertNotNull(sut.drawing.value)
        assertTrue(sut.drawing.value?.lines?.isEmpty() ?: false)
    }

    @Test
    fun maybeStartTimer_inInitialState_startsTimer() = runBlocking(Dispatchers.Main) {

        val sut = DrawingViewModel()
        var timerStarted = false

        sut.timerValue.observeWithDuringAsync(observer = { timerStarted = true }) {
            val countDown = sut.maybeStartTimer(GameTimer.fromSeconds(1))
            assertNotNull(countDown)
            countDown?.join()
        }

        assertTrue(timerStarted)
    }

    @Test
    fun maybeStartTimer_inNonInitialState_doesNotStartTimer(): Unit = runBlocking(Dispatchers.Main) {

        val sut = DrawingViewModel()
        val countDown = sut.maybeStartTimer(oneSecond)
        assertNotNull(countDown)
        val secondCountDown = sut.maybeStartTimer(oneSecond)
        assertNull(secondCountDown)
        countDown?.join()
    }

    @Test
    fun startLineAt_publishesDrawingWithTheNewLine() = runBlocking(Dispatchers.Main) {
        val sut = DrawingViewModel()
        var publishedDrawing: Drawing? = null

        sut.drawing.observeWithDuring({ publishedDrawing = it}) {
            val aPoint = Point(2F, 2F)
            sut.startLineAt(aPoint)
            assertNotNull(publishedDrawing?.lastLine)
            assertEquals(aPoint, publishedDrawing?.lastLine?.points?.last())
            assertTrue(publishedDrawing?.lines?.isEmpty() ?: false)
        }
    }

    @Test
    fun addToLine_publishesDrawingWithLastLineEndingAtThatPoint() = runBlocking(Dispatchers.Main) {
        val sut = DrawingViewModel()
        var publishedDrawing: Drawing? = null

        sut.drawing.observeWithDuring({ publishedDrawing = it}) {
            val aPoint = Point(2F, 2F)
            sut.startLineAt(aPoint)
            assertEquals(aPoint, publishedDrawing?.lastLine?.points?.last())
            assertTrue(publishedDrawing?.lines?.isEmpty() ?: false)
        }
    }


    @Test
    fun startLineAt_publishesDrawingWithNewLineAtEndAndPreviousLastLineAddedToList() = runBlocking(Dispatchers.Main) {
        val sut = DrawingViewModel()
        var publishedDrawing: Drawing? = null
        sut.startLineAt(Point(1F, 1F))
        sut.endLineAt(Point(2F, 2F))

        sut.drawing.observeWithDuring({ publishedDrawing = it}) {
            val addedPoint = Point(4F, 4F)
            sut.startLineAt(Point(3F, 3F))
            sut.addToLine(addedPoint)
            assertEquals(addedPoint, publishedDrawing?.lastLine?.points?.last())
            assertTrue(publishedDrawing?.lines?.isNotEmpty() ?: false)
        }
    }
}

/**
 * Helper function that ensures observation of [this] with [observer] and cleans up once
 * [testCode] finishes execution
 */
private suspend fun <T> LiveData<T>.observeWithDuringAsync(
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

/**
 * Helper function that ensures observation of [this] with [observer] and cleans up once
 * [testCode] finishes execution
 */
private fun <T> LiveData<T>.observeWithDuring(
        observer: ((T) -> Unit),
        testCode: () -> Unit) {

    try {
        observeForever(observer)
        testCode()
    }
    finally {
        removeObserver(observer)
    }
}
