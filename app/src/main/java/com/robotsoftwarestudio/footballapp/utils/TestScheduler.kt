package com.robotsoftwarestudio.footballapp.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestScheduler: SchedulerContract {

    override fun ui(): Scheduler = Schedulers.trampoline()

    override fun computation(): Scheduler = Schedulers.trampoline()

    override fun trampoline(): Scheduler = Schedulers.trampoline()

    override fun newThread(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()

}