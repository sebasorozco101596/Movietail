package com.sebasorozcob.www.data.remote.api

import com.sebasorozcob.www.data.remote.dto.CreditsDto
import com.sebasorozcob.www.data.remote.dto.MovieDto
import com.sebasorozcob.www.data.remote.dto.MoviesDto
import com.sebasorozcob.www.data.remote.dto.NowPlayingMoviesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovietailApi {

    @GET("discover/movie?api_key=5669628df72e3478c3edcd2e56e9dc8f&language=en-US" +
            "&sort_by=popularity.desc&include_adult=false" +
            "&with_watch_monetization_types=flatrate")
    suspend fun getMovies(
        @Query("page") page: Int
    ): Response<MoviesDto>

    @GET("search/movie?api_key=5669628df72e3478c3edcd2e56e9dc8f&language=en-US" +
            "&page=1&include_adult=false")
    suspend fun searchMovies(
       @Query("query") query: String
    ): Response<MoviesDto>

    // https://api.themoviedb.org/3/movie/675353?api_key=5669628df72e3478c3edcd2e56e9dc8f&language=en-US
    @GET("movie/{id}?language=en-US")
    suspend fun getMovieDetail(
        @Path("id") movieID: String
    ): Response<MovieDto>

    @GET("movie/{movie_id}/credits?api_key=5669628df72e3478c3edcd2e56e9dc8f&language=en-US")
    suspend fun getCredits(
        @Path("movie_id") movieID: Int
    ): Response<CreditsDto>

    @GET("https://api.themoviedb.org/3/movie/now_playing?api_key=5669628df72e3478c3edcd2e56e9dc8f" +
            "&language=en-US&region=US")
    suspend fun getMoviesNowPlaying(
        @Query("page") page: Int
    ): Response<NowPlayingMoviesDto>

}