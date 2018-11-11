package com.robotsoftwarestudio.footballapp.utils

import io.reactivex.Scheduler

interface SchedulerContract {

    fun ui(): Scheduler
    fun computation(): Scheduler
    fun trampoline(): Scheduler
    fun newThread(): Scheduler
    fun io(): Scheduler

}