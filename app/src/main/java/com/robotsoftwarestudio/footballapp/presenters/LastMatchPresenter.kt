package com.robotsoftwarestudio.footballapp.presenters

import com.robotsoftwarestudio.footballapp.models.event.EventsContract
import com.robotsoftwarestudio.footballapp.models.event.EventsResponse
import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.utils.SchedulerContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class LastMatchPresenter(private val view: EventsContract.View, private val apiRepositoryPresenter: ApiRepositoryPresenter,
                         private val scheduler: SchedulerContract): EventsContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getEventsLeague(id: String?) {

        view.showLoading()

        //control activity fetching data
        compositeDisposable.add(apiRepositoryPresenter.eventsPastLeague(id)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object: ResourceSubscriber<EventsResponse>() {
                    override fun onComplete() { view.hideLoading() }

                    override fun onNext(t: EventsResponse) { view.eventsLeague(t.events) }

                    override fun onError(t: Throwable?) {
                        view.eventsLeague(Collections.emptyList())
                        view.hideLoading()
                        view.onFailure()
                    }

                }))
    }

    override fun onDestroyPresenter() { compositeDisposable.dispose() }

}