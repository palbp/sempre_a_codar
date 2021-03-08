package edu.sempreacodar.drag.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.sempreacodar.drag.model.Round

/**
 * The view model for the round review screen.
 */
class RoundReviewViewModel : ViewModel() {

    /**
     * The round data
     */
    private val _round = MutableLiveData(Round())
    val round: LiveData<Round> = _round

    /**
     * Asynchronously fetches the round data and publishes it to [round]
     */
    fun fetchRoundData() {
        TODO()
    }
}