package com.sebasorozcob.www.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Credits(
    val id: Int,
    val cast: @RawValue List<Credit>
): Parcelable
