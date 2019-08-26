package com.nwar.individual.dagger.simpleExample

import dagger.Module
import dagger.Provides

@Module
class UserMakerModule() {
    @Provides
    fun providerUser() = User(1000,"이름")
}