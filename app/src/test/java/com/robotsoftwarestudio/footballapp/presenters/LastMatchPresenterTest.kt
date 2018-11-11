package com.robotsoftwarestudio.footballapp.presenters

import com.robotsoftwarestudio.footballapp.models.event.Events
import com.robotsoftwarestudio.footballapp.models.event.EventsContract
import com.robotsoftwarestudio.footballapp.models.event.EventsResponse
import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.utils.TestScheduler
import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class LastMatchPresenterTest {

    @Mock
    private lateinit var mView: EventsContract.View

    @Mock
    private lateinit var apiRepositoryPresenter: ApiRepositoryPresenter

    private lateinit var mPresenter: LastMatchPresenter
    val mEvents = mutableListOf<Events>()


    @Before
    fun seUP() {
        MockitoAnnotations.initMocks(this)
        val schedulerTest = TestScheduler()
        val eventsResponse = EventsResponse(mEvents)
        val flowable = Flowable.just(eventsResponse)
        mPresenter = LastMatchPresenter(mView, apiRepositoryPresenter, schedulerTest)
        `when`(apiRepositoryPresenter.eventsPastLeague("4328")).thenReturn(flowable)

    }

    @Test
    fun eventsPastLeague() {
        mPresenter.getEventsLeague("4328")
        verify(mView).showLoading()
        verify(mView).eventsLeague(mEvents)
        verify(mView).hideLoading()
    }
}