package ir.codroid.books.data.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface BookApi {

    @GET("/books")
    suspend fun getBooks(): List<BookDto>

    @GET("/books/{id}")
    suspend fun getBookById(@Path("id") id: String): BookDto

    @DELETE("/books/{id}")
    suspend fun deleteBookById(@Path("id") id: String): String

    @POST("/books")
    suspend fun addBook(
        @Body title: String,
        @Body author: String,
        @Body genre: String,
        @Body yearPublished: Int,
    ): String

    @PATCH("/books/{id}")
    suspend fun updateBookById(
        @Body title: String? = null,
        @Body author: String? = null,
        @Body genre: String? = null,
        @Body yearPublished: Int? = null,
        @Body checkedOut: Boolean? = null,
    ): String

}