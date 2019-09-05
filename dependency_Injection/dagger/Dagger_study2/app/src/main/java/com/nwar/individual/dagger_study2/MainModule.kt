package com.nwar.individual.dagger_study2

import dagger.Module
import dagger.Provides

@Module
class MainModule() {
    @Provides
    fun provide() = "aaa"
}