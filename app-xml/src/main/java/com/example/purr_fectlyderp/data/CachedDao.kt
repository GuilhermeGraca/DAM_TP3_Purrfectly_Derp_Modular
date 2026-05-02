package com.example.purr_fectlyderp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CachedDao {
    @Query("SELECT * FROM cached_derps ORDER BY timestamp DESC")
    fun getAllCached(): List<CachedDerp>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cachedDerps: List<CachedDerp>)

    @Query("SELECT COUNT(*) FROM cached_derps")
    fun getCount(): Int

    @Query("DELETE FROM cached_derps WHERE id NOT IN (SELECT id FROM cached_derps ORDER BY timestamp DESC LIMIT 50)")
    fun deleteOldestBeyondLimit()
}
