package com.robotsoftwarestudio.footballapp.presenters

import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.models.searchteam.SearchTeamsContract
import com.robotsoftwarestudio.footballapp.models.searchteam.SearchTeamsResponse
import com.robotsoftwarestudio.footballapp.models.team.Teams
import com.robotsoftwarestudio.footballapp.utils.TestScheduler
import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchTeamPresenterTest {

    @Mock
    private lateinit var mView: SearchTeamsContract.View

    @Mock
    private lateinit var apiRepositoryPresenter: ApiRepositoryPresenter

    private lateinit var mPresenter: SearchTeamPresenter
    val mTeams = mutableListOf<Teams>()


    @Before
    fun seUP() {
        MockitoAnnotations.initMocks(this)
        val schedulerTest = TestScheduler()
        val teamResponse = SearchTeamsResponse(mTeams)
        val flowable = Flowable.just(teamResponse)
        mPresenter = SearchTeamPresenter(mView, apiRepositoryPresenter, schedulerTest)
        Mockito.`when`(apiRepositoryPresenter.searchTeams("Arsenal")).thenReturn(flowable)

    }

    @Test
    fun searchTeam() {
        mPresenter.getTeamsLeague("Arsenal")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).teamsLeague(mTeams)
        Mockito.verify(mView).hideLoading()
    }

}