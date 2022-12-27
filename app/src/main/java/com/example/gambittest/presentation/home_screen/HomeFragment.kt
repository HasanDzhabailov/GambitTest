package com.example.gambittest.presentation.home_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gambittest.common.autoCleared
import com.example.gambittest.databinding.FragmentHomeBinding
import com.example.gambittest.di.Injectable
import com.example.gambittest.domain.model.Dish
import com.example.gambittest.presentation.adapters.Adapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeFragment : Fragment(), Injectable, Adapter.SwipeFavoritesListener {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory
	private lateinit var homeViewModel: HomeViewModel
	private lateinit var adapter: Adapter
	private var binding by autoCleared<FragmentHomeBinding>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding = FragmentHomeBinding.inflate(inflater, container, false)
		homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
		adapter = Adapter(requireContext(), this)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		lifecycleScope.launch {
			homeViewModel.homeStateFlow.collectLatest {
				it.success?.let { items ->
					homeViewModel.addDishList(items)
				}
			}
		}

		lifecycleScope.launch {
			homeViewModel.getFavoritesProducts().collectLatest {
				adapter.submitList(it)
				binding.listOfDishes.adapter = adapter
				binding.listOfDishes.layoutManager =
					LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
			}
		}
	}

	override fun onClick(dish: Dish, statusClick: Boolean) {
		dish.isFavorite = statusClick
		this.homeViewModel.updateFavorites(dish)

	}

}