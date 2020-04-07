package palbp.sempreacodar.snake

import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceHolder.Callback
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

@MainThread
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arenaView.holder.addCallback(object : Callback {
            lateinit var engine: Engine
            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                engine.start(holder, arenaView)
            }

            override fun surfaceCreated(holder: SurfaceHolder) {
                engine = Engine()
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                engine.stop()
            }
        })
    }
}
