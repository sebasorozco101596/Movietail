package com.sebasorozcob.www.data.remote.api

import com.sebasorozcob.www.data.remote.dto.MovieDto
import com.sebasorozcob.www.data.remote.dto.MoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovietailApi {

    //https://api.themoviedb.org/3/discover/movie?api_key=5669628df72e3478c3edcd2e56e9dc8f&
    // language=en-US&sort_by=popularity.desc&include_adult=false&
    // &page=1&with_watch_monetization_types=flatrate
    @GET("discover/movie?api_key=5669628df72e3478c3edcd2e56e9dc8f&language=en-US" +
            "&sort_by=popularity.desc&include_adult=false" +
            "&with_watch_monetization_types=flatrate")
    // &page=1
    //@QueryMap queries: Map<String, String>
    suspend fun getMovies(
        @Query("page") page: Int
    ): Response<MoviesDto>

    // https://api.themoviedb.org/3/movie/675353?api_key=5669628df72e3478c3edcd2e56e9dc8f&language=en-US
    @GET("movie/{id}?language=en-US")
    suspend fun getMovieDetail(
        @Path("id") movieID: String
    ): Response<MovieDto>
}