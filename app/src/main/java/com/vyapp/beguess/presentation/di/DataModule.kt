package com.vyapp.beguess.presentation.di

import com.vyapp.beguess.data.network.BeguessApi
import com.vyapp.beguess.data.repository.BeguessRepositoryImp
import com.vyapp.beguess.domain.repository.BeguessRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideBeguessApi(): BeguessApi =
        Retrofit.Builder().baseUrl("https://peaceful-twig-production.up.railway.app/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeguessApi::class.java)

    @Provides
    @Singleton
    fun provideBeguessRepository(api: BeguessApi): BeguessRepository = BeguessRepositoryImp(api)

}