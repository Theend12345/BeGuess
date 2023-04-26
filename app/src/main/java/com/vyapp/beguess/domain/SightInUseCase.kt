package com.vyapp.beguess.domain

import com.vyapp.beguess.domain.model.UserRegisterDomain
import com.vyapp.beguess.domain.repository.BeguessRepository

class SightInUseCase(val repository: BeguessRepository) {

    fun execute(user: UserRegisterDomain) = repository.sightIn(user)

}