package com.example.gambittest.domain.use_case

import com.example.gambittest.data.local.model.FavoritesProduct
import com.example.gambittest.domain.model.Dish
import com.example.gambittest.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.sql.SQLException
import javax.inject.Inject

class GetFavoritesProductsUseCase @Inject constructor(private val repository: Repository) {
	operator fun invoke(): Flow<List<Dish>> = flow {
		try {
			val items = repository.getFavoritesProducts()
			emit(items)
		}
		catch (ex:SQLException){
			ex.message
		}
	}
}