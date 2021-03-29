package edu.sempreacodar.drag.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.sempreacodar.drag.model.Drawing
import edu.sempreacodar.drag.model.DrawingGuess
import edu.sempreacodar.drag.model.Round
import edu.sempreacodar.drag.model.WordGuess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * The view model for the round review screen.
 */
class RoundReviewViewModel : ViewModel() {

    /**
     * The round data
     */
    private val _round = MutableLiveData<Round?>()
    val round: LiveData<Round?> = _round

    /**
     * The current move index, if one is being shown
     */
    private val _currentMoveIndex: MutableLiveData<Int?> = MutableLiveData()
    val currentMoveIndex: LiveData<Int?> = _currentMoveIndex

    /**
     * Asynchronously fetches the round data and publishes it to [round]
     */
    fun fetchRoundData() {
        // TODO: Implement this for real
        viewModelScope.launch {
            delay(5000)
            _round.value = Round(
                listOf(
                    DrawingGuess("Pintainho", Drawing.EMPTY),
                    WordGuess("Galinha", Drawing.EMPTY),
                    DrawingGuess("Dinossauro", Drawing.EMPTY)
                )
            )
            _currentMoveIndex.value = 0
        }
    }

    /**
     * Navigates to the next move in the round, if one exists.
     */
    fun navigateToNextMove() {
        val index = _currentMoveIndex.value
        if (index != null && compareValues(index + 1, _round.value?.moves?.size) < 0) {
            _currentMoveIndex.value = index + 1
        }
    }

    /**
     * Navigates to the previous move in the round, if one exists.
     */
    fun navigateToPreviousMove() {
        val index = _currentMoveIndex.value
        if (index != null && index > 0) {
            _currentMoveIndex.value = index - 1
        }
    }
}