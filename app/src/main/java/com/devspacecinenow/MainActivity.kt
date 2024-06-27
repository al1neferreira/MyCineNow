package com.devspacecinenow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.devspacecinenow.data.model.MovieDto
import com.devspacecinenow.data.model.MovieResponse
import com.devspacecinenow.data.remote.ApiService
import com.devspacecinenow.data.remote.RetrofitClient
import com.devspacecinenow.ui.theme.CineNowTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CineNowTheme {
                var nowPLayingMovies: List<MovieDto> by remember {
                    mutableStateOf<List<MovieDto>>(emptyList())
                }
                val apiService =
                    RetrofitClient
                        .retrofitInstance
                        .create(ApiService::class.java)

                val callNowPlaying = apiService.getNowPlaying()

                callNowPlaying.enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if (response.isSuccessful) {
                            val movies = response.body()?.results
                            if (movies != null) {
                                nowPLayingMovies = movies
                            }

                        } else {
                            Log.d("MainActivity", "Request Error :: ${response.errorBody()}")
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Log.d("MainActivity", "Network Error :: ${t.message}")
                    }
                })

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    LazyColumn {
                        items(nowPLayingMovies){
                            Text(text = it.title)
                            
                        }

                    }

                }
            }
        }
    }
}


