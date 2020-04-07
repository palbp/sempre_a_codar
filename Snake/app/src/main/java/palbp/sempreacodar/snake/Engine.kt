package palbp.sempreacodar.snake

import android.graphics.Canvas
import android.view.SurfaceHolder
import androidx.annotation.AnyThread
import palbp.sempreacodar.snake.physics.Location
import palbp.sempreacodar.snake.physics.Velocity

/**
 * The game engine.
 */
@AnyThread
class Engine {

    private fun initializeWorld(arena: ArenaView) {
        if (arena.snake == null) {
            arena.snake = Snake(
                position = Location(arena.width/2f, arena.height/2f),
                velocity = Velocity(0f, 3f),
                headRadius = 50f,
                bounds = Location(arena.width.toFloat(), arena.height.toFloat())
            )
        }
    }

    private fun doStep(holder: SurfaceHolder, arena: ArenaView) {
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
       get() = worker != null

    /**
     * Starts the game engine
     *
     * @param   holder  The surface holder used to draw the game UI
     * @param   arena   The view that displays the game arena
     */
    fun start(holder: SurfaceHolder, arena: ArenaView) {

        if (isStarted)
            return

        worker = kotlin.concurrent.thread(name = "Engine thread") {
            initializeWorld(arena)
            while (isStarted) {
                doStep(holder, arena)
            }
        }
    }

    /**
     * Stops the engine, blocking the calling thread until the engine thread has stopped
     */
    fun stop() {
        val terminatingWorker = worker ?: return
        worker = null
        terminatingWorker.interrupt()
        terminatingWorker.join()
    }
}