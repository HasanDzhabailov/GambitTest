package com.example.gambittest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_products_table")
data class Dish(
	@PrimaryKey(autoGenerate = false) var id: Int,
	val name: String,
	val image: String,
	val price: Int,
	var isFavorite: Boolean = false,
)