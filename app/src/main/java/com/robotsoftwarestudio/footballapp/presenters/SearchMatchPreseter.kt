package com.robotsoftwarestudio.footballapp.presenters

import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.models.searchmatch.SearchMatchesContract
import com.robotsoftwarestudio.footballapp.models.searchmatch.SearchMatchesResponse
import com.robotsoftwarestudio.footballapp.utils.SchedulerContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class SearchMatchPreseter(private val view: SearchMatchesContract.View, private val apiRepositoryPresenter: ApiRepositoryPresenter,
                          private val scheduler: SchedulerContract): SearchMatchesContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getEventsLeague(query: String?) {

        view.showLoading()

        //control activity fetching data
        compositeDisposable.add(apiRepositoryPresenter.searchEvents(query)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object: ResourceSubscriber<SearchMatchesResponse>() {
                    override fun onComplete() { view.hideLoading() }

                    override fun onNext(t: SearchMatchesResponse) { view.eventsLeague(t.event) }

                    override fun onError(t: Throwable?) {
                        view.eventsLeague(Collections.emptyList())
                        view.hideLoading()
                        view.onFailure()
                    }

                }))
    }

    override fun onDestroyPresenter() { compositeDisposable.dispose() }

}