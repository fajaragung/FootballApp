package com.robotsoftwarestudio.footballapp.models.player

interface PlayersContract {

    interface View {

        fun showLoading()
        fun hideLoading()
        fun playersTeam(listPlayers: List<Players>)
        fun onFailure()

    }

    interface Presenter {

        fun getPlayersTeam(query: String?)
        fun onDestroyPresenter()

    }

}