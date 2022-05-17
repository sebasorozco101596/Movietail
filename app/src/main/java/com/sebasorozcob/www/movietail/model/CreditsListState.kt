package com.sebasorozcob.www.movietail.model

import com.sebasorozcob.www.domain.model.Credits
import com.sebasorozcob.www.domain.model.Movies

data class CreditsListState(
    val isLoading: Boolean = false,
    val credits: Credits? = null,
    val error: String = ""
)