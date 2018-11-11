package com.robotsoftwarestudio.footballapp.views.fragmenttab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.extensions.gone
import com.robotsoftwarestudio.footballapp.extensions.visible
import com.robotsoftwarestudio.footballapp.models.player.Players
import com.robotsoftwarestudio.footballapp.models.player.PlayersContract
import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.presenters.PlayerTeamPresenter
import com.robotsoftwarestudio.footballapp.rest.ApiService
import com.robotsoftwarestudio.footballapp.utils.SchedulerPresenter
import com.robotsoftwarestudio.footballapp.views.adapter.PlayerAdapter
import com.robotsoftwarestudio.footballapp.views.main.DetailTeamActivity
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.fragment_nav_main.view.*

class TabPlayersFragment: Fragment(), PlayersContract.View {

    private lateinit var v: View
    private val mListPlayers = mutableListOf<Players>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_nav_main, container, false)

        RxJavaPlugins.setErrorHandler { t:Throwable? -> }

        //gone tab layout, spinner and view pager then show recycler view
        v.main_tab_layout.gone()
        v.spinner_league.gone()
        v.view_page_main.gone()
        v.refresh_main.visible()

        v.refresh_main.setOnRefreshListener { doRequest() }

        return v
    }

    override fun onResume() {
        super.onResume()

        doRequest()

    }

    /**
     * this method request data of api on server
     */
    private fun doRequest() {
        val apiService = ApiService.create()
        val apiRepositoryPresenter = ApiRepositoryPresenter(apiService)
        val schedulerPresenter = SchedulerPresenter()
        val presenter = PlayerTeamPresenter(this, apiRepositoryPresenter, schedulerPresenter)
        presenter.getPlayersTeam(DetailTeamActivity.mTeams.teamName)
    }

    override fun showLoading() {
        val context = activity as DetailTeamActivity
        context.detail_team_progress.visible()
        v.refresh_main.gone()
    }

    override fun hideLoading() {
        val context = activity as DetailTeamActivity
        context.detail_team_progress.gone()
        v.refresh_main.visible()

        v.refresh_main.isRefreshing = false
    }

    override fun playersTeam(listPlayers: List<Players>) {
        mListPlayers.clear()
        mListPlayers.addAll(listPlayers)

        inflateAdapter()
    }

    override fun onFailure() {
        Toast.makeText(activity, getString(R.string.No_internet_connection), Toast.LENGTH_SHORT).show()
    }

    /**
     * this method inflate adapter to fragment
     */
    private fun inflateAdapter() {

        v.recycler_main.layoutManager = LinearLayoutManager(activity)
        v.recycler_main.adapter = PlayerAdapter(activity!!.applicationContext, mListPlayers)
        v.recycler_main.adapter.notifyDataSetChanged()

    }
}