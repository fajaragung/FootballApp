package com.robotsoftwarestudio.footballapp.presenters

import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.models.searchteam.SearchTeamsContract
import com.robotsoftwarestudio.footballapp.models.searchteam.SearchTeamsResponse
import com.robotsoftwarestudio.footballapp.utils.SchedulerContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class SearchTeamPresenter(private val view: SearchTeamsContract.View, private val apiRepositoryPresenter: ApiRepositoryPresenter,
                          private val scheduler: SchedulerContract): SearchTeamsContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getTeamsLeague(query: String?) {

        view.showLoading()

        //control activity fetching data
        compositeDisposable.add(apiRepositoryPresenter.searchTeams(query)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object: ResourceSubscriber<SearchTeamsResponse>() {
                    override fun onComplete() { view.hideLoading() }

                    override fun onNext(t: SearchTeamsResponse) { view.teamsLeague(t.teams) }

                    override fun onError(t: Throwable?) {
                        view.teamsLeague(Collections.emptyList())
                        view.hideLoading()
                        view.onFailure()
                    }

                }))
    }

    override fun onDestroyPresenter() { compositeDisposable.dispose() }

}