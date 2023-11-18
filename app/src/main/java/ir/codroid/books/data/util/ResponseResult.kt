package ir.codroid.books.data.util

data class ResponseResult<T>(
    val data: T,
    val message: String,
    val status: String,
)
