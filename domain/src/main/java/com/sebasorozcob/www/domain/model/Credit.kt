package com.sebasorozcob.www.domain.model

data class Credit(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val knownDepartment: String,
    val name: String,
    val popularity: Double,
    val profilePhoto: String,
    val castId: Int,
    val character: String,
    val creditId: String,
    val order: Int
)
