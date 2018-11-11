package com.robotsoftwarestudio.footballapp.views.fragmenttab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.views.main.DetailTeamFavoriteActivity
import kotlinx.android.synthetic.main.tab_overview.view.*

class TabOverviewsFavoriteFragment: Fragment() {

    private lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.tab_overview, container, false)

        v.overview_tv.text = DetailTeamFavoriteActivity.mTeams.teamDescription

        return v
    }

}