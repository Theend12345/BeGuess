package com.vyapp.beguess.presentation.di

import com.vyapp.beguess.domain.*
import com.vyapp.beguess.presentation.BeguessViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideBeguessViewModelFactory(
        registrationUseCase: RegistrationUseCase,
        sightInUseCase: SightInUseCase,
        usersUseCase: GetAllUsersUseCase,
        getTaskUseCase: GetTaskUseCase,
        rightAnswerUseCase: RightAnswerUseCase,
        getUserUseCase: GetUserUseCase
    ): BeguessViewModelFactory =
        BeguessViewModelFactory(
            registrationUseCase,
            sightInUseCase,
            usersUseCase,
            getTaskUseCase,
            rightAnswerUseCase,
            getUserUseCase
        )

}