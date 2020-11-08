package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.madlevel4task2.model.Move
import com.example.madlevel4task2.model.Result
import com.example.madlevel4task2.model.Winner
import com.example.madlevel4task2.repository.ResultRepository
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class GameFragment : Fragment() {

    private lateinit var currentGameResult: Result

    // Room
    private lateinit var resultRepository: ResultRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        resultRepository = ResultRepository(requireContext())
    }

    private fun initViews() {
        input_rock.setOnClickListener {
            play(Move.ROCK)
        }
        input_paper.setOnClickListener {
            play(Move.PAPER)
        }
        input_scissors.setOnClickListener {
            play(Move.SCISSORS)
        }

        bt_history.setOnClickListener {
            findNavController().navigate(R.id.action_GameFragment_to_HistoryFragment)
        }
    }

    private fun updateViews() {
        tv_game_winner.text = getString(currentGameResult.winner.getText())
        move_computer.setImageResource(currentGameResult.computerMove.getImage())
        move_player.setImageResource(currentGameResult.playerMove.getImage())
    }

    private fun addCurrentResult() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                resultRepository.insertResult(currentGameResult)
            }
        }
    }

    private fun play(playerMove: Move) {
        var currentComputerMove = computerMove()
        var currentWinner = checkGame(playerMove, currentComputerMove)

        // Sets the current result
        currentGameResult = Result(
            date = Calendar.getInstance().time,
            playerMove = playerMove,
            computerMove = currentComputerMove,
            winner = currentWinner
        )

        updateViews()
        addCurrentResult()
    }

    private fun computerMove(): Move {
        var randomMove =(1..3).random()

        when (randomMove) {
            1 -> {
                return Move.ROCK
            }
            2 -> {
                return Move.PAPER
            }
            3 -> {
                return Move.SCISSORS
            }
            else -> return Move.ROCK
        }
    }

    private fun checkGame(playerMove: Move, computerMove: Move): Winner {
        if (playerMove == Move.ROCK && computerMove == Move.ROCK) {
            return Winner.DRAW
        } else if (playerMove == Move.ROCK && computerMove == Move.PAPER) {
            return Winner.COMPUTER
        } else if (playerMove == Move.ROCK && computerMove == Move.SCISSORS) {
            return Winner.PLAYER
        } else if (playerMove == Move.PAPER && computerMove == Move.ROCK) {
            return Winner.PLAYER
        } else if (playerMove == Move.PAPER && computerMove == Move.PAPER) {
            return Winner.DRAW
        } else if (playerMove == Move.PAPER && computerMove == Move.SCISSORS) {
            return Winner.COMPUTER
        } else if (playerMove == Move.SCISSORS && computerMove == Move.ROCK) {
            return Winner.COMPUTER
        } else if (playerMove == Move.SCISSORS && computerMove == Move.PAPER) {
            return Winner.PLAYER
        } else if (playerMove == Move.SCISSORS && computerMove == Move.SCISSORS) {
            return Winner.DRAW
        } else {
            return Winner.DRAW
        }
    }
}