package com.robotsoftwarestudio.footballapp.views.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.robotsoftwarestudio.footballapp.extensions.gone
import com.robotsoftwarestudio.footballapp.extensions.visible
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.models.team.AllTeamsContract
import com.robotsoftwarestudio.footballapp.models.team.Teams
import com.robotsoftwarestudio.footballapp.presenters.AllTeamPresenter
import com.robotsoftwarestudio.footballapp.rest.ApiService
import com.robotsoftwarestudio.footballapp.utils.SchedulerPresenter
import com.robotsoftwarestudio.footballapp.views.adapter.TeamAdapter
import com.robotsoftwarestudio.footballapp.views.main.AboutAppActivity
import com.robotsoftwarestudio.footballapp.views.main.MainActivity
import com.robotsoftwarestudio.footballapp.views.main.SearchTeamsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_nav_main.view.*
import org.jetbrains.anko.startActivity

class MainTeamsFragment: Fragment(), AllTeamsContract.View {

    private lateinit var v: View
    private lateinit var mPresenter: AllTeamPresenter
    private val mListTeams = mutableListOf<Teams>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_nav_main, container, false)

        //gone the tab layout and the view page on layout
        v.main_tab_layout.gone()
        v.view_page_main.gone()
        v.refresh_main.visible()

        setHasOptionsMenu(true)

        v.refresh_main.setOnRefreshListener { doRequest() }

        isSelectedLeague()

        return v
    }

    override fun onResume() {
        super.onResume()

        doRequest()

    }

    /**
     * this method for request data of api on server
     */
    private fun doRequest() {

        val apiService = ApiService.create()
        val apiRepository = ApiRepositoryPresenter(apiService)
        val schedulerPresenter = SchedulerPresenter()
        mPresenter = AllTeamPresenter(this, apiRepository, schedulerPresenter)
        mPresenter.getAllTeams("English Premier League")

    }

    override fun showLoading() {
        val context = activity as MainActivity
        context.main_progress.visible()
        v.refresh_main.gone()
    }

    override fun hideLoading() {
        val context = activity as MainActivity
        context.main_progress.gone()
        v.refresh_main.visible()

        v.refresh_main.isRefreshing = false

    }

    override fun allTeams(listTeams: List<Teams>) {
        mListTeams.clear()
        mListTeams.addAll(listTeams)

        inflateAdapter()
    }

    override fun onFailure() {
        Toast.makeText(activity, resources.getString(R.string.No_internet_connection), Toast.LENGTH_SHORT).show()
    }

    /**
     * this method for inflate adapter
     */
    private fun inflateAdapter() {

        v.recycler_main.layoutManager = LinearLayoutManager(activity)
        v.recycler_main.adapter = TeamAdapter(activity!!.applicationContext, mListTeams)
        v.recycler_main.adapter.notifyDataSetChanged()

    }


    /**
     * this method inflate layout menu to toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.toolbar_search, menu)

        //initiallize
        val search = menu?.findItem(R.id.action_search)?.actionView as SearchView

        search.queryHint = getString(R.string.Search_Team)
        search.isSubmitButtonEnabled = true

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != "") {
                    context?.startActivity<SearchTeamsActivity>("SEARCH" to query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {

            R.id.action_about_app -> context?.startActivity<AboutAppActivity>()

        }

        return super.onOptionsItemSelected(item)
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

                    "English Premier League" -> mPresenter.getAllTeams("English Premier League")

                    "Spanish La Liga" -> mPresenter.getAllTeams("Spanish La Liga")

                    "German Bundesliga" -> mPresenter.getAllTeams("German Bundesliga")

                    "Italian Serie A" -> mPresenter.getAllTeams("Italian Serie A")

                    "French League 1" -> mPresenter.getAllTeams("French League 1")

                    else -> mPresenter.getAllTeams("English Premier League")
                }
            }

        }

    }

}