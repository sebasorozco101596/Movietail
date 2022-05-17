package com.sebasorozcob.www.domain.use_case.credits

import com.sebasorozcob.www.domain.common.Resource
import com.sebasorozcob.www.domain.model.Credits
import com.sebasorozcob.www.domain.repository.CreditsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCreditsUseCase @Inject constructor(
    private val repository: CreditsRepository
){
    operator fun invoke(movieId: Int) : Flow<Resource<Credits>> = flow {
        try {
            emit(Resource.Loading())
            val credits = repository.getCredits(movieId)
            emit(Resource.Success(credits))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection!"))
        }
    }
}