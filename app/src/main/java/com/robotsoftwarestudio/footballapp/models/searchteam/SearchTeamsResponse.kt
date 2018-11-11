package com.robotsoftwarestudio.footballapp.models.searchteam

import com.google.gson.annotations.SerializedName
import com.robotsoftwarestudio.footballapp.models.team.Teams

data class SearchTeamsResponse(

        @SerializedName("teams")
        val teams: List<Teams>
)