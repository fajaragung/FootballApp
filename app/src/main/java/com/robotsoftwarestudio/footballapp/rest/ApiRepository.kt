package com.robotsoftwarestudio.footballapp.rest

import com.robotsoftwarestudio.footballapp.models.event.EventsResponse
import com.robotsoftwarestudio.footballapp.models.player.PlayersResponse
import com.robotsoftwarestudio.footballapp.models.searchmatch.SearchMatchesResponse
import com.robotsoftwarestudio.footballapp.models.searchteam.SearchTeamsResponse
import com.robotsoftwarestudio.footballapp.models.team.AllTeamsResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRepository {

    @GET("eventsnextleague.php")
    fun eventsNextLeague(@Query("id") id: String?): Flowable<EventsResponse>

    @GET("eventspastleague.php")
    fun eventsPastLeague(@Query("id") id: String?): Flowable<EventsResponse>

    @GET("search_all_teams.php")
    fun searchAllTeams(@Query("l") query: String?): Flowable<AllTeamsResponse>

    @GET("searchplayers.php")
    fun searchPlayers(@Query("t") query: String?): Flowable<PlayersResponse>

    @GET("searchevents.php")
    fun searchEvents(@Query("e") query: String?): Flowable<SearchMatchesResponse>

    @GET("searchteams.php")
    fun searchTeams(@Query("t") query: String?): Flowable<SearchTeamsResponse>


}