package com.sebasorozcob.www.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sebasorozcob.www.domain.model.Credit

data class CreditsDto(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val cast: ArrayList<Credit>
)
