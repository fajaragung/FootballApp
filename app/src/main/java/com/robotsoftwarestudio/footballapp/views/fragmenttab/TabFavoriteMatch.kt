package com.robotsoftwarestudio.footballapp.views.fragmenttab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robotsoftwarestudio.footballapp.extensions.gone
import com.robotsoftwarestudio.footballapp.extensions.visible
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.extensions.sqlite
import com.robotsoftwarestudio.footballapp.models.favorite.FavoritesMatch
import com.robotsoftwarestudio.footballapp.views.adapter.recycler.FavoriteMatchAdapter
import kotlinx.android.synthetic.main.fragment_nav_main.*
import kotlinx.android.synthetic.main.fragment_nav_main.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class TabFavoriteMatch: Fragment() {

    private lateinit var v: View
    private val mListFavorites = mutableListOf<FavoritesMatch>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_nav_main, container, false)

        //gone tab layout, spinner and view pager then show recycler view
        v.main_tab_layout.gone()
        v.spinner_league.gone()
        v.view_page_main.gone()
        v.refresh_main.visible()

        return v
    }

    override fun onResume() {
        super.onResume()

        inflateAdapter()

        listFavorite()

        v.refresh_main.setOnRefreshListener { listFavorite(); refresh_main.isRefreshing = false }
    }

    private fun listFavorite() {
        activity!!.sqlite.use {
            val result = select(FavoritesMatch.TABLE_NAME)
            val favorite = result.parseList(classParser<FavoritesMatch>())

            mListFavorites.clear()
            mListFavorites.addAll(favorite)
        }
    }

    private fun inflateAdapter() {

        v.recycler_main.layoutManager = LinearLayoutManager(activity)
        v.recycler_main.adapter = FavoriteMatchAdapter(activity!!.applicationContext, mListFavorites)
        v.recycler_main.adapter.notifyDataSetChanged()

    }

}