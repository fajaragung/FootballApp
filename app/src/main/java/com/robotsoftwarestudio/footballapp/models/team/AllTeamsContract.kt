package com.robotsoftwarestudio.footballapp.models.team

interface AllTeamsContract {

    interface View {

        fun showLoading()
        fun hideLoading()
        fun allTeams(listTeams: List<Teams>)
        fun onFailure()

    }

    interface Presenter {

        fun getAllTeams(query: String?)
        fun onDestroyPresenter()

    }
}