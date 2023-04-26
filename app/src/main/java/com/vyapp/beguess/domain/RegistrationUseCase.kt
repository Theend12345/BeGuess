package com.vyapp.beguess.domain

import com.vyapp.beguess.domain.model.UserRegisterDomain
import com.vyapp.beguess.domain.repository.BeguessRepository

class RegistrationUseCase(val repository: BeguessRepository) {

    fun execute(user: UserRegisterDomain) = repository.register(user)

}