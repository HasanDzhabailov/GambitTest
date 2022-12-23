package com.example.gambittest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_products_table")
data class FavoritesProduct(
	@PrimaryKey(autoGenerate = false) var id: String,
	val favorites: Boolean,
)