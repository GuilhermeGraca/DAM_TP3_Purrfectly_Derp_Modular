package com.example.purr_fectlyderp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites ORDER BY timestamp DESC")
    fun getAllFavorites(): Flow<List<FavoriteDerp>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: FavoriteDerp)

    @Query("SELECT COUNT(*) FROM favorites")
    fun getCount(): Int

    @Query("DELETE FROM favorites WHERE id = (SELECT id FROM favorites ORDER BY timestamp ASC LIMIT 1)")
    fun deleteOldestFavorite()
}
