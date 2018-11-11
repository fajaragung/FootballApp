package com.robotsoftwarestudio.footballapp.views.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.models.favorite.FavoritesTeam
import com.robotsoftwarestudio.footballapp.views.main.DetailTeamFavoriteActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_teams.view.*
import org.jetbrains.anko.startActivity

class FavoriteTeamAdapter(private val context: Context, private val favoritesTeam: List<FavoritesTeam>):
        RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_teams, parent, false))

    override fun getItemCount(): Int = favoritesTeam.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { holder.bindFavorite(favoritesTeam[position]) }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindFavorite(favorite: FavoritesTeam) {

            //display to ui
            if(favorite.teamBadge != null) {
                Picasso.get().load(favorite.teamBadge).into(itemView.pic_img)
            } else {
                Picasso.get().load(R.drawable.ic_player_no_image).into(itemView.pic_img)
            }

            itemView.name_tv.text = favorite.teamName

            itemView.setOnClickListener {
                context.startActivity<DetailTeamFavoriteActivity>("TEAMS" to favorite)
            }

        }

    }
}