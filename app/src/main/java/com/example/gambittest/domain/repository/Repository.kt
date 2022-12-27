package com.example.gambittest.domain.repository

import com.example.gambittest.data.remote.model.DishDto
import com.example.gambittest.domain.model.Dish

interface Repository {
	suspend fun getDishesList(): List<DishDto>

	suspend fun getFavoritesProducts(): List<Dish>

	suspend fun insertFavorite(favoritesProduct: List<Dish>)

	suspend fun updateFavorite(favoritesProduct: Dish)
}