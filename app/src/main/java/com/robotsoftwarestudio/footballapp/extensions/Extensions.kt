package com.robotsoftwarestudio.footballapp.extensions

import android.content.Context
import android.view.View
import com.robotsoftwarestudio.footballapp.database.SQLiteHelper

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

val Context.sqlite: SQLiteHelper
    get() = SQLiteHelper.getInstance(applicationContext)