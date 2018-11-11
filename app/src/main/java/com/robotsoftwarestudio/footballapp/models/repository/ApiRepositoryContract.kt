package com.robotsoftwarestudio.footballapp.models.repository

import com.robotsoftwarestudio.footballapp.models.event.EventsResponse
import com.robotsoftwarestudio.footballapp.models.player.PlayersResponse
import com.robotsoftwarestudio.footballapp.models.searchmatch.SearchMatchesResponse
import com.robotsoftwarestudio.footballapp.models.searchteam.SearchTeamsResponse
import com.robotsoftwarestudio.footballapp.models.team.AllTeamsResponse
import io.reactivex.Flowable

interface ApiRepositoryContract {

    fun eventsNextLeague(id: String?): Flowable<EventsResponse>

    fun eventsPastLeague(id: String?): Flowable<EventsResponse>

    fun searchAllTeams(query: String?): Flowable<AllTeamsResponse>

    fun searchPlayers(query: String?): Flowable<PlayersResponse>

    fun searchEvents(query: String?): Flowable<SearchMatchesResponse>

    fun searchTeams(query: String?): Flowable<SearchTeamsResponse>

}