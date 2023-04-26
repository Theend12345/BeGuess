package com.vyapp.beguess.domain

import com.vyapp.beguess.domain.repository.BeguessRepository

class GetTaskUseCase(val repository: BeguessRepository) {

    fun execute() = repository.getTask()

}