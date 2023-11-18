package ir.codroid.books.data.util

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T, message: String) : NetworkResult<T>(data, message)
    class Error<T>(data: T? = null, message: String) : NetworkResult<T>(data, message)
    class Loading<T> : NetworkResult<T>()
}