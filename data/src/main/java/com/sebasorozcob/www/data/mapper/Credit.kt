package com.sebasorozcob.www.data.mapper

import com.sebasorozcob.www.data.remote.dto.CreditDto
import com.sebasorozcob.www.data.remote.dto.CreditsDto
import com.sebasorozcob.www.domain.model.Credit
import com.sebasorozcob.www.domain.model.Credits

fun CreditsDto.toCredits(): Credits {
    return Credits(id, cast)
    //return Credits(page, results.map { it.toMovie() } , totalPages,totalResults)
}

fun CreditDto.toCredit(): Credit {
    return Credit(adult, gender, id, knownDepartment, name, popularity, profilePhoto, castId, character, creditId, order)
}