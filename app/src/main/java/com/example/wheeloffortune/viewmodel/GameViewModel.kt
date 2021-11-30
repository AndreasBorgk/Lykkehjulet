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


    private var guessedInput = ""

    fun guessWord(input: String) {
        guessedInput += input
        var wordToGuess = word.value
        var inputInWord = wordToGuess?.trim(' ')?.contains(guessedInput, ignoreCase = true)
        val countOfInputInWord = wordToGuess?.count{
            input.contains(it)
        }
        if (inputInWord!!) {
           var pointsToWin = countOfInputInWord!! * 100
            _points.value = points.value!! + pointsToWin
        } else {
            _health.value = health.value!! - 1
        }

    }

    fun spinWheel() {

        var result = when((0..100).random()) {
            in 0..60 -> "Spin"
            in 61..75 -> "Ekstra forsøg"
            in 76..90 -> "Misset tur"
            else -> "bankerot"
        }
        if(result == "Spin"){


        }
        if(result == "Ekstra forsøg") {
            _health.value = health.value!! + 1
            spinWheel()

        }
        if(result == "Misset tur") {
            _health.value = health.value!! - 1

        }
        if(result == "bankerot") {
            _points.value = 0
        }

    }
}
