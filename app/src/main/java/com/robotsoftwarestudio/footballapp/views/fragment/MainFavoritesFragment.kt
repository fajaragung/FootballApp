package com.robotsoftwarestudio.footballapp.views.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.robotsoftwarestudio.footballapp.extensions.gone
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.views.adapter.TabPageAdapter
import com.robotsoftwarestudio.footballapp.views.fragmenttab.TabFavoriteMatch
import com.robotsoftwarestudio.footballapp.views.fragmenttab.TabFavoriteTeam
import com.robotsoftwarestudio.footballapp.views.main.AboutAppActivity
import kotlinx.android.synthetic.main.fragment_nav_main.view.*
import org.jetbrains.anko.startActivity

class MainFavoritesFragment: Fragment() {

    private lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_nav_main, container, false)

        //gone spinner on layout
        v.spinner_league.gone()

        setHasOptionsMenu(true)

        setUpTab()

        return v
    }

    /**
     * this method setup tab title
     */
    private fun setUpTab() {

        //instance
        val adapter = TabPageAdapter(childFragmentManager)

        //set tab view
        adapter.setUPTab(TabFavoriteMatch(), getString(R.string.Match))
        adapter.setUPTab(TabFavoriteTeam(), getString(R.string.Team))

        v.view_page_main.adapter = adapter
        v.main_tab_layout.setupWithViewPager(v.view_page_main)

    }

    /**
     * this method inflate layout menu to toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.toolbar_search, menu)

        //initiallize
        val search = menu?.findItem(R.id.action_search)
        search?.isVisible = false

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {

            R.id.action_about_app -> context?.startActivity<AboutAppActivity>()

        }

        return super.onOptionsItemSelected(item)
    }

}