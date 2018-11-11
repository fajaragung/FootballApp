package com.robotsoftwarestudio.footballapp.views.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.views.fragment.MainFavoritesFragment
import com.robotsoftwarestudio.footballapp.views.fragment.MainMatchesFragment
import com.robotsoftwarestudio.footballapp.views.fragment.MainTeamsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.support_toolbar.*

class MainActivity: AppCompatActivity() {

    companion object {
        var idlingResourceCounter = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(support_toolbar)

        isSelectedNavBottom(savedInstanceState)

    }

    private fun isSelectedNavBottom(bundle: Bundle?) {

        bottom_nav.setOnNavigationItemSelectedListener {

            when(it.itemId) {

                R.id.bottom_menu_matches -> {
                    replaceFragment(MainMatchesFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.bottom_menu_Teams -> {
                    replaceFragment(MainTeamsFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.bottom_menu_Favorites -> {
                    replaceFragment(MainFavoritesFragment())
                    return@setOnNavigationItemSelectedListener true
                }

            }

            false
        }

        if(bundle == null) {

            bottom_nav.selectedItemId = R.id.bottom_menu_matches

        }

    }

    private fun replaceFragment(fragment: Fragment) {

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit()

    }

}