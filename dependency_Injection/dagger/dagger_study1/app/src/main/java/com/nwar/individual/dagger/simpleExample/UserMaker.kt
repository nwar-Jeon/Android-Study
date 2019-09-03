package com.nwar.individual.dagger.simpleExample

import javax.inject.Inject

class UserMaker() {
    @Inject
    lateinit var user : User
}