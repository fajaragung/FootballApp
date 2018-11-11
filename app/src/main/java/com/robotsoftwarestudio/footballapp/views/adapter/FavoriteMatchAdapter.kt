package com.robotsoftwarestudio.footballapp.views.adapter.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.extensions.gone
import com.robotsoftwarestudio.footballapp.models.favorite.FavoritesMatch
import com.robotsoftwarestudio.footballapp.views.main.DetailMatchFavoriteActivity
import kotlinx.android.synthetic.main.adapter_event.view.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class FavoriteMatchAdapter(private val context: Context, private val favoritesMatch: List<FavoritesMatch>):
        RecyclerView.Adapter<FavoriteMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_event, parent, false))

    override fun getItemCount(): Int = favoritesMatch.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { holder.bindFavorite(favoritesMatch[position]) }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindFavorite(favorite: FavoritesMatch) {

            val event = SimpleDateFormat("yyyy-MM-d").parse(favorite.dateEvent)
            val dateEvent = SimpleDateFormat("EEE, d MMM yyyy", Locale.US).format(event)
            itemView.date_event_tv.text = dateEvent

            itemView.add_to_calendar_btn.gone()

            val time = SimpleDateFormat("HH:mm:ss").parse(favorite.dateTime)
            val dateTime = SimpleDateFormat("HH:mm", Locale.US).format(time)
            itemView.time_event_tv.text = dateTime

            itemView.home_team_tv.text = favorite.homeTeam

            if(favorite.homeScore == null || favorite.awayScore == null) {
                itemView.home_score_tv.text = itemView.resources.getString(R.string.Zero)
                itemView.away_score_tv.text = itemView.resources.getString(R.string.Zero)
            } else {
                itemView.home_score_tv.text = favorite.homeScore.toString()
                itemView.away_score_tv.text = favorite.awayScore.toString()
            }

            itemView.away_team_tv.text = favorite.awayTeam

            itemView.setOnClickListener {

                context.startActivity<DetailMatchFavoriteActivity>("EVENTS" to favorite)

            }

        }

    }
}