package com.robotsoftwarestudio.footballapp.models.team

import com.google.gson.annotations.SerializedName

data class AllTeamsResponse(

        @SerializedName("teams")
        val teams: List<Teams>

)