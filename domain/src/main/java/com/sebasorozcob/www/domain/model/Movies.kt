package com.sebasorozcob.www.domain.model

data class Movies (
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)