package com.example.madlevel4task2.repository

import android.content.Context
import com.example.madlevel4task2.dao.GameDao
import com.example.madlevel4task2.database.GamesRoomDatabase
import com.example.madlevel4task2.model.Game

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = GamesRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

//    suspend fun getAllWins() {
//        return gameDao.getGamesResultWins()
//    }
//
//    suspend fun getAllDraws() {
//        return gameDao.getGamesResultDraws()
//    }
//
//    suspend fun getAllLoses() {
//        return gameDao.getGamesResultLosses()
//    }

}
