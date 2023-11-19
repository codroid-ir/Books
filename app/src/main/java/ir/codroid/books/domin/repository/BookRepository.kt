package ir.codroid.books.domin.repository

import ir.codroid.books.data.remote.model.ActionsDto
import ir.codroid.books.data.remote.model.BookDto
import ir.codroid.books.data.util.NetworkResult

interface BookRepository {

    suspend fun getBooks(): NetworkResult<List<BookDto>>

    suspend fun getBookById(id: String): NetworkResult<BookDto>

    suspend fun deleteBookById(id: String): NetworkResult<ActionsDto>

    suspend fun addBook(
        title: String,
        author: String,
        genre: String,
        yearPublished: Int,
    ): NetworkResult<ActionsDto>

    suspend fun updateBookById(
        id: String,
        title: String,
        author: String,
        genre: String,
        yearPublished: Int,
    ): NetworkResult<ActionsDto>
}