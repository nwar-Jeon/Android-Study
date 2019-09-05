package com.nwar.individual.clean_architecture_study1.di

import androidx.lifecycle.ViewModelProvider
import com.nwar.individual.clean_architecture_study1.presenter.userInterface.MainActivity
import dagger.Component

@Component(modules = [
    MainModule::class
])
interface MainComponent{
    fun inject(obj : MainActivity)
    fun testInject() : ViewModelProvider.Factory
}