package com.devspacecinenow.data.remote

import com.devspacecinenow.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("now_playing?language=en-US&page=1")
    fun getAthleteListings(): Call<MovieResponse>
}