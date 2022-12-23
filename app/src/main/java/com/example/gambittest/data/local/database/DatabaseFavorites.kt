package com.example.gambittest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gambittest.data.local.model.FavoritesProduct

@Database(entities = [FavoritesProduct::class], version = 1, exportSchema = false)

abstract class DatabaseFavorites:RoomDatabase() {
	abstract fun DatabaseFavoritesDao(): DatabaseFavoritesDao
}