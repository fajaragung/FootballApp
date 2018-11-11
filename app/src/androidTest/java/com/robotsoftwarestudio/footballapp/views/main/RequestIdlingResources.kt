package com.robotsoftwarestudio.footballapp.views.main

import android.support.test.espresso.IdlingResource

class RequestIdlingResources: IdlingResource {

    override fun getName(): String {
        return "${RequestIdlingResources::class.java.simpleName}"
    }

    override fun isIdleNow(): Boolean {
        var isIdle = false

        if(MainActivity.idlingResourceCounter == 0) {
            callback?.onTransitionToIdle()
            isIdle = true
        }

        return isIdle
    }

    private var callback: IdlingResource.ResourceCallback? = null

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

}