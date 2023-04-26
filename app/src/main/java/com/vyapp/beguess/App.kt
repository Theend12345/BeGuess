package com.vyapp.beguess

import android.app.Application
import com.vyapp.beguess.presentation.di.AppComponent
import com.vyapp.beguess.presentation.di.DaggerAppComponent

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }

}