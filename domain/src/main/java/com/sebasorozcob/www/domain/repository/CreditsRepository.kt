package com.sebasorozcob.www.domain.repository

import com.sebasorozcob.www.domain.model.Credits

interface CreditsRepository {
    suspend fun getCredits(movieId: Int): Credits
}