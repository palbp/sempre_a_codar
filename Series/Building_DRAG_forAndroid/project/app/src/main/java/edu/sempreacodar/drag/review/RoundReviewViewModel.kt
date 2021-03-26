package edu.sempreacodar.drag.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.sempreacodar.drag.model.Round
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
        viewModelScope.launch {
            delay(5000)
            _round.value = Round()
            _currentMoveIndex.value = 0
        }
    }

    fun navigateToNextMove() {
        val index = _currentMoveIndex.value
        if (index != null) {
            // TODO: make it conditional to the Round size
            _currentMoveIndex.value = index + 1
        }
    }

    fun navigateToPreviousMove() {
        val index = _currentMoveIndex.value
        if (index != null && index > 0) {
            _currentMoveIndex.value = index - 1
        }
    }
}