package com.nwar.individual.dagger.simpleExample

import dagger.Component

@Component(modules = arrayOf(UserMakerModule::class))
interface UserComponent {
    //fun make() : UserMaker
    fun inject(userMaker : UserMaker)
}