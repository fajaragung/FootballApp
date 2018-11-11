package com.robotsoftwarestudio.footballapp.views.main

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.extensions.gone
import com.robotsoftwarestudio.footballapp.extensions.sqlite
import com.robotsoftwarestudio.footballapp.extensions.visible
import com.robotsoftwarestudio.footballapp.models.event.Events
import com.robotsoftwarestudio.footballapp.models.favorite.FavoritesMatch
import com.robotsoftwarestudio.footballapp.models.repository.ApiRepositoryPresenter
import com.robotsoftwarestudio.footballapp.models.team.AllTeamsContract
import com.robotsoftwarestudio.footballapp.models.team.Teams
import com.robotsoftwarestudio.footballapp.presenters.AllTeamPresenter
import com.robotsoftwarestudio.footballapp.rest.ApiService
import com.robotsoftwarestudio.footballapp.utils.SchedulerPresenter
import com.squareup.picasso.Picasso
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.support_toolbar.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.lang.reflect.InvocationTargetException
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchActivity: AppCompatActivity(), AllTeamsContract.View {

    private lateinit var events: Events
    private lateinit var mPresenter: AllTeamPresenter
    private var menuItem: Menu? = null
    private var isFavorite = false
    private val mListTeams = mutableListOf<Teams>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        RxJavaPlugins.setErrorHandler { t: Throwable -> }

        setSupportActionBar(support_toolbar)
        supportActionBar?.title = getString(R.string.Detail_Match)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        events = intent.extras.getSerializable("EVENTS") as Events

        refresh_detail_match.setOnRefreshListener { doRequest() }

        stateFavorite()

    }

    override fun onResume() {
        super.onResume()

        doRequest()

    }

    /**
     * this method do request data api on a server
     */
    private fun doRequest() {

        val service = ApiService.create()
        val apiRepositoryPresenter = ApiRepositoryPresenter(service)
        val schedulerPresenter = SchedulerPresenter()
        mPresenter = AllTeamPresenter(this, apiRepositoryPresenter, schedulerPresenter)
        mPresenter.getAllTeams(events.strLeague)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu_favorite, menu)
        menuItem = menu
        setFavorite()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.action_add_to_favorite -> {

                if(isFavorite) {
                    removeFavorite()
                } else {
                    addFavorite()
                }

                isFavorite = !isFavorite
                setFavorite()

                true

            }

            else -> super.onOptionsItemSelected(item)

        }

    }

    override fun showLoading() {

        progress_detail_match.visible()
        refresh_detail_match.gone()

    }

    override fun hideLoading() {

        progress_detail_match.gone()
        refresh_detail_match.visible()

    }

    override fun allTeams(listTeams: List<Teams>) {

        mListTeams.clear()
        mListTeams.addAll(listTeams)

        displayBadgeTeam()
        displayDetailMatchToUI()

    }

    override fun onFailure() {
        Toast.makeText(this, resources.getString(R.string.No_internet_connection), Toast.LENGTH_SHORT).show()
    }

    /**
     * this method display team badge
     */
    private fun displayBadgeTeam() {

        val id = mListTeams.map { it.idTeam }
        var teamBadge: String?

        for(i in id.indices) {

            if(events.idHomeTeam == id[i]) {

                val index = mListTeams[i]
                teamBadge = index.teamBadge
                Picasso.get().load(teamBadge).into(home_image)

            }

            if(events.idAwayTeam == id[i]) {

                val index = mListTeams[i]
                teamBadge = index.teamBadge
                Picasso.get().load(teamBadge).into(away_image)

            }

        }

    }

    /**
     * this method for display data to UI
     */
    private fun displayDetailMatchToUI() {

        val event = SimpleDateFormat("yyyy-MM-d").parse(events.dateEvent)
        val dateEvent = SimpleDateFormat("EEE, d MMM yyyy", Locale.US).format(event)
        date_match_tv.text = dateEvent

        val time = SimpleDateFormat("HH:mm:ssZ").parse(events.dateTime)
        val timeEvent = SimpleDateFormat("HH:mm", Locale.US).format(time)
        match_time_tv.text = timeEvent

        home_team_tv.text = events.homeTeam

        if(events.homeScore == null || events.awayScore == null) {

            home_score_tv.text = resources.getString(R.string.Zero)
            away_score_tv.text = resources.getString(R.string.Zero)


        } else {

            home_score_tv.text = events.homeScore.toString()
            away_score_tv.text = events.awayScore.toString()

        }

        away_team_tv.text = events.awayTeam

        home_goal_tv.text = events.homeGoal
        away_goal_tv.text = events.awayGoal

        if(events.homeShots == null || events.awayShots == null) {

            home_shots_tv.text = ""
            away_shots_tv.text = ""

        } else {

            home_shots_tv.text = events.homeShots.toString()
            away_shots_tv.text = events.awayShots.toString()

        }

        home_keeper_tv.text = events.homeKeeper
        home_defense_tv.text = events.homeDefense
        home_mid_tv.text = events.homeMid
        home_forward_tv.text =  events.homeForward
        home_subs_tv.text = events.homeSubs

        away_keeper_tv.text = events.awayKeeper
        away_defense_tv.text = events.awayDefense
        away_mid_tv.text = events.awayMid
        away_forward_tv.text =  events.awayForward
        away_subs_tv.text = events.awaySubs

    }

    private fun stateFavorite() {

        try {
            sqlite.use {
                val result = select(FavoritesMatch.TABLE_NAME)
                        .whereArgs("(ID_EVENT_MATCHES={events})", "events" to events.idEvent.toString())
                val favorite = result.parseList(classParser<FavoritesMatch>())

                if(!favorite.isEmpty()) isFavorite = true
            }
        } catch (exc: InvocationTargetException) {
            exc.printStackTrace()
        }

    }

    private fun addFavorite() {

        try {
            sqlite.use {
                insert(FavoritesMatch.TABLE_NAME,
                        FavoritesMatch.ID_EVENT_MATCH to events.idEvent.toString(),
                        FavoritesMatch.DATE_EVENT to events.dateEvent,
                        FavoritesMatch.DATE_TIME to events.dateTime,
                        FavoritesMatch.ID_HOME_TEAM to events.idHomeTeam,
                        FavoritesMatch.ID_AWAY_TEAM to events.idAwayTeam,
                        FavoritesMatch.HOME_SCORE to events.homeScore,
                        FavoritesMatch.AWAY_SCORE to events.awayScore,
                        FavoritesMatch.HOME_TEAM to events.homeTeam,
                        FavoritesMatch.AWAY_TEAM to events.awayTeam,
                        FavoritesMatch.HOME_GOAL to events.homeGoal,
                        FavoritesMatch.AWAY_GOAL to events.awayGoal,
                        FavoritesMatch.HOME_SHOTS to events.homeShots,
                        FavoritesMatch.AWAY_SHOTS to events.awayShots,
                        FavoritesMatch.HOME_KEEPER to events.homeKeeper,
                        FavoritesMatch.AWAY_KEEPER to events.awayKeeper,
                        FavoritesMatch.HOME_DEFENSE to events.homeDefense,
                        FavoritesMatch.AWAY_DEFENSE to events.awayDefense,
                        FavoritesMatch.HOME_MID to events.homeMid,
                        FavoritesMatch.AWAY_MID to events.awayMid,
                        FavoritesMatch.HOME_FORWARD to events.homeForward,
                        FavoritesMatch.AWAY_FORWARD to events.awayForward,
                        FavoritesMatch.HOME_SUBS to events.homeSubs,
                        FavoritesMatch.AWAY_SUBS to events.awaySubs,
                        FavoritesMatch.STR_LEAGUE to events.strLeague)
            }
            Snackbar.make(refresh_detail_match, getString(R.string.Added_to_Favorite), Snackbar.LENGTH_SHORT).show()
        } catch (exc: SQLiteConstraintException) {
            exc.printStackTrace()
        }

    }

    private fun removeFavorite() {

        try {
            sqlite.use {
                delete(FavoritesMatch.TABLE_NAME, "(ID_EVENT_MATCHES={events})",
                        "events" to events.idEvent.toString())
            }
            Snackbar.make(refresh_detail_match, getString(R.string.Remove_from_Favorite), Snackbar.LENGTH_SHORT).show()
        } catch (exc: SQLiteConstraintException) {
            exc.printStackTrace()
        }

    }

    private fun setFavorite() {

        if(isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
        }

    }

}