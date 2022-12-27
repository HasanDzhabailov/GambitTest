package com.example.gambittest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gambittest.data.remote.model.DishDto
import com.example.gambittest.data.remote.model.toMapper
import com.example.gambittest.domain.model.Dish

@Entity(tableName = "favorites_products_table")
data class FavoritesProduct(
	@PrimaryKey(autoGenerate = false) var id: Int,
	val name: String,
	val image: String,
	val price: Int,
	var isFavorite:Boolean = false
)
fun FavoritesProduct.toMapper(): Dish= Dish(
	id = id,
	name = name,
	image = image,
	price = price
)
fun List<FavoritesProduct>.toMapper() =  map { it.toMapper() }