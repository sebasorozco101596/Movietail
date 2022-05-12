package com.sebasorozcob.www.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sebasorozcob.www.data.local.dao.MovietailDao
import com.sebasorozcob.www.data.local.entities.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovietailDatabase: RoomDatabase() {
    abstract fun getDao(): MovietailDao
}