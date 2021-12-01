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

    private lateinit var underScoreWord: String

    private val _character = MutableLiveData<MutableList<Char>>()
    val character: LiveData<List<Char>> = Transformations.map(_character) {
        it.toList()



    }

    private val _topic = MutableLiveData<String>()
    val topic: LiveData<String> = _topic

    private val _word = MutableLiveData<String>()
    val word: LiveData<String> = _word

    fun randomTopic() {
        var randomGenerator = loadCategories.random()

        _topic.value = randomGenerator.topic

        _word.value = randomGenerator.words.random()

    }

   fun generateUnderScores(words : String) {
        val unders = StringBuilder()
        words.forEach {
            if ( it == ' ') {
                unders.append(" ")

            } else {
                unders.append("_")
            }

        }
    }


    fun showHealthAndPoints() {
        _health.value
        _points.value
    }



    private var guessedInputString = ""

    fun guessWord(input: String) {
        var sanitizedInput = input[0].lowercase()
       // Log.d("Andreas", sanitizedInput.toString())
        guessedInputString += sanitizedInput
       // Log.d("Andreas", guessedInputString.toString())
        var wordToGuess = word.value?.trim(' ')?.lowercase()
       // Log.d("Andreas", wordToGuess.toString())
        var wordContainsInput = wordToGuess?.contains(sanitizedInput.lowercase().toCharArray().first(), ignoreCase = true)
        //Log.d("Andreas", wordContainsInput.toString())
        val countOfInputInWord = wordToGuess?.count{
            //Log.d("Andreas", it.toString() + sanitizedInput.contains(it).toString())
            sanitizedInput.contains(it)
        }
        // Log.d("Andreas", countOfInputInWord.toString())
        if (wordContainsInput!!) {
           var pointsToWin = countOfInputInWord!! * 100
            _points.value = points.value!! + pointsToWin
        } else {
            _health.value = health.value!! - 1
        }
        val indexes = mutableListOf<Int>()

        wordToGuess?.forEachIndexed { index, char ->
            if(Char.equals(wordContainsInput)) {
                indexes.add(index)
            }

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
        if(result == "Misset tur") {
            handleMissedTurn()


        }
        if(result == "bankerot") {
            handleBankrupt()
        }

        if(result == "Ekstra forsøg") {
            handleExtraTurn()
            return false

        }

        if(result == "Guess Word")
        {
            return false
        }

            return true
        result
    }


}
