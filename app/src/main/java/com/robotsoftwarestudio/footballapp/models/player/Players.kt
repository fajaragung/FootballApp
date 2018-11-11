package com.robotsoftwarestudio.footballapp.models.player

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Players (

        @SerializedName("idTeam")
        val idTeam: Int?,

        @SerializedName("strNationality")
        val playerNationality: String?,

        @SerializedName("strPlayer")
        val playerName: String?,

        @SerializedName("strTeam")
        val teamName: String?,

        @SerializedName("dateBorn")
        val playerDateBorn: String?,

        @SerializedName("dateSigned")
        val playerDateSigned: String?,

        @SerializedName("strSigning")
        val playerPrice: String?,

        @SerializedName("strWage")
        val playerSalary: String?,

        @SerializedName("strBirthLocation")
        val playerBirthLoc: String?,

        @SerializedName("strDescriptionEN")
        val playerDescription: String?,

        @SerializedName("strPosition")
        val playerPosition: String?,

        @SerializedName("strHeight")
        val playerHeight: String?,

        @SerializedName("strWeight")
        val playerWeight: String?,

        @SerializedName("strThumb")
        val playerThumb: String?,

        @SerializedName("strCutout")
        val playerImage: String?

): Serializable