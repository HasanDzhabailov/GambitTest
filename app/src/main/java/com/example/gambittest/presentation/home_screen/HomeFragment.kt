package com.example.gambittest.presentation.home_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.gambittest.R
import com.example.gambittest.common.autoCleared
import com.example.gambittest.databinding.FragmentHomeBinding
import com.example.gambittest.di.Injectable
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeFragment : Fragment(), Injectable {
	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	private lateinit var homeViewModel: HomeViewModel

	private var binding by autoCleared<FragmentHomeBinding>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding = FragmentHomeBinding.inflate(inflater,container,false)
		homeViewModel = ViewModelProvider(this,viewModelFactory)[HomeViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)


		lifecycleScope.launch {
			homeViewModel.homeStateFlow.collectLatest {
				Log.d("Balat111", it.success.toString())
			}
		}
	}


}