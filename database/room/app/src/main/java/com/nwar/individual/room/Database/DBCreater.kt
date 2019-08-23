package com.nwar.individual.room.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nwar.individual.room.Entity.FirstEntity
import com.nwar.individual.room.Entity.ForeignEntity

@Database(version = 1, entities = arrayOf(
    ForeignEntity::class,
    FirstEntity::class
))
abstract class DBCreater() : RoomDatabase() {
    
}