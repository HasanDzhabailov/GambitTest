package com.example.gambittest.domain.use_case

import com.example.gambittest.common.ResponseNetwork
import com.example.gambittest.data.remote.model.toMapper

import com.example.gambittest.domain.model.Dish

import com.example.gambittest.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHomeProductsUseCase @Inject constructor (private val repository: Repository) {
	operator fun invoke(): Flow<ResponseNetwork<List<Dish>>> = flow {
		try {
			emit(ResponseNetwork.Loading())

			val items = repository.getDishesList().toMapper()
			emit(ResponseNetwork.Success(items))
		} catch (e: HttpException) {
			emit(ResponseNetwork.Error(e.localizedMessage ?: "An unexpected error occurred"))
		} catch (e: IOException) {
			emit(ResponseNetwork.Error("Couldn't reach server. Check your internet connection."))
		}
	}
}