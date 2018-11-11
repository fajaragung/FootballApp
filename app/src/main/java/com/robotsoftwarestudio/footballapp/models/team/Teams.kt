package com.robotsoftwarestudio.footballapp.models.team

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Teams (

        @SerializedName("idTeam")
        val idTeam: Int?,

        @SerializedName("idLeague")
        val idLeague: Int?,

        @SerializedName("strLeague")
        val strLeague: String?,

        @SerializedName("strTeam")
        val teamName: String?,

        @SerializedName("intFormedYear")
        val teamFormed: Int?,

        @SerializedName("strManager")
        val teamManager: String?,

        @SerializedName("strStadium")
        val teamStadium: String?,

        @SerializedName("strDescriptionEN")
        val teamDescription: String?,

        @SerializedName("strTeamBadge")
        val teamBadge: String?

): Serializable