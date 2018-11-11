package com.robotsoftwarestudio.footballapp.views.main

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.extensions.sqlite
import com.robotsoftwarestudio.footballapp.models.favorite.FavoritesTeam
import com.robotsoftwarestudio.footballapp.models.team.Teams
import com.robotsoftwarestudio.footballapp.views.adapter.TabPageAdapter
import com.robotsoftwarestudio.footballapp.views.fragmenttab.TabOverviewsFragment
import com.robotsoftwarestudio.footballapp.views.fragmenttab.TabPlayersFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.support_toolbar.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.lang.reflect.InvocationTargetException

class DetailTeamActivity: AppCompatActivity() {

    private var isFavorite = false
    private var menuItem: Menu? = null

    companion object {

        lateinit var mTeams: Teams

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        setSupportActionBar(support_toolbar)
        supportActionBar?.title = getString(R.string.Detail_Team)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mTeams = intent.extras.getSerializable("TEAMS") as Teams

        setUpTab()
        displayDataToUI()
        stateFavorite()

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

    private fun stateFavorite() {

        try {
            sqlite.use {
                val result = select(FavoritesTeam.TABLE_NAME)
                        .whereArgs("(ID_TEAMS={mTeams})", "mTeams" to mTeams.idTeam.toString())
                val favorite = result.parseList(classParser<FavoritesTeam>())

                if(!favorite.isEmpty()) isFavorite = true
            }
        } catch (exc: InvocationTargetException) {
            exc.printStackTrace()
        }

    }

    private fun addFavorite() {

        try {
            sqlite.use {
                insert(FavoritesTeam.TABLE_NAME,
                        FavoritesTeam.ID_TEAM to mTeams.idTeam,
                        FavoritesTeam.ID_LEAGUE to mTeams.idLeague,
                        FavoritesTeam.LEAGUE to mTeams.strLeague,
                        FavoritesTeam.TEAM_NAME to mTeams.teamName,
                        FavoritesTeam.TEAM_FORMED to mTeams.teamFormed,
                        FavoritesTeam.TEAM_STADIUM to mTeams.teamStadium,
                        FavoritesTeam.TEAM_DESCRIPTION to mTeams.teamDescription,
                        FavoritesTeam.TEAM_BADGE to mTeams.teamBadge)
            }

            Snackbar.make(coordinator_layout, getString(R.string.Added_to_Favorite), Snackbar.LENGTH_SHORT).show()

        } catch (exc: SQLiteConstraintException) {
            exc.printStackTrace()
        }

    }

    private fun removeFavorite() {

        try {
            sqlite.use {
                delete(FavoritesTeam.TABLE_NAME, "(ID_TEAMS={mTeams})",
                        "mTeams" to mTeams.idTeam.toString())
            }
            Snackbar.make(coordinator_layout, getString(R.string.Remove_from_Favorite), Snackbar.LENGTH_SHORT).show()
        } catch (exc: SQLiteConstraintException) {
            exc.printStackTrace()
        }

    }

    private fun setUpTab() {

        //instance
        val adapter = TabPageAdapter(supportFragmentManager)

        //set tab view
        adapter.setUPTab(TabOverviewsFragment(), getString(R.string.Overviews))
        adapter.setUPTab(TabPlayersFragment(), getString(R.string.Players))

        view_page_team_detail.adapter = adapter
        tab_team_detail.setupWithViewPager(view_page_team_detail)

    }

    private fun displayDataToUI() {

        Picasso.get().load(mTeams.teamBadge).into(team_image)
        team_name_tv.text = mTeams.teamName
        established_since_tv.text = mTeams.teamFormed.toString()
        stadium_name_tv.text = mTeams.teamStadium

    }

    private fun setFavorite() {

        if(isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
        }

    }

}