package com.devspacecinenow.data.model

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
class MovieDto (
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String
)