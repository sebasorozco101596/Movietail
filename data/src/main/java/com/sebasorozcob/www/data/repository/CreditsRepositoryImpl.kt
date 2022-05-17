package com.sebasorozcob.www.data.repository

import com.sebasorozcob.www.data.mapper.toCredits
import com.sebasorozcob.www.data.remote.api.MovietailApi
import com.sebasorozcob.www.domain.model.Credits
import com.sebasorozcob.www.domain.repository.CreditsRepository
import javax.inject.Inject

class CreditsRepositoryImpl @Inject constructor(
    private val api: MovietailApi
): CreditsRepository {

    override suspend fun getCredits(movieId: String): Credits {
        return api.getCredits(movieId).body()!!.toCredits()
    }

}