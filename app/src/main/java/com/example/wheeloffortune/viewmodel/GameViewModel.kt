package com.example.wheeloffortune.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.wheeloffortune.Data.DataSource

class GameViewModel : ViewModel() {

    val loadCategories = DataSource().loadChosenCategory()

    private val _health = MutableLiveData<Int>(5)
    val health: LiveData<Int> = _health

    private val _points = MutableLiveData<Int>(0)
    val points: LiveData<Int> = _points

    private val _character = MutableLiveData<MutableList<Char>>()
    val character: LiveData<List<Char>> = Transformations.map(_character) {
        it.toList()

    }

}
