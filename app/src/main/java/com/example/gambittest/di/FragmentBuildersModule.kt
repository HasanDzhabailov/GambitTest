package com.example.gambittest.di

import com.example.gambittest.presentation.home_screen.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
	@ContributesAndroidInjector
	abstract fun contributeHomeFragmentFragment(): HomeFragment
}