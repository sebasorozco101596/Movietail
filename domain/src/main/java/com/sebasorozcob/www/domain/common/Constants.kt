package com.sebasorozcob.www.domain.common

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.sebasorozcob.www.domain.BuildConfig

object Constants {

    // Remote constants
    const val BASE_URL = "https://api.themoviedb.org/3/"
    //const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original"
    //const val DATABASE_NAME = "movietail_database"

    const val MOVIE_RESULT_KEY = "movieBundle"

    const val API_KEY = BuildConfig.API_KEY



    enum class Recyclers{
        POPULAR,NOW_CINE
    }
}