package com.sebasorozcob.www.data.mapper

import com.sebasorozcob.www.data.remote.dto.MovieDto
import com.sebasorozcob.www.data.remote.dto.MoviesDto
import com.sebasorozcob.www.domain.model.Movie
import com.sebasorozcob.www.domain.model.Movies

fun MoviesDto.toMovies(): Movies {
    return Movies(page, results.map { it.toMovie() } , totalPages,totalResults)
}

fun MovieDto.toMovie(): Movie {
    return Movie(isAdult, posterImage, genreIds, id, originalLanguage, overview, popularity, mainImage, releaseDate, title, voteAverage, voteCount)
}