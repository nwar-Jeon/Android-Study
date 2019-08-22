package com.nwar.individual.room.Entity

import android.graphics.Bitmap
import androidx.room.*

@Entity (tableName = "second", primaryKeys = arrayOf("id","name"))
data class SecondEntity (
    @ColumnInfo(name = "id")
    val _id : Int,
    @ColumnInfo(name = "name")
    val _name : String,
    @Ignore
    val picture : Bitmap
)

