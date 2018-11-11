package com.robotsoftwarestudio.footballapp.models.repository

import com.robotsoftwarestudio.footballapp.models.event.EventsResponse
import com.robotsoftwarestudio.footballapp.models.player.PlayersResponse
import com.robotsoftwarestudio.footballapp.models.searchmatch.SearchMatchesResponse
import com.robotsoftwarestudio.footballapp.models.searchteam.SearchTeamsResponse
import com.robotsoftwarestudio.footballapp.models.team.AllTeamsResponse
import com.robotsoftwarestudio.footballapp.rest.ApiRepository
import io.reactivex.Flowable

class ApiRepositoryPresenter(private val apiRepository: ApiRepository): ApiRepositoryContract {

    override fun eventsNextLeague(id: String?): Flowable<EventsResponse> =
            apiRepository.eventsNextLeague(id)

    override fun eventsPastLeague(id: String?): Flowable<EventsResponse> =
            apiRepository.eventsPastLeague(id)

    override fun searchAllTeams(query: String?): Flowable<AllTeamsResponse> =
            apiRepository.searchAllTeams(query)

    override fun searchPlayers(query: String?): Flowable<PlayersResponse> =
            apiRepository.searchPlayers(query)

    override fun searchEvents(query: String?): Flowable<SearchMatchesResponse> =
            apiRepository.searchEvents(query)

    override fun searchTeams(query: String?): Flowable<SearchTeamsResponse> =
            apiRepository.searchTeams(query)

}