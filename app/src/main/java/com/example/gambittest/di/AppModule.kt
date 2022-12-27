package com.example.gambittest.di

import android.app.Application
import androidx.room.Room
import com.example.gambittest.data.local.database.DatabaseFavorites
import com.example.gambittest.data.local.database.DatabaseFavoritesDao

import com.example.gambittest.data.remote.network.ApiService
import com.example.gambittest.data.repository.RepositoryImpl
import com.example.gambittest.domain.repository.Repository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
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

	@Singleton
	@Provides

	fun provideRetrofit(gson: Gson): Retrofit =
		Retrofit.Builder()
			.baseUrl("https://api.gambit-app.ru/")
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build()

	@Provides
	fun provideGson(): Gson = GsonBuilder().create()

	@Provides
	fun provideUsersService(retrofit: Retrofit): ApiService =
		retrofit.create(ApiService::class.java)

	@Singleton
	@Provides
	fun provideRepositoryImpl(apiService: ApiService, dao: DatabaseFavoritesDao): Repository =
		RepositoryImpl(apiService, dao)
}