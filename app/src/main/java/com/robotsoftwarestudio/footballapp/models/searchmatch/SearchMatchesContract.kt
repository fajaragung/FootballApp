package com.robotsoftwarestudio.footballapp.models.searchmatch

import com.robotsoftwarestudio.footballapp.models.event.Events

interface SearchMatchesContract {

    interface View {

        fun showLoading()
        fun hideLoading()
        fun eventsLeague(listEvents: List<Events>)
        fun onFailure()

    }

    interface Presenter {

        fun getEventsLeague(query: String?)
        fun onDestroyPresenter()

    }

}