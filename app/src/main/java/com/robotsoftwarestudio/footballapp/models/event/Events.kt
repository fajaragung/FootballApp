package com.robotsoftwarestudio.footballapp.models.event

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Events(

        @SerializedName("idEvent")
        val idEvent: Int?,

        @SerializedName("idLeague")
        val idLeague: Int?,

        @SerializedName("strLeague")
        val strLeague: String?,

        @SerializedName("strHomeTeam")
        val homeTeam: String?,

        @SerializedName("strAwayTeam")
        val awayTeam: String?,

        @SerializedName("intHomeScore")
        val homeScore: Int?,

        @SerializedName("intAwayScore")
        val awayScore: Int?,

        @SerializedName("strHomeGoalDetails")
        val homeGoal: String?,

        @SerializedName("intHomeShots")
        val homeShots: Int?,

        @SerializedName("intAwayShots")
        val awayShots: Int?,

        @SerializedName("strHomeLineupGoalkeeper")
        val homeKeeper: String?,

        @SerializedName("strHomeLineupDefense")
        val homeDefense: String?,

        @SerializedName("strHomeLineupMidfield")
        val homeMid: String?,

        @SerializedName("strHomeLineupForward")
        val homeForward: String?,

        @SerializedName("strHomeLineupSubstitutes")
        val homeSubs: String?,

        @SerializedName("strAwayGoalDetails")
        val awayGoal: String?,

        @SerializedName("strAwayLineupGoalkeeper")
        val awayKeeper: String?,

        @SerializedName("strAwayLineupDefense")
        val awayDefense: String?,

        @SerializedName("strAwayLineupMidfield")
        val awayMid: String?,

        @SerializedName("strAwayLineupForward")
        val awayForward: String?,

        @SerializedName("strAwayLineupSubstitutes")
        val awaySubs: String?,

        @SerializedName("dateEvent")
        val dateEvent: String?,

        @SerializedName("strTime")
        val dateTime: String?,

        @SerializedName("idHomeTeam")
        val idHomeTeam: Int?,

        @SerializedName("idAwayTeam")
        val idAwayTeam: Int?


): Serializable