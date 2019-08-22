package com.nwar.individual.room.Dao

import androidx.room.Dao
import androidx.room.Delete
import com.nwar.individual.room.Entity.FirstEntity

@Dao
interface DeleteDao {
    @Delete
    fun delete(vararg items : FirstEntity)
}