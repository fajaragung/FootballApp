package com.robotsoftwarestudio.footballapp.views.fragmenttab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.robotsoftwarestudio.footballapp.extensions.gone
import com.robotsoftwarestudio.footballapp.extensions.visible
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.models.event.Events
import com.robotsoftwarestudio.footballapp.models.event.EventsContract
import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.presenters.NextMatchPresenter
import com.robotsoftwarestudio.footballapp.rest.ApiService
import com.robotsoftwarestudio.footballapp.utils.SchedulerPresenter
import com.robotsoftwarestudio.footballapp.views.adapter.EventAdapter
import com.robotsoftwarestudio.footballapp.views.main.MainActivity
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_nav_main.view.*

class TabNextMatchFragment : Fragment(), EventsContract.View {

    private lateinit var v: View
    private lateinit var mPresenter: NextMatchPresenter
    private val mListEvents = mutableListOf<Events>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_nav_main, container, false)

        //gone tab layout, spinner and view pager then show recycler view
        v.main_tab_layout.gone()
        v.view_page_main.gone()
        v.refresh_main.visible()

        RxJavaPlugins.setErrorHandler { t:Throwable? -> }

        v.refresh_main.setOnRefreshListener { doRequest() }

        return v
    }

    override fun onResume() {
        super.onResume()

        doRequest()
        isSelectedLeague()

    }

    /**
     * this method do action request data api on server
     */
    private fun doRequest() {
        val apiService = ApiService.create()
        val apiRepositoryPresenter = ApiRepositoryPresenter(apiService)
        val schedulerPresenter = SchedulerPresenter()
        mPresenter = NextMatchPresenter(this, apiRepositoryPresenter, schedulerPresenter)
        mPresenter.getEventsLeague("4328")
    }

    override fun showLoading() {
        //set context activity as main activity
        val context = activity as MainActivity
        context.main_progress.visible()
        v.recycler_main.gone()
    }

    override fun hideLoading() {
        //set context activity as main activity
        val context = activity as MainActivity
        context.main_progress.gone()
        v.refresh_main.isRefreshing = false
        v.recycler_main.visible()
    }

    override fun eventsLeague(listEvents: List<Events>) {
        mListEvents.clear()
        mListEvents.addAll(listEvents)

        inflateAdapter()
    }

    override fun onFailure() {
        Toast.makeText(activity, getString(R.string.No_internet_connection), Toast.LENGTH_SHORT).show()
    }

    /**
     * this method inflate adapter to activity
     */
    private fun inflateAdapter() {
        v.recycler_main.layoutManager = LinearLayoutManager(activity)
        v.recycler_main.adapter = EventAdapter(activity!!.applicationContext, mListEvents)
        v.recycler_main.adapter.notifyDataSetChanged()
    }

    /**
     * this method give text's on spinner and take user selected spinner
     */
    private fun isSelectedLeague() {

        //declare spinner text and setup behaviour
        val spinnerLeague = resources.getStringArray(R.array.list_league)
        val adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, spinnerLeague)

        //set adapter
        v.spinner_league.adapter = adapter
        //on user selected
        v.spinner_league.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val leagueSelected = v.spinner_league.selectedItem.toString()

                when (leagueSelected) {

                    "English Premier League" -> mPresenter.getEventsLeague("4328")

                    "Spanish La Liga" -> mPresenter.getEventsLeague("4335")

                    "German Bundesliga" -> mPresenter.getEventsLeague("4331")

                    "Italian Serie A" -> mPresenter.getEventsLeague("4332")

                    "French League 1" -> mPresenter.getEventsLeague("4334")

                    else -> mPresenter.getEventsLeague("4328")
                }
            }

        }

    }
}