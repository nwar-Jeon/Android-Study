package com.nwar.individual.room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FirstEntity(
    @PrimaryKey
    val id : Int,

    val name : String
)