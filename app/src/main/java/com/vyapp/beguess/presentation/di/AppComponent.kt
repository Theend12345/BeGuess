package com.vyapp.beguess.presentation.di

import com.vyapp.beguess.presentation.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {

    fun registerInject(registrationFragment: RegistrationFragment)
    fun sightInInject(sightInFragment: SightInFragment)
    fun userInject(userFragment: UserFragment)
    fun userListInject(userListFragment: UsersListFragment)
    fun mainInject(mainFragment: MainFragment)
    fun successInject(successFragment: SuccessFragment)

}