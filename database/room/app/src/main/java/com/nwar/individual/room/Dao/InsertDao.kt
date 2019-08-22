package com.nwar.individual.room.Dao

import androidx.room.Insert
import com.nwar.individual.room.Entity.FirstEntity

interface InsertDao {
    @Insert
    fun insertItemsToFirstEntity(vararg items : FirstEntity)

    @Insert
    fun insertTwoItemToFirstEntity(item1 : FirstEntity, item2 : FirstEntity)

    @Insert
    fun insertItemAndItemList(item : FirstEntity, items : List<FirstEntity>)
}