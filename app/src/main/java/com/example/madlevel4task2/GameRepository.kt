package com.example.madlevel4task2

import android.content.Context

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
