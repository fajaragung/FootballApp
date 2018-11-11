package com.robotsoftwarestudio.footballapp.views.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.extensions.visible
import com.robotsoftwarestudio.footballapp.models.player.Players
import com.robotsoftwarestudio.footballapp.views.main.DetailPlayerActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_teams.view.*
import org.jetbrains.anko.startActivity

class PlayerAdapter(private val context: Context, private val playerList: List<Players>):
        RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerAdapter.ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_teams, parent, false))

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: PlayerAdapter.ViewHolder, position: Int) { holder.bindMatch(playerList[position]) }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindMatch(players: Players) {

            itemView.position_tv.visible()

            //display to ui
            if(players.playerImage != null) {
                Picasso.get().load(players.playerImage).into(itemView.pic_img)
            } else {
                itemView.pic_img.setImageResource(R.drawable.ic_player_no_image)
            }

            itemView.name_tv.text = players.playerName
            itemView.position_tv.text = players.playerPosition

            itemView.setOnClickListener {
                context.startActivity<DetailPlayerActivity>("PLAYER" to players)
            }

        }

    }

}