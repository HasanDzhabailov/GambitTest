package com.example.gambittest.common

data class ResourceState<DataSource>(
	val loading: Boolean = false,
	val success: DataSource? = null,
	val error: String = "",
)
