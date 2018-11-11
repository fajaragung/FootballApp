package com.robotsoftwarestudio.footballapp.presenters

import com.robotsoftwarestudio.footballapp.models.player.PlayersContract
import com.robotsoftwarestudio.footballapp.models.player.PlayersResponse
import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.utils.SchedulerContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class PlayerTeamPresenter(private val view: PlayersContract.View, private val apiRepositoryPresenter: ApiRepositoryPresenter,
                       private val scheduler: SchedulerContract): PlayersContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getPlayersTeam(query: String?) {

        view.showLoading()

        //control activity fetching data
        compositeDisposable.add(apiRepositoryPresenter.searchPlayers(query)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object: ResourceSubscriber<PlayersResponse>() {
                    override fun onComplete() { view.hideLoading() }

                    override fun onNext(t: PlayersResponse) { view.playersTeam(t.player) }

                    override fun onError(t: Throwable?) {
                        view.playersTeam(Collections.emptyList())
                        view.hideLoading()
                        view.onFailure()
                    }

                }))
    }

    override fun onDestroyPresenter() { compositeDisposable.dispose() }

}