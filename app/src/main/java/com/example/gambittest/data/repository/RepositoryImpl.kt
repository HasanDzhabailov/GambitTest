package com.example.gambittest.data.repository

import com.example.gambittest.data.remote.model.DishDto
import com.example.gambittest.data.remote.network.ApiService
import com.example.gambittest.domain.repository.Repository
import retrofit2.Call

class RepositoryImpl(private val repositoryImpl: ApiService):Repository {
	override suspend fun getDishesList(): List<DishDto> = repositoryImpl.getDishes()
}