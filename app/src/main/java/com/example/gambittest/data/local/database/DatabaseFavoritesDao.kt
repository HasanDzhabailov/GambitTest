package com.example.gambittest.data.local.database

import androidx.room.*
import com.example.gambittest.domain.model.Dish

@Dao
interface DatabaseFavoritesDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(dishList: List<Dish>)

	@Update
	suspend fun update(dish: Dish)

	@Query("SELECT * from favorites_products_table")
	suspend fun getProductByFavorites(): List<Dish>
}