package com.robotsoftwarestudio.footballapp.models.player

import com.google.gson.annotations.SerializedName

data class PlayersResponse(
        @SerializedName("player")
        val player: List<Players>
)