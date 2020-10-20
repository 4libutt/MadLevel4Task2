package com.example.madlevel4task2

import androidx.annotation.DrawableRes

data class Game (
    var date : String,
    @DrawableRes var computerChoice: Int,
    @DrawableRes var userChoice: Int,
    var result: String
)
