package com.vyapp.beguess.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vyapp.beguess.domain.*

class BeguessViewModelFactory(
    val registrationUseCase: RegistrationUseCase,
    val sightInUseCase: SightInUseCase,
    val usersUseCase: GetAllUsersUseCase,
    val getTaskUseCase: GetTaskUseCase,
    val rightAnswerUseCase: RightAnswerUseCase,
    val getUserUseCase: GetUserUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeGuessViewModel(
            registrationUseCase,
            sightInUseCase,
            usersUseCase,
            getTaskUseCase,
            rightAnswerUseCase,
            getUserUseCase
        ) as T
    }

}