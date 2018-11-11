package com.robotsoftwarestudio.footballapp.models.searchmatch

import com.google.gson.annotations.SerializedName
import com.robotsoftwarestudio.footballapp.models.event.Events

data class SearchMatchesResponse(

        @SerializedName("event")
        val event: List<Events>

)