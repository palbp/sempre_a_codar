package edu.sempreacodar.drag.review

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import edu.sempreacodar.drag.ui.theme.DRAGTheme

/**
 * The round review screen.
 */
class RoundReviewActivity : AppCompatActivity() {

    private val viewModel: RoundReviewViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DRAGTheme {
                Surface(color = MaterialTheme.colors.background) {
                    // TODO
                }
            }
        }
    }
}
