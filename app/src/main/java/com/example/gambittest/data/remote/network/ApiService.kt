package com.example.gambittest.data.remote.network

import com.example.gambittest.data.remote.model.DishDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
	@GET("category/1")
	suspend fun getDishes(
		@Query("page") page: Int = 1,
	): List<DishDto>
}