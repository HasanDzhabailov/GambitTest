package com.example.gambittest.common

sealed class ResponseNetwork<T>(val data: T? = null, val message: String? = null) {
	class Success<T>(data: T?) : ResponseNetwork<T>(data)
	class Error<T>(message: String, data: T? = null) : ResponseNetwork<T>(data, message)
	class Loading<T>(data: T? = null) : ResponseNetwork<T>(data)
}