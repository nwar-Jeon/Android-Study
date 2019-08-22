package com.nwar.individual.room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.nwar.individual.room.Entity.FirstEntity
import com.nwar.individual.room.Entity.SecondEntity

@Dao
interface QueryDao {
    @Query("SELECT * FROM FIRSTENTITY")
    fun loadAll() : List<FirstEntity>
    @Query("SELECT * FROM FIRSTENTITY WHERE id > 100")
    fun loadAllById() : List<IdOnly>
    @Query("Select * FROM firstentity WHERE id IN (:ids)")
    fun loadFromRegions (ids : List<Int>) : LiveData<List<FirstEntity>>
    @Query("SELECT * FROM ForeignEntity" +
            "INNER JOIN second ON second.id = ForeignEntity.id"
            + "WHERE id > 100")
    fun loadJoin() : List<SecondEntity>
}

data class IdOnly(
    val id : Int
)