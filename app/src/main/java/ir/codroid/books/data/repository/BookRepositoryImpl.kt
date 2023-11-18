package ir.codroid.books.data.repository

import ir.codroid.books.data.remote.BookApi
import ir.codroid.books.data.remote.BookDto
import ir.codroid.books.data.util.BaseApiResponse
import ir.codroid.books.data.util.NetworkResult
import ir.codroid.books.domin.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi,
) : BaseApiResponse(), BookRepository {
    override suspend fun getBooks(): NetworkResult<List<BookDto>> = safeApiCall { api.getBooks() }

    override suspend fun getBookById(id: String): NetworkResult<BookDto> =
        safeApiCall { api.getBookById(id) }


    override suspend fun deleteBookById(id: String): NetworkResult<String> =
        safeApiCall { api.deleteBookById(id) }

    override suspend fun addBook(
        title: String,
        author: String,
        genre: String,
        yearPublished: Int,
    ): NetworkResult<String> = safeApiCall {
        api.addBook(title = title, author = author, genre = genre, yearPublished = yearPublished)
    }


    override suspend fun updateBookById(
        id: String,
        title: String?,
        author: String?,
        genre: String?,
        yearPublished: Int?,
        checkedOut: Boolean?,
    ): NetworkResult<String> = safeApiCall {
        api.updateBookById(
            id = id,
            title = title,
            author = author,
            genre = genre,
            yearPublished = yearPublished,
            checkedOut = checkedOut
        )
    }
}