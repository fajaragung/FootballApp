package com.robotsoftwarestudio.footballapp.presenters

import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.models.team.AllTeamsContract
import com.robotsoftwarestudio.footballapp.models.team.AllTeamsResponse
import com.robotsoftwarestudio.footballapp.utils.SchedulerContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class AllTeamPresenter(private val view: AllTeamsContract.View, private val apiRepositoryPresenter: ApiRepositoryPresenter,
                       private val scheduler: SchedulerContract): AllTeamsContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getAllTeams(query: String?) {

        view.showLoading()

        //control activity fetching data
        compositeDisposable.add(apiRepositoryPresenter.searchAllTeams(query)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object: ResourceSubscriber<AllTeamsResponse>() {
                    override fun onComplete() { view.hideLoading() }

                    override fun onNext(t: AllTeamsResponse) { view.allTeams(t.teams) }

                    override fun onError(t: Throwable?) {
                        view.allTeams(Collections.emptyList())
                        view.hideLoading()
                        view.onFailure()
                    }

                }))
    }

    override fun onDestroyPresenter() { compositeDisposable.dispose() }

}