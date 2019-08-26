package com.nwar.individual.dagger.simpleExample

fun run() : UserMaker{
    val userMaker = UserMaker()
    DaggerUserComponent.create().inject(userMaker)
    return userMaker
}