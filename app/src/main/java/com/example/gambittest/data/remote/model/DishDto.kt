package com.example.gambittest.data.remote.model

import androidx.lifecycle.Transformations.map
import com.example.gambittest.domain.model.Dish
import retrofit2.Call


data class DishDto(
	val id: Int,
	val name: String,
	val image: String,
	val price: Int,
	var isFavorite:Boolean = false
)
	fun DishDto.toMapper(): Dish = Dish(
		id = id,
		name = name,
		image = image,
		price = price
	)
fun List<DishDto>.toMapper() =  map { it.toMapper() }


