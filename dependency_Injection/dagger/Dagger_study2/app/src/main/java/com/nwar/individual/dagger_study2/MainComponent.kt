package com.nwar.individual.dagger_study2

import dagger.Component

@Component(modules = [MainModule::class])
interface MainComponent {
    fun getString() : String
}