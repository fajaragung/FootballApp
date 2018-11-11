package com.robotsoftwarestudio.footballapp.models.repository

import com.robotsoftwarestudio.footballapp.rest.ApiRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ApiRepositoryPresenterTest {

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var apiRepositoryPresenter: ApiRepositoryPresenter

    @Before
    fun setUP() {
        MockitoAnnotations.initMocks(this)
        apiRepositoryPresenter = ApiRepositoryPresenter(apiRepository)
    }

    @Test
    fun eventsNextLeague() {
        apiRepositoryPresenter.eventsNextLeague("4328")
        verify(apiRepository).eventsNextLeague("4328")
    }

    @Test
    fun eventsPastLeague() {
        apiRepositoryPresenter.eventsPastLeague("4328")
        verify(apiRepository).eventsPastLeague("4328")
    }

    @Test
    fun searchAllTeams() {
        apiRepositoryPresenter.searchAllTeams("English Premier League")
        verify(apiRepository).searchAllTeams("English Premier League")
    }

    @Test
    fun searchPlayers() {
        apiRepositoryPresenter.searchPlayers("Arsenal")
        verify(apiRepository).searchPlayers("Arsenal")
    }

    @Test
    fun searchEvents() {
        apiRepositoryPresenter.searchEvents("Arsenal")
        verify(apiRepository).searchEvents("Arsenal")
    }

    @Test
    fun searchTeams() {

        apiRepositoryPresenter.searchTeams("Arsenal")
        verify(apiRepository).searchTeams("Arsenal")
    }

}