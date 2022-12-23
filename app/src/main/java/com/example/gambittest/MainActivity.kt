package com.example.gambittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gambittest.data.remote.model.DishDto
import com.example.gambittest.data.remote.model.toMapper
import com.example.gambittest.domain.model.Dish
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {
	@Inject
	lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}