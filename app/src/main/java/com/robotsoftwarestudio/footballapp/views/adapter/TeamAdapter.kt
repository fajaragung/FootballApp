package com.robotsoftwarestudio.footballapp.views.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.models.team.Teams
import com.robotsoftwarestudio.footballapp.views.main.DetailTeamActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_teams.view.*
import org.jetbrains.anko.startActivity

class TeamAdapter(private val context: Context, private val teamList: List<Teams>):
        RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_teams, parent, false))

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: TeamAdapter.ViewHolder, position: Int) { holder.bindMatch(teamList[position]) }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindMatch(teams: Teams) {

            //display to ui
            if(teams.teamBadge != null) {
                Picasso.get().load(teams.teamBadge).into(itemView.pic_img)
            } else {
                Picasso.get().load(R.drawable.ic_player_no_image).into(itemView.pic_img)
            }

            itemView.name_tv.text = teams.teamName

            itemView.setOnClickListener {
                context.startActivity<DetailTeamActivity>("TEAMS" to teams)
            }

        }

    }

}