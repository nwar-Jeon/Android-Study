package com.nwar.individual.room.Entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(foreignKeys = arrayOf(
    ForeignKey(
        entity = SecondEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("foreign_id")
    )
))
data class ForeignEntity(
    val foreign_id : Int,
    val name : String
)