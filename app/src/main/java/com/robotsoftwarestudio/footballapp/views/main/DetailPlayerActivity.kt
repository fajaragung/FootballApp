package com.robotsoftwarestudio.footballapp.views.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.robotsoftwarestudio.footballapp.R
import com.robotsoftwarestudio.footballapp.models.player.Players
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*
import kotlinx.android.synthetic.main.support_toolbar.*

class DetailPlayerActivity: AppCompatActivity() {

    private lateinit var players: Players

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        setSupportActionBar(support_toolbar)
        supportActionBar?.title = null

        players = intent.extras.getSerializable("PLAYER") as Players
        displayDataToUI()
    }

    private fun displayDataToUI() {

        custom_title.text = players.playerName

        if(players.playerImage == null || players.playerThumb == null) {

            player_image.setImageResource(R.drawable.ic_player_no_image)
            thumb_image.setImageResource(R.drawable.ic_broken_image)

        } else {

            Picasso.get().load(players.playerThumb).into(thumb_image)
            Picasso.get().load(players.playerImage).into(player_image)

        }

        player_name_tv.text = resources.getString(R.string.Colon) + players.playerName
        player_date_born_tv.text = resources.getString(R.string.Colon) + players.playerDateBorn
        player_nationality_tv.text = resources.getString(R.string.Colon) + players.playerNationality
        player_weight_tv.text = players.playerWeight
        player_height_tv.text = players.playerHeight

        player_club_tv.text = resources.getString(R.string.Colon) + players.teamName
        player_date_signed_tv.text = resources.getString(R.string.Colon) + players.playerDateSigned
        player_price_tv.text = resources.getString(R.string.Colon) + players.playerPrice
        player_salary_tv.text = resources.getString(R.string.Colon) + players.playerSalary
        player_position_tv.text = resources.getString(R.string.Colon) + players.playerPosition

        player_description_tv.text = players.playerDescription


    }
}