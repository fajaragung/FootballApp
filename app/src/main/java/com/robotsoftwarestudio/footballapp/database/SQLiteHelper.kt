package com.robotsoftwarestudio.footballapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.robotsoftwarestudio.footballapp.models.favorite.FavoritesMatch
import com.robotsoftwarestudio.footballapp.models.favorite.FavoritesTeam
import org.jetbrains.anko.db.*

class SQLiteHelper(private val context: Context): ManagedSQLiteOpenHelper(context, "FootballApp.db", null, 1) {

    companion object {
        private var sqlite: SQLiteHelper? = null

        @Synchronized
        fun getInstance(context: Context): SQLiteHelper {

            if(sqlite == null) {
                sqlite = SQLiteHelper(context.applicationContext)
            }

            return sqlite!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(FavoritesMatch.TABLE_NAME, true,
                FavoritesMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,

                //Match
                FavoritesMatch.ID_EVENT_MATCH to INTEGER,
                FavoritesMatch.DATE_EVENT to TEXT,
                FavoritesMatch.DATE_TIME to TEXT,
                FavoritesMatch.ID_HOME_TEAM to INTEGER + UNIQUE,
                FavoritesMatch.ID_AWAY_TEAM to INTEGER + UNIQUE,
                FavoritesMatch.HOME_SCORE to INTEGER,
                FavoritesMatch.AWAY_SCORE to INTEGER,
                FavoritesMatch.HOME_TEAM to TEXT,
                FavoritesMatch.AWAY_TEAM to TEXT,
                FavoritesMatch.HOME_GOAL to TEXT,
                FavoritesMatch.AWAY_GOAL to TEXT,
                FavoritesMatch.HOME_SHOTS to INTEGER,
                FavoritesMatch.AWAY_SHOTS to INTEGER,
                FavoritesMatch.HOME_KEEPER to TEXT,
                FavoritesMatch.AWAY_KEEPER to TEXT,
                FavoritesMatch.HOME_DEFENSE to TEXT,
                FavoritesMatch.AWAY_DEFENSE to TEXT,
                FavoritesMatch.HOME_MID to TEXT,
                FavoritesMatch.AWAY_MID to TEXT,
                FavoritesMatch.HOME_FORWARD to TEXT,
                FavoritesMatch.AWAY_FORWARD to TEXT,
                FavoritesMatch.HOME_SUBS to TEXT,
                FavoritesMatch.AWAY_SUBS to TEXT,
                FavoritesMatch.STR_LEAGUE to TEXT)

        db.createTable(FavoritesTeam.TABLE_NAME, true,
                FavoritesTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,

                FavoritesTeam.ID_TEAM to INTEGER,
                FavoritesTeam.ID_LEAGUE to INTEGER,
                FavoritesTeam.LEAGUE to TEXT,
                FavoritesTeam.TEAM_NAME to TEXT,
                FavoritesTeam.TEAM_FORMED to TEXT,
                FavoritesTeam.TEAM_STADIUM to TEXT,
                FavoritesTeam.TEAM_DESCRIPTION to TEXT,
                FavoritesTeam.TEAM_BADGE to TEXT)


    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.dropTable(FavoritesMatch.TABLE_NAME, true)

    }


}