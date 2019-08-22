package com.nwar.individual.room.Dao

import androidx.room.TypeConverter
import java.util.*

class TypeConverter() {
    @TypeConverter
    fun fromTimeStamp(value : Long?) = value?.let {
        Date(it)
    }

    @TypeConverter
    fun dateToTimeStamp(date : Date?) = date?.time
}