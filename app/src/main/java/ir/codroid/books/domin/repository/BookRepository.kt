package ir.codroid.books.domin.repository

import ir.codroid.books.data.remote.BookDto
import ir.codroid.books.data.util.NetworkResult

interface BookRepository {

    suspend fun getBooks(): NetworkResult<List<BookDto>>

    suspend fun getBookById(id: String): NetworkResult<BookDto>

    suspend fun deleteBookById(id: String): NetworkResult<String>

    suspend fun addBook(
        title: String,
        author: String,
        genre: String,
        yearPublished: Int,
    ): NetworkResult<String>

    suspend fun updateBookById(
        id: String,
        title: String? = null,
        author: String? = null,
        genre: String? = null,
        yearPublished: Int? = null,
        checkedOut: Boolean? = null,
    ): NetworkResult<String>
}