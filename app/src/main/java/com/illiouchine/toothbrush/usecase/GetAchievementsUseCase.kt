package com.illiouchine.toothbrush.usecase

import javax.inject.Inject

class GetAchievementsUseCase @Inject constructor(

){

    operator fun invoke(): List<Achievement>{
        return emptyList()
    }

    data class Achievement(
        val name: String,
        val description: String,
        val earned: Boolean
    )
}
