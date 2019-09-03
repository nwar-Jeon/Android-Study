package com.nwar.individual.dagger.simpleExample

data class User(
    val id : Int,
    val name : String
) {
    override fun toString() = "$id : $name"
}