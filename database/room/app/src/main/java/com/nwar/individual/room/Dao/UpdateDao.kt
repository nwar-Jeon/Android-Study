package com.nwar.individual.room.Dao

import androidx.room.Dao
import androidx.room.RoomDatabase
import androidx.room.Update
import com.nwar.individual.room.Entity.FirstEntity

@Dao
interface UpdateDao {
    @Update
    fun update(vararg items : FirstEntity)
}