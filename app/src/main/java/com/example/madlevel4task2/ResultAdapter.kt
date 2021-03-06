package com.example.madlevel4task2

import android.content.Context
import com.example.madlevel4task2.model.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_result.view.*

class ResultAdapter(private val results: List<Result>): RecyclerView.Adapter<ResultAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun databind(result: Result) {

            // Set date
            itemView.item_tv_date.text = result.date.toString()

            // Viewholder needs context to access Resources
            // TODO move this to enum itself
            when (result.winner) {
                Winner.COMPUTER -> {
                    itemView.item_tv_game_winner.text = "Computer wins!"
                }
                Winner.PLAYER -> {
                    itemView.item_tv_game_winner.text = "You win!"
                }
                Winner.DRAW -> {
                    itemView.item_tv_game_winner.text = "Draw"
                }
            }

            // Set image views
            itemView.item_move_computer.setImageResource(result.computerMove.getImage())
            itemView.item_move_player.setImageResource(result.playerMove.getImage())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(results[position])
    }

    override fun getItemCount(): Int {
        return results.size
    }
}