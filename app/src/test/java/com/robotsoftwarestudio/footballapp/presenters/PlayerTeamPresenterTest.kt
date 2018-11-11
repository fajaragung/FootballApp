package com.robotsoftwarestudio.footballapp.presenters

import com.robotsoftwarestudio.footballapp.models.player.Players
import com.robotsoftwarestudio.footballapp.models.player.PlayersContract
import com.robotsoftwarestudio.footballapp.models.player.PlayersResponse
import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.utils.TestScheduler
import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayerTeamPresenterTest {

    @Mock
    private lateinit var mView: PlayersContract.View

    @Mock
    private lateinit var apiRepositoryPresenter: ApiRepositoryPresenter

    private lateinit var mPresenter: PlayerTeamPresenter
    val mPlayer = mutableListOf<Players>()


    @Before
    fun seUP() {
        MockitoAnnotations.initMocks(this)
        val schedulerTest = TestScheduler()
        val playersResponse = PlayersResponse(mPlayer)
        val flowable = Flowable.just(playersResponse)
        mPresenter = PlayerTeamPresenter(mView, apiRepositoryPresenter, schedulerTest)
        Mockito.`when`(apiRepositoryPresenter.searchPlayers("Arsenal")).thenReturn(flowable)

    }

    @Test
    fun searchTeams() {
        mPresenter.getPlayersTeam("Arsenal")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).playersTeam(mPlayer)
        Mockito.verify(mView).hideLoading()
    }

}