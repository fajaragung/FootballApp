package com.robotsoftwarestudio.footballapp.views.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.robotsoftwarestudio.footballapp.R
import kotlinx.android.synthetic.main.support_toolbar.*

class AboutAppActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)

        setSupportActionBar(support_toolbar)
        supportActionBar?.title = getString(R.string.About_App)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}