package com.robotsoftwarestudio.footballapp.models.favorite

import java.io.Serializable

data class FavoritesTeam(

        val id: Long?,

        //Team
        val idTeam: Int?,
        val idLeague: Int?,
        val league: String?,
        val teamName: String?,
        val teamFormed: String?,
        val teamStadium: String?,
        val teamDescription: String?,
        val teamBadge: String?

): Serializable {

    companion object {

        const val TABLE_NAME = "FAVORITES_TEAMS"
        const val ID = "ID_"

        //Team
        const val ID_TEAM = "ID_TEAMS"
        const val ID_LEAGUE = "ID_LEAGUE"
        const val LEAGUE = "LEAGUE_TEAM"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_FORMED = "TEAM_FORMED"
        const val TEAM_STADIUM = "TEAM_STADIUM"
        const val TEAM_DESCRIPTION = "TEAM_DESCRIPTION"
        const val TEAM_BADGE = "TEAM_BADGE"

    }

}