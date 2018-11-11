package com.robotsoftwarestudio.footballapp.models.event

import com.google.gson.annotations.SerializedName

data class EventsResponse (
        @SerializedName("events")
        val events: List<Events>
)