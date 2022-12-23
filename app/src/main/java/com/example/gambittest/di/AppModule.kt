package com.example.gambittest.di

import android.app.Application
import androidx.room.Room
import com.example.gambittest.data.local.database.DatabaseFavorites
import com.example.gambittest.data.local.database.DatabaseFavoritesDao
import com.example.gambittest.data.remote.model.DishDto

import com.example.gambittest.data.remote.network.ApiService
import com.example.gambittest.data.repository.RepositoryImpl
import com.example.gambittest.domain.repository.Repository
import com.example.gambittest.domain.use_case.GetHomeProductsUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
	@Singleton
	@Provides
	fun provideDb(app: Application): DatabaseFavorites {
		return Room.databaseBuilder(app, DatabaseFavorites::class.java, "favorites_products_table")
			.fallbackToDestructiveMigration()
			.build()
	}

	@Singleton
	@Provides
	fun provideFavoritesDao(db: DatabaseFavorites): DatabaseFavoritesDao {
		return db.DatabaseFavoritesDao()
	}

	private val contentType =  "application/json".toMediaType()
	@OptIn(ExperimentalSerializationApi::class)
	@Singleton
	@Provides

	fun provideRetrofit(gson: Gson): Retrofit =
		Retrofit.Builder()
			.baseUrl("https://api.gambit-app.ru/")
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build()
	@Provides fun provideGson(): Gson = GsonBuilder().create()
	@Provides
	fun provideUsersService(retrofit: Retrofit): ApiService =
		retrofit.create(ApiService::class.java)

	@Singleton
	@Provides
	fun provideRepositoryImpl(apiService: ApiService):Repository = RepositoryImpl(apiService)





}