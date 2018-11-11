package com.robotsoftwarestudio.footballapp.models.favorite

import java.io.Serializable

data class FavoritesMatch(

        val id: Long?,

        //Match
        val idEventMatch: Int?,
        val dateEvent: String?,
        val dateTime: String?,
        val idHomeTeam: Int?,
        val idAwayTeam: Int?,
        val homeScore: Int?,
        val awayScore: Int?,
        val homeTeam: String?,
        val awayTeam: String?,
        val homeGoal: String?,
        val awayGoal: String?,
        val homeShots: Int?,
        val awayShots: Int?,
        val homeKeeper: String?,
        val awayKeeper: String?,
        val homeDefense: String?,
        val awayDefense: String?,
        val homeMid: String?,
        val awayMid: String?,
        val homeForward: String?,
        val awayForward: String?,
        val homeSubs: String?,
        val awaySubs: String?,
        val strLeague: String?

): Serializable {

    companion object {

        const val TABLE_NAME = "FAVORITES_MATCHES"
        const val ID = "ID_"

        //Match
        const val ID_EVENT_MATCH = "ID_EVENT_MATCHES"
        const val DATE_EVENT = "DATE_EVENT"
        const val DATE_TIME = "DATE_TIME"
        const val ID_HOME_TEAM = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM = "ID_AWAY_TEAM"
        const val HOME_SCORE = "HOME_SCORE"
        const val AWAY_SCORE = "AWAY_SCORE"
        const val HOME_TEAM = "HOME_TEAM"
        const val AWAY_TEAM = "AWAY_TEAM"
        const val HOME_GOAL = "HOME_GOAL"
        const val AWAY_GOAL = "AWAY_GOAL"
        const val HOME_SHOTS = "HOME_SHOTS"
        const val AWAY_SHOTS = "AWAY_SHOTS"
        const val HOME_KEEPER = "HOME_KEEPER"
        const val AWAY_KEEPER = "AWAY_KEEPER"
        const val HOME_DEFENSE = "HOME_DEFENSE"
        const val AWAY_DEFENSE = "AWAY_DEFENSE"
        const val HOME_MID = "HOME_MID"
        const val AWAY_MID = "AWAY_MID"
        const val HOME_FORWARD = "HOME_FORWARD"
        const val AWAY_FORWARD = "AWAY_FORWARD"
        const val HOME_SUBS = "HOME_SUBS"
        const val AWAY_SUBS = "AWAY_SUBS"
        const val STR_LEAGUE = "LEAGUE"

    }

}