package com.nwar.individual.dagger_study2

import android.app.Application

class AppApplication() : Application() {
    fun buildComponent() : MainComponent = DaggerMainComponent.builder().build()
}