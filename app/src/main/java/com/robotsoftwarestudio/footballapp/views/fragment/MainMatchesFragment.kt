package com.robotsoftwarestudio.footballapp.views.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*

import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.extensions.gone
import com.robotsoftwarestudio.footballapp.views.adapter.TabPageAdapter
import com.robotsoftwarestudio.footballapp.views.fragmenttab.TabLastMatchFragment
import com.robotsoftwarestudio.footballapp.views.fragmenttab.TabNextMatchFragment
import com.robotsoftwarestudio.footballapp.views.main.AboutAppActivity
import com.robotsoftwarestudio.footballapp.views.main.SearchEventsActivity
import kotlinx.android.synthetic.main.fragment_nav_main.view.*
import org.jetbrains.anko.startActivity

class MainMatchesFragment : Fragment() {

    private lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_nav_main, container, false)
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
        adapter.setUPTab(TabNextMatchFragment(), getString(R.string.Next_Match))
        adapter.setUPTab(TabLastMatchFragment(), getString(R.string.Last_Match))

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
        val search = menu?.findItem(R.id.action_search)?.actionView as SearchView

        search.queryHint = getString(R.string.Search_Match)
        search.isSubmitButtonEnabled = true

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != "") {
                    context?.startActivity<SearchEventsActivity>("SEARCH" to query)
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

}
