package com.example.gambittest.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gambittest.common.ResourceState
import com.example.gambittest.common.ResponseNetwork
import com.example.gambittest.domain.model.Dish
import com.example.gambittest.domain.use_case.GetHomeProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getHomeProductsUseCase: GetHomeProductsUseCase) : ViewModel() {
	private val _homeStateFlow = MutableStateFlow(ResourceState<List<Dish>>())
	val homeStateFlow = _homeStateFlow.asStateFlow()

	init {
		getHomeProducts()
	}
	private fun getHomeProducts() {
		getHomeProductsUseCase().onEach { result ->
			when (result) {
				is ResponseNetwork.Success -> {
					_homeStateFlow.value = ResourceState(success = result.data)
				}
				is ResponseNetwork.Error -> {
					_homeStateFlow.value = ResourceState(error = result.message
						?: "An unexpected error occurred while getting all products")
				}
				is ResponseNetwork.Loading -> {
					_homeStateFlow.value = ResourceState(loading = true)
				}
			}
		}.launchIn(viewModelScope)
	}
}