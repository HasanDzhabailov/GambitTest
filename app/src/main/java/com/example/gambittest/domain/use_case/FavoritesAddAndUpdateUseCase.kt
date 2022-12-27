package com.example.gambittest.domain.use_case

import com.example.gambittest.domain.model.Dish
import com.example.gambittest.domain.repository.Repository
import javax.inject.Inject

class FavoritesAddAndUpdateUseCase @Inject constructor(private val repository: Repository) {
	suspend fun addDishList(favoritesProduct: List<Dish>) {
		repository.insertFavorite(favoritesProduct)
	}

	suspend fun updateFavoritesProduct(favoritesProduct: Dish) {
		repository.updateFavorite(favoritesProduct)
	}
}