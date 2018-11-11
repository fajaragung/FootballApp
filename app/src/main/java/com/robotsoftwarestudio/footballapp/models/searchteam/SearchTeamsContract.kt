package com.robotsoftwarestudio.footballapp.models.searchteam

import com.robotsoftwarestudio.footballapp.models.team.Teams

interface SearchTeamsContract {

    interface View {

        fun showLoading()
        fun hideLoading()
        fun teamsLeague(listTeams: List<Teams>)
        fun onFailure()

    }

    interface Presenter {

        fun getTeamsLeague(query: String?)
        fun onDestroyPresenter()

    }

}