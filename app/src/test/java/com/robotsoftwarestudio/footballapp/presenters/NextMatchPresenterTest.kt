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
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {

    @Mock
    private lateinit var mView: EventsContract.View

    @Mock
    private lateinit var apiRepositoryPresenter: ApiRepositoryPresenter

    private lateinit var mPresenter: NextMatchPresenter
    val mEvents = mutableListOf<Events>()


    @Before
    fun seUP() {
        MockitoAnnotations.initMocks(this)
        val schedulerTest = TestScheduler()
        val eventsResponse = EventsResponse(mEvents)
        val flowable = Flowable.just(eventsResponse)
        mPresenter = NextMatchPresenter(mView, apiRepositoryPresenter, schedulerTest)
        Mockito.`when`(apiRepositoryPresenter.eventsNextLeague("4328")).thenReturn(flowable)

    }

    @Test
    fun eventsNextLeague() {
        mPresenter.getEventsLeague("4328")
        Mockito.verify(mView).showLoading()
        Mockito.verify(mView).eventsLeague(mEvents)
        Mockito.verify(mView).hideLoading()
    }
}