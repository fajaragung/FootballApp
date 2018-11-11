package com.robotsoftwarestudio.footballapp.views.adapter

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.models.event.Events
import com.robotsoftwarestudio.footballapp.views.main.DetailMatchActivity
import kotlinx.android.synthetic.main.adapter_event.view.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class EventAdapter(private val context: Context, private val eventList: List<Events>):
        RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_event, parent, false))

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: EventAdapter.ViewHolder, position: Int) { holder.bindMatch(eventList[position]) }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindMatch(events: Events) {

            //display to ui
            val event = SimpleDateFormat("yyyy-MM-d").parse(events.dateEvent)
            val dateEvent = SimpleDateFormat("EEE, d MMM yyyy", Locale.US).format(event)
            itemView.date_event_tv.text = dateEvent

            val time = SimpleDateFormat("HH:mm:ss").parse(events.dateTime)
            val dateTime = SimpleDateFormat("HH:mmzz", Locale.getDefault()).format(time)

            itemView.time_event_tv.text = dateTime

            itemView.home_team_tv.text = events.homeTeam

            if(events.homeScore == null || events.awayScore == null) {
                itemView.home_score_tv.text = itemView.resources.getString(R.string.Zero)
                itemView.away_score_tv.text = itemView.resources.getString(R.string.Zero)
            } else {
                itemView.home_score_tv.text = events.homeScore.toString()
                itemView.away_score_tv.text = events.awayScore.toString()
            }

            itemView.away_team_tv.text = events.awayTeam

            val beginTime = dateEvent + dateTime
            addToCalendar(itemView.add_to_calendar_btn, events, beginTime)

            itemView.setOnClickListener {

                context.startActivity<DetailMatchActivity>("EVENTS" to events)

            }

        }

        private fun addToCalendar(button: Button, events: Events, beginTime: String) {

            button.setOnClickListener {

                if(events.homeScore == null || events.awayScore == null) {
                    val intent = Intent(Intent.ACTION_EDIT)
                    intent.type = "vnd.android.cursor.item/event"
                    intent.putExtra(CalendarContract.Events.TITLE, events.homeTeam + " VS " + events.awayTeam)
                    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime)
                    intent.putExtra(CalendarContract.Events.ALL_DAY, false)
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "TV")
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivities(arrayOf(intent))
                } else {
                    Toast.makeText(context, context.getString(R.string.Failed_add_to_Calendar), Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

}