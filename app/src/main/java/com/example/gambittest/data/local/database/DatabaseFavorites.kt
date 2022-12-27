package com.example.gambittest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gambittest.data.local.model.FavoritesProduct
import com.example.gambittest.domain.model.Dish

@Database(entities = [Dish::class], version = 1, exportSchema = false)

abstract class DatabaseFavorites:RoomDatabase() {
	abstract fun DatabaseFavoritesDao(): DatabaseFavoritesDao
}