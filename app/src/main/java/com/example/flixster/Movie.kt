package com.example.flixster

import org.json.JSONArray

// Data model
data class Movie(val title: String,
                 val movieId: Int,
                 private val posterPath: String,
                 val overview: String) {

    val posterImageUrl = "https://image.tmdb.org/t/p/w342/$posterPath"

    companion object {

        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {

            val movies = mutableListOf<Movie>()

            // Parse info from all 20 movies
            for (i in 0 until movieJsonArray.length()) {

                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(Movie(movieJson.getString("title"),
                                 movieJson.getInt("id"),
                                 movieJson.getString("poster_path"),
                                 movieJson.getString("overview"))
                )
            }
            return movies
        }
    }
}