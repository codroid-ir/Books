package ir.codroid.books.domin.repository

import ir.codroid.books.data.remote.BookDto
import ir.codroid.books.data.util.ResponseResult
import retrofit2.Response

interface BookRepository {

    suspend fun getBooks(): Response<ResponseResult<List<BookDto>>>

    suspend fun getBookById(id: String): Response<ResponseResult<BookDto>>

    suspend fun deleteBookById(id: String): Response<ResponseResult<String>>

    suspend fun addBook(
        title: String,
        author: String,
        genre: String,
        yearPublished: Int,
    ): Response<ResponseResult<String>>

    suspend fun updateBookById(
        title: String? = null,
        author: String? = null,
        genre: String? = null,
        yearPublished: Int? = null,
        checkedOut: Boolean? = null,
    ): Response<ResponseResult<String>>
}