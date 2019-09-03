package com.nwar.individual.clean_architecture_study1.di

import com.nwar.individual.clean_architecture_study1.domain.useCase.MainUseCase
import dagger.Component

@Component(modules = arrayOf(
    MainModule::class
))
interface MainComponent{
    fun inject(useCase : MainUseCase)
}