package com.robotsoftwarestudio.footballapp.views.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.extensions.gone
import com.robotsoftwarestudio.footballapp.extensions.visible
import com.robotsoftwarestudio.footballapp.models.event.Events
import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.models.searchmatch.SearchMatchesContract
import com.robotsoftwarestudio.footballapp.presenters.SearchMatchPreseter
import com.robotsoftwarestudio.footballapp.rest.ApiService
import com.robotsoftwarestudio.footballapp.utils.SchedulerPresenter
import com.robotsoftwarestudio.footballapp.views.adapter.EventAdapter
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.support_toolbar.*

class SearchEventsActivity: AppCompatActivity(), SearchMatchesContract.View {

    private lateinit var searchQuery: String
    private lateinit var mPresenter: SearchMatchPreseter
    private val mListEvents = mutableListOf<Events>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        RxJavaPlugins.setErrorHandler { t:Throwable? -> }
        searchQuery = intent.getStringExtra("SEARCH")

        setSupportActionBar(support_toolbar)
        refresh_search.setOnRefreshListener { doRequest() }
    }

    override fun onResume() {
        super.onResume()

        doRequest()

    }

    /**
     * this method do request data of api on server
     */
    private fun doRequest() {
        val apiService = ApiService.create()
        val apiRepositoryPreseter = ApiRepositoryPresenter(apiService)
        val schedulerPresenter = SchedulerPresenter()
        mPresenter = SearchMatchPreseter(this, apiRepositoryPreseter, schedulerPresenter)
        mPresenter.getEventsLeague(searchQuery)
    }

    override fun showLoading() {
        search_progress.visible()
        refresh_search.gone()
    }

    override fun hideLoading() {
        search_progress.gone()
        refresh_search.visible()

        refresh_search.isRefreshing = false
    }

    override fun eventsLeague(listEvents: List<Events>) {
        mListEvents.clear()
        mListEvents.addAll(listEvents)

        inflateAdapter()
    }

    override fun onFailure() {
        Toast.makeText(this, getString(R.string.Events_not_Found), Toast.LENGTH_SHORT).show()
    }

    /**
     * this method inflate adapter on activity
     */
    private fun inflateAdapter() {

            recycler_search.layoutManager = LinearLayoutManager(this)
            recycler_search.adapter = EventAdapter(this, mListEvents)
            recycler_search.adapter.notifyDataSetChanged()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_search, menu)
        menu?.findItem(R.id.action_about_app)?.isVisible = false

        //initiallize
        val search = menu?.findItem(R.id.action_search)?.actionView as SearchView

        search.queryHint = getString(R.string.Search_Match)

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mPresenter.getEventsLeague(newText)
                return true
            }

        })

        return true
    }
}