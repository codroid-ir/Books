package ir.codroid.books.data.repository

import ir.codroid.books.data.remote.BookApi
import ir.codroid.books.data.remote.BookDto
import ir.codroid.books.data.util.ResponseResult
import ir.codroid.books.domin.repository.BookRepository
import retrofit2.Response
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi,
) : BookRepository {
    override suspend fun getBooks(): Response<ResponseResult<List<BookDto>>> = api.getBooks()

    override suspend fun getBookById(id: String): Response<ResponseResult<BookDto>> = api.getBookById(id)


    override suspend fun deleteBookById(id: String): Response<ResponseResult<String>> = api.deleteBookById(id)

    override suspend fun addBook(
        title: String,
        author: String,
        genre: String,
        yearPublished: Int,
    ): Response<ResponseResult<String>> =
        api.addBook(title = title, author = author, genre = genre, yearPublished = yearPublished)

    override suspend fun updateBookById(
        title: String?,
        author: String?,
        genre: String?,
        yearPublished: Int?,
        checkedOut: Boolean?,
    ): Response<ResponseResult<String>> = api.updateBookById(
        title = title,
        author = author,
        genre = genre,
        yearPublished = yearPublished,
        checkedOut = checkedOut
    )
}