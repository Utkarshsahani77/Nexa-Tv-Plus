package com.nexatv.app.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nexatv.app.data.model.Addon

@Dao
interface AddonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(addon: Addon)

    @Query("SELECT * FROM addons")
    suspend fun getAll(): List<Addon>

    @Query("SELECT * FROM addons WHERE id = :id")
    suspend fun getById(id: String): Addon?

    @Delete
    suspend fun delete(addon: Addon)

    @Query("DELETE FROM addons WHERE id = :id")
    suspend fun deleteById(id: String)
}
