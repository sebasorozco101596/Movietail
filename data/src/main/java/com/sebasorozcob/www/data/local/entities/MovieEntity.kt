package com.sebasorozcob.www.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MOVIE")
data class MovieEntity(
    @PrimaryKey val id: Int? = null
)