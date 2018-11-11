package com.robotsoftwarestudio.footballapp.rest

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    companion object {

        fun create(): ApiRepository {

            val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(ApiRepository::class.java)


        }

    }

}