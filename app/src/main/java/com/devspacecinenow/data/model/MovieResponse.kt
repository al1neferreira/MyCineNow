package com.devspacecinenow.data.model

@kotlinx.serialization.Serializable
data class MovieResponse(

    val results: List<MovieDto>

)