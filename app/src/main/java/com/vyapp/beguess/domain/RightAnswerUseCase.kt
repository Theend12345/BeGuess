package com.vyapp.beguess.domain

import com.vyapp.beguess.domain.model.UserDomain
import com.vyapp.beguess.domain.repository.BeguessRepository
import io.reactivex.Observable

class RightAnswerUseCase(val repository: BeguessRepository) {

    fun execute(user: UserDomain) = repository.rightAnswer(user)

}