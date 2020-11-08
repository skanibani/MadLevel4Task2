package com.example.madlevel4task2.model

import com.example.madlevel4task2.R

enum class Winner {
    PLAYER {
        override fun getText(): Int = R.string.player_win

    },
    COMPUTER {
        override fun getText(): Int = R.string.computer_win
    },
    DRAW {
        override fun getText(): Int = R.string.draw
    };

    abstract fun getText(): Int
}