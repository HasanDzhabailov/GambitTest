package com.example.gambittest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gambittest.common.ViewModelFactory
import com.example.gambittest.data.remote.model.DishDto
import com.example.gambittest.domain.repository.Repository
import com.example.gambittest.domain.use_case.GetHomeProductsUseCase_Factory
import com.example.gambittest.presentation.home_screen.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
	@Binds
	@IntoMap
	@ViewModelKey(HomeViewModel::class)
	abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel


	@Binds
	abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}