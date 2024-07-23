package com.devspacecinenow.data.remote

import com.devspacecinenow.data.model.MovieDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("{movie_id}?language=en-US")
    fun getMovieById(@Path("movie_id") movieId: String): Call<MovieDto>
}