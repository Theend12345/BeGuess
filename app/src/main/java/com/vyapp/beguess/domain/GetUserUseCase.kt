package com.vyapp.beguess.domain

import com.vyapp.beguess.domain.model.UserDomain
import com.vyapp.beguess.domain.repository.BeguessRepository

class GetUserUseCase(val repository: BeguessRepository) {

    fun execute(user: UserDomain) = repository.getUser(user)

}