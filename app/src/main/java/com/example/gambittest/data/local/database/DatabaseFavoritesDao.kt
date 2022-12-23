package com.example.gambittest.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.gambittest.data.local.model.FavoritesProduct

@Dao
interface DatabaseFavoritesDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(favorites: FavoritesProduct)

}