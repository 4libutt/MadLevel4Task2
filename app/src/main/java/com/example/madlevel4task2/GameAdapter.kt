package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_rps_overview.*
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun databind(game: Game) {
            itemView.tvDate.text = game.date
            itemView.tvGameResults.text = game.result
            when(game.computerChoice) {
                "rock" -> {
                    itemView.ivPCMove.setImageResource(R.drawable.rock)
                }
                "paper" -> {
                    itemView.ivPCMove.setImageResource(R.drawable.paper)
                }
                "scissors" -> {
                    itemView.ivPCMove.setImageResource(R.drawable.scissors)
                }
            }
            when(game.userChoice) {
                "rock" -> {
                    itemView.ivUserMove.setImageResource(R.drawable.rock)
                }
                "paper" -> {
                    itemView.ivUserMove.setImageResource(R.drawable.paper)
                }
                "scissors" -> {
                    itemView.ivUserMove.setImageResource(R.drawable.scissors)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GameAdapter.ViewHolder, position: Int) {
        holder.databind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }
}