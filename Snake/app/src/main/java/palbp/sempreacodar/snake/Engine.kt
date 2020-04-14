package palbp.sempreacodar.snake

import android.graphics.Canvas
import android.view.SurfaceHolder
import androidx.annotation.AnyThread
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import palbp.sempreacodar.snake.physics.Location
import palbp.sempreacodar.snake.physics.Vector2D
import palbp.sempreacodar.snake.physics.Velocity

/**
 * The game engine.
 */
class Engine {

    private fun initializeWorld(arena: ArenaView, arenaBounds: Location) {
        if (arena.snake == null) {
            arena.snake = Snake(
                position = Location(arenaBounds.x/2, arenaBounds.y/2),
                velocity = Velocity.Stopped,
                headRadius = 30f,
                bounds = arenaBounds)
        }
    }

    private fun doStep(holder: SurfaceHolder, arena: ArenaView) {
        arena.snake?.velocity = snakeVelocity
        arena.snake?.move()
        draw(holder, arena)
    }

    private fun draw(holder: SurfaceHolder, arena: ArenaView) {
        var canvas: Canvas? = null
        try {
            canvas = holder.lockCanvas()
            arena.draw(canvas)
        } finally {
            if (canvas != null) {
                // The following is a blocking operation, meaning, the current thread only proceeds
                // after the canvas is actually delivered to the image consumer. The consumption
                // rate is determined by the device's FPS
                holder.unlockCanvasAndPost(canvas)
            }
        }
    }

    /**
     * The engine's worker thread. The property is also used to flag whether the engine is running
     * or not.
     */
    @Volatile
    private var worker: Thread? = null

    /**
     * Indicates whether the game engine is running or not
     */
    val isStarted: Boolean
        @AnyThread get() = worker != null

    /**
     * The current velocity of the snake
     */
    @Volatile
    private var snakeVelocity: Velocity = Velocity.Stopped

    /**
     * Starts the game engine
     *
     * @param   holder  The surface holder used to draw the game UI
     * @param   arena   The view that displays the game arena
     * @param   pad     The gmae control pad
     */
    @MainThread
    fun start(holder: SurfaceHolder, arena: ArenaView, pad: ControlPad) {

        if (isStarted)
            return

        pad.listener = object : ControlPad.ControlListener {
            override fun onChangeDirection(direction: Vector2D) {
                snakeVelocity = Velocity.of(direction * 0.3f)
            }
        }

        val bounds = Location(arena.width.toFloat(), arena.height.toFloat())
        worker = kotlin.concurrent.thread(name = "Engine thread") @WorkerThread {
            initializeWorld(arena, bounds)
            while (isStarted) {
                doStep(holder, arena)
            }
        }
    }

    /**
     * Stops the engine, blocking the calling thread until the engine thread has stopped
     */
    @MainThread
    fun stop() {
        val terminatingWorker = worker ?: return
        worker = null
        terminatingWorker.interrupt()
        terminatingWorker.join()
    }
}