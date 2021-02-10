package edu.sempreacodar.drag.draw

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.sempreacodar.drag.databinding.ActivityDrawingBinding

/**
 * The drawing screen.
 *
 * TODO:
 */
class DrawingActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDrawingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}