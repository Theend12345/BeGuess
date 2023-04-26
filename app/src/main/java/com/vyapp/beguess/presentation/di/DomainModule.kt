package com.vyapp.beguess.presentation.di

import com.vyapp.beguess.domain.*
import com.vyapp.beguess.domain.repository.BeguessRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideRegistrationUseCase(repository: BeguessRepository): RegistrationUseCase =
        RegistrationUseCase(repository)

    @Provides
    fun provideSightInUseCase(repository: BeguessRepository): SightInUseCase =
        SightInUseCase(repository)

    @Provides
    fun provideUsersUseCase(repository: BeguessRepository): GetAllUsersUseCase =
        GetAllUsersUseCase(repository)

    @Provides
    fun provideGetTaskUseCase(repository: BeguessRepository): GetTaskUseCase =
        GetTaskUseCase(repository)

    @Provides
    fun provideRightAnswerUseCase(repository: BeguessRepository): RightAnswerUseCase =
        RightAnswerUseCase(repository)

    @Provides
    fun provideGetUserUseCase(repository: BeguessRepository): GetUserUseCase =
        GetUserUseCase(repository)

}