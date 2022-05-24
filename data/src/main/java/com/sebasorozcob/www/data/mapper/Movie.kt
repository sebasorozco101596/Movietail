package com.sebasorozcob.www.data.mapper

import androidx.lifecycle.Transformations.map
import com.sebasorozcob.www.data.remote.dto.DatesDto
import com.sebasorozcob.www.data.remote.dto.MovieDto
import com.sebasorozcob.www.data.remote.dto.MoviesDto
import com.sebasorozcob.www.data.remote.dto.NowPlayingMoviesDto
import com.sebasorozcob.www.domain.model.Dates
import com.sebasorozcob.www.domain.model.Movie
import com.sebasorozcob.www.domain.model.Movies
import com.sebasorozcob.www.domain.model.NowPlayingMovies
import java.util.*

fun MoviesDto.toMovies(): Movies {
    return Movies(
        page,
        results.map { it.toMovie() } ,
        totalPages,
        totalResults)
}

fun MovieDto.toMovie(): Movie {
    return Movie(
        isAdult,
        posterImage,
        genreIds,
        id,
        originalLanguage,
        overview,
        popularity,
        mainImage,
        releaseDate,
        title,
        voteAverage,
        voteCount)
}

fun DatesDto.toDates(): Dates {
    return Dates(
        maximum,
        minimum)
}

fun NowPlayingMoviesDto.toNowPlayingMovies(): NowPlayingMovies {
    return NowPlayingMovies(
        dates.toDates(),
        page,
        results.map {it.toMovie()},
        totalPages,
        totalResults)
}