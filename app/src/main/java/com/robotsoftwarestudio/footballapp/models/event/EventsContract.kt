package com.robotsoftwarestudio.footballapp.models.event

interface EventsContract {

    interface View {

        fun showLoading()
        fun hideLoading()
        fun eventsLeague(listEvents: List<Events>)
        fun onFailure()

    }

    interface Presenter {

        fun getEventsLeague(id: String?)
        fun onDestroyPresenter()

    }

}