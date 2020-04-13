package palbp.sempreacodar.snake

import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceHolder.Callback
import android.view.View
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

@MainThread
class MainActivity : AppCompatActivity() {

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideSystemUI()
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
