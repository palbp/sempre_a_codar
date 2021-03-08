package edu.sempreacodar.drag.model

/**
 * Sum type used to define guesses which can be either: a [WordGuess], when the player has to
 * try to guess the word depicted by the drawing; and a [DrawingGuess], when the player has to
 * draw (a drawing guess) the received word.
 */
sealed class Guess
data class WordGuess(val word: String?, val drawing: Drawing) : Guess()
data class DrawingGuess(val word: String, val drawing: Drawing?) : Guess()

/**
 * A round is comprised of a list of guesses.
 */
data class Round(val moves: List<Guess> = listOf())
