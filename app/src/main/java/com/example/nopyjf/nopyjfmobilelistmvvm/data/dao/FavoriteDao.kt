package com.example.nopyjf.nopyjfmobilelistmvvm.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.OnConflictStrategy
import androidx.room.Insert
import androidx.room.Delete
import com.example.nopyjf.nopyjfmobilelistmvvm.data.model.FavoriteEntity

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    suspend fun getFavorites(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(data: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(data: FavoriteEntity)
}