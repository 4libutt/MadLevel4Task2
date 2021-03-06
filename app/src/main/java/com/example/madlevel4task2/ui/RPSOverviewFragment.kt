package com.example.madlevel4task2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.repository.GameRepository
import kotlinx.android.synthetic.main.fragment_rps_overview.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RPSOverviewFragment : Fragment() {
    var userMove = "paper"
    var computerMove = "rock"
    var gameResult = ""

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

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
//        getGamesStatsFromDatabase()

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
                when (computerMove) {
                    getString(R.string.rock) -> {
                        tvResults.text = getString(R.string.draw)
                        gameResult = "draw"
                    }
                    getString(R.string.paper) -> {
                        tvResults.text = getString(R.string.loss)
                        gameResult = "loss"
                    }
                    getString(R.string.scissors) -> {
                        tvResults.text = getString(R.string.win)
                        gameResult = "win"
                    }
                }
                addGame()
            }
            "paper" -> {
                ivUserChoice.setImageResource(R.drawable.paper)
                when (computerMove) {
                    getString(R.string.rock) -> {
                        tvResults.text = getString(R.string.win)
                        gameResult = "win"
                    }
                    getString(R.string.paper) -> {
                        tvResults.text = getString(R.string.draw)
                        gameResult = "draw"
                    }
                    getString(R.string.scissors) -> {
                        tvResults.text = getString(R.string.loss)
                        gameResult = "loss"
                    }
                }
                addGame()
            }
            "scissors" -> {
                ivUserChoice.setImageResource(R.drawable.scissors)
                when (computerMove) {
                    getString(R.string.rock) -> {
                        tvResults.text = getString(R.string.loss)
                        gameResult = "loss"
                    }
                    getString(R.string.paper) -> {
                        tvResults.text = getString(R.string.win)
                        gameResult = "win"
                    }
                    getString(R.string.scissors) -> {
                        tvResults.text = getString(R.string.draw)
                        gameResult = "draw"
                    }
                }
                addGame()
            }
        }
    }

    private fun generatePcMove(){

        when((1..3).random()) {
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

    private fun addGame() {
        val gameDate = Date()
        mainScope.launch {
            val game = Game(
                date = gameDate.toString(),
                computerChoice = computerMove,
                userChoice = userMove,
                result = gameResult

            )

            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }
    }

//    private fun getGamesStatsFromDatabase() {
//        mainScope.launch {
//            val numOfWins = withContext(Dispatchers.IO) { gameRepository.getAllWins() }
//            val numOfDraws = withContext(Dispatchers.IO) { gameRepository.getAllDraws() }
//            val numOfLoses = withContext(Dispatchers.IO) { gameRepository.getAllLoses() }
//
//            tvStatistics.text = getString(R.string.allGameStats, numOfWins, numOfLoses, numOfDraws)
//        }
//    }
}