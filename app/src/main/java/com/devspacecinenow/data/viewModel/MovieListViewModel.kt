package com.devspacecinenow.data.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.devspacecinenow.common.RetrofitClient
import com.devspacecinenow.data.model.MovieDto
import com.devspacecinenow.data.model.MovieResponse
import com.devspacecinenow.data.remote.ListService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListViewModel(
    listService: ListService
):ViewModel() {

    private val _uiNowPlaying = MutableStateFlow<List<MovieDto>>(emptyList())
    val uiNowPlaying: StateFlow<List<MovieDto>> = _uiNowPlaying


    init {
        listService.getNowPlaying().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    if (movies != null) {
                        _uiNowPlaying.value= movies
                        Log.d("MainActivity", "${response.body()}")
                    }
                } else {
                    Log.d("ListViewModel", "Request Error :: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("ListViewModel", "Network Error :: ${t.message}")
            }
        })

    }

    companion object {
        val Factory: ViewModelProvider.Factory = object: ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val listService = RetrofitClient.retrofitInstance.create(ListService::class.java)
                return MovieListViewModel(
                    listService
                ) as T
            }
        }
    }
}