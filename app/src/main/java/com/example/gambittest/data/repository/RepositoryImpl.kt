package com.example.gambittest.data.repository

import com.example.gambittest.data.local.database.DatabaseFavoritesDao
import com.example.gambittest.data.local.model.FavoritesProduct
import com.example.gambittest.data.remote.model.DishDto
import com.example.gambittest.data.remote.network.ApiService
import com.example.gambittest.domain.model.Dish
import com.example.gambittest.domain.repository.Repository

class RepositoryImpl(private val apiService: ApiService, private val databaseFavoritesDao: DatabaseFavoritesDao):Repository {
	override suspend fun getDishesList(): List<DishDto> = apiService.getDishes()
	override suspend fun getFavoritesProducts(): List<Dish> = databaseFavoritesDao.getProductByFavorites()
	override suspend fun insertFavorite(favoritesProduct: List<Dish>) {
		databaseFavoritesDao.insert(favoritesProduct)
	}

	override suspend fun updateFavorite(favoritesProduct: Dish) {
		databaseFavoritesDao.update(favoritesProduct)
	}


}