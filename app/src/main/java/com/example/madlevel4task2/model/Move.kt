package com.example.madlevel4task2.model

import com.example.madlevel4task2.R


enum class Move {
    ROCK {
        override fun getImage(): Int = R.drawable.rock
    },
    PAPER {
        override fun getImage(): Int = R.drawable.paper
    },
    SCISSORS {
        override fun getImage(): Int = R.drawable.scissors
    };

    abstract fun getImage(): Int
}