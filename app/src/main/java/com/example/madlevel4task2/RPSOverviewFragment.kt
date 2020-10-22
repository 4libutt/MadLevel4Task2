package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_rps_overview.*
import kotlinx.android.synthetic.main.item_game.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RPSOverviewFragment : Fragment() {
    var userMove = "paper"
    var computerMove = "rock"

    private lateinit var gameRepository: GameRepository

    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rps_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())

        ivRock.setOnClickListener {
            checkGameResult(ivRock)
        }

        ivPaper.setOnClickListener {
            checkGameResult(ivPaper)
        }

        ivScissors.setOnClickListener {
            checkGameResult(ivScissors)
        }
    }

    private fun checkGameResult(view: View) {
        userMove = view.contentDescription.toString()
        generatePcMove()


        when(userMove) {
            "rock" -> {
                ivUserChoice.setImageResource(R.drawable.rock)
                when(computerMove){
                    getString(R.string.rock) -> {
                        tvResults.text = getString(R.string.draw)
                    }
                    getString(R.string.paper) -> {
                        tvResults.text = getString(R.string.loss)
                    }
                    getString(R.string.scissors) -> {
                        tvResults.text = getString(R.string.win)
                    }
                }
            }
            "paper" -> {
                ivUserChoice.setImageResource(R.drawable.paper)
                when(computerMove){
                    getString(R.string.rock) -> {
                        tvResults.text = getString(R.string.win)
                    }
                    getString(R.string.paper) -> {
                        tvResults.text = getString(R.string.draw)
                    }
                    getString(R.string.scissors) -> {
                        tvResults.text = getString(R.string.loss)
                    }
                }
            }
            "scissors" -> {
                ivUserChoice.setImageResource(R.drawable.scissors)
                when(computerMove){
                    getString(R.string.rock) -> {
                        tvResults.text = getString(R.string.loss)
                    }
                    getString(R.string.paper) -> {
                        tvResults.text = getString(R.string.win)
                    }
                    getString(R.string.scissors) -> {
                        tvResults.text = getString(R.string.draw)
                    }
                }
            }
        }
    }

    private fun generatePcMove(){
        val pcmove = (1..3).random()

        when(pcmove) {
            1 -> {
                computerMove = "rock"

                ivComputerChoice.setImageResource(R.drawable.rock)
            }
            2 -> {
                computerMove = "paper"
                ivComputerChoice.setImageResource(R.drawable.paper)
            }
            3 -> {
                computerMove = "scissors"
                ivComputerChoice.setImageResource(R.drawable.scissors)
            }
        }
    }
}