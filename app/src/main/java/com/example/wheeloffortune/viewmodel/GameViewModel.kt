package com.example.wheeloffortune.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.wheeloffortune.Data.DataSource

class GameViewModel : ViewModel() {

    private val _health = MutableLiveData<Int>(5)
    val health: LiveData<Int> = _health

    private val _points = MutableLiveData<Int>(0)
    val points: LiveData<Int> = _points

    val loadCategories = DataSource().words

    private lateinit var underScoreInsteadOfChar: String

    var guessedInputList = mutableListOf<Char>()

    private var usedChars: String = ""

    private val _topic = MutableLiveData<String>()
    val topic: LiveData<String> = _topic

    private val _word = MutableLiveData<String>()
    val word: LiveData<String> = _word

    fun randomTopic() {
        var randomGenerator = loadCategories.random()

        _topic.value = randomGenerator.topic

        _word.value = randomGenerator.words.random()


    }


    fun generateUnderScores(): String {
        val unders = StringBuilder()
        word.value?.forEach {
            if (it == ' ') {
                unders.append(" ")

            } else {
                if(guessedInputString.contains(it, ignoreCase = true)){
                    unders.append(it)

                } else {
                    unders.append("_")
                }
            }

        }
        return unders.toString()
    }




    fun showHealthAndPoints() {
        _health.value
        _points.value
    }

    fun inputOK(input: String): Boolean {
        var sanitizedInput = input[0].lowercase().single()
        if(guessedInputList.contains(sanitizedInput) || input.isNullOrEmpty()) {
            return false

        } else {
            return true
        }

    }


    private var guessedInputString = ""
    var isWinner = false

    fun guessWord(input: String) {
        var sanitizedInput = input[0].lowercase()

        guessedInputList.add(sanitizedInput.single())



        guessedInputString += sanitizedInput

        // https://stackoverflow.com/questions/60028103/how-to-remove-all-the-whitespaces-from-a-string-in-kotlin


        var wordToGuess = word.value?.filter { !it.isWhitespace() }?.lowercase()


        var wordContainsInput = wordToGuess?.contains(
            sanitizedInput.lowercase().toCharArray().first(),
            ignoreCase = true
        )

        val countOfInputInWord = wordToGuess?.count {

            sanitizedInput.contains(it)
        }


        if (wordContainsInput!!) {
            var pointsToWin = countOfInputInWord!! * 2
            Log.d("Andreas", pointsToWin.toString())
            _points.value = points.value!! + pointsToWin
            Log.d("Andreas", _points.value.toString())
        } else {
            _health.value = health.value!! - 1
        }
        Log.d("Andreas", wordToGuess.toString())
        var lettersInWordList = wordToGuess?.toList()

        Log.d("andreas", guessedInputList.toString() )
        Log.d("andreas", lettersInWordList.toString() )
        if(guessedInputList.containsAll(lettersInWordList!!)) {
            Log.d("Andreas", "du har vundet et eller andet" )
            isWinner = true

        }


    }



    private fun handleExtraTurn() {
        print("Extra Turn")
        _health.value = health.value!! + 1
    }

    private fun handleMissedTurn() {
        print("Missed turn and lost a life")
        _health.value = health.value!! - 1
    }


    private fun handleBankrupt() {
        print("you are bankrupt")
        _points.value = 0
    }


    fun spinWheel(): Boolean {

        val result = when ((0..100).random()) {
            in 0..65 -> "Guess Word"
            in 66..81 -> "Ekstra forsøg"
            in 82..95 -> "Misset tur"
            else -> "bankerot"
        }
        if (result == "Misset tur") {
            handleMissedTurn()


        }
        if (result == "bankerot") {
            handleBankrupt()
        }

        if (result == "Ekstra forsøg") {
            handleExtraTurn()
            return false

        }

        if (result == "Guess Word") {
            return false
        }

        return true

    }
}
