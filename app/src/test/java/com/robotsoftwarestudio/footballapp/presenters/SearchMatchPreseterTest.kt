package com.robotsoftwarestudio.footballapp.presenters

import com.robotsoftwarestudio.footballapp.models.event.Events
import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.models.searchmatch.SearchMatchesContract
import com.robotsoftwarestudio.footballapp.models.searchmatch.SearchMatchesResponse
import com.robotsoftwarestudio.footballapp.utils.TestScheduler
import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchMatchPreseterTest {

    @Mock
    private lateinit var mView: SearchMatchesContract.View

    @Mock
    private lateinit var apiRepositoryPresenter: ApiRepositoryPresenter

    private lateinit var mPresenter: SearchMatchPreseter
    val mMatch = mutableListOf<Events>()


    @Before
    fun seUP() {
        MockitoAnnotations.initMocks(this)
        val schedulerTest = TestScheduler()
        val matchResponse = SearchMatchesResponse(mMatch)
        val flowable = Flowable.just(matchResponse)
        mPresenter = SearchMatchPreseter(mView, apiRepositoryPresenter, schedulerTest)
        Mockito.`when`(apiRepositoryPresenter.searchEvents("Arsenal VS Chelsea")).thenReturn(flowable)

    }

    @Test
    fun eventsLeague() {
        mPresenter.getEventsLeague("Arsenal VS Chelsea")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).eventsLeague(mMatch)
        Mockito.verify(mView).hideLoading()
    }

}