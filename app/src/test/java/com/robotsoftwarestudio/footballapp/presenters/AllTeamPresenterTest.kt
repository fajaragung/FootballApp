package com.robotsoftwarestudio.footballapp.presenters

import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.models.team.AllTeamsContract
import com.robotsoftwarestudio.footballapp.models.team.AllTeamsResponse
import com.robotsoftwarestudio.footballapp.models.team.Teams
import com.robotsoftwarestudio.footballapp.utils.TestScheduler
import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AllTeamPresenterTest {

    @Mock
    private lateinit var mView: AllTeamsContract.View

    @Mock
    private lateinit var apiRepositoryPresenter: ApiRepositoryPresenter

    private lateinit var mPresenter: AllTeamPresenter
    val mTeams = mutableListOf<Teams>()


    @Before
    fun seUP() {
        MockitoAnnotations.initMocks(this)
        val schedulerTest = TestScheduler()
        val teamsResponse = AllTeamsResponse(mTeams)
        val flowable = Flowable.just(teamsResponse)
        mPresenter = AllTeamPresenter(mView, apiRepositoryPresenter, schedulerTest)
        Mockito.`when`(apiRepositoryPresenter.searchAllTeams("English Premier League")).thenReturn(flowable)

    }

    @Test
    fun searchAllTeams() {
        mPresenter.getAllTeams("English Premier League")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).allTeams(mTeams)
        Mockito.verify(mView).hideLoading()
    }

}