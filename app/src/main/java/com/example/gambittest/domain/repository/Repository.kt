package com.example.gambittest.domain.repository

import com.example.gambittest.data.remote.model.DishDto
import retrofit2.Call
import javax.inject.Inject


interface Repository {
	suspend fun getDishesList(): List<DishDto>
}