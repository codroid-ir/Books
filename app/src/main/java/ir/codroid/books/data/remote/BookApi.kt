package ir.codroid.books.data.remote

import ir.codroid.books.data.remote.model.ActionsDto
import ir.codroid.books.data.remote.model.BookDto
import ir.codroid.books.data.remote.model.BookParameter
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface BookApi {

    @GET("books")
    suspend fun getBooks(): Response<List<BookDto>>

    @GET("/books/{id}")
    suspend fun getBookById(@Path("id") id: String): Response<BookDto>

    @DELETE("/books/{id}")
    suspend fun deleteBookById(@Path("id") id: String): Response<ActionsDto>

    @POST("/books")
    suspend fun addBook(
        @Body bookParameter: BookParameter,
    ): Response<ActionsDto>

    @PATCH("/books/{id}")
    suspend fun updateBookById(
        @Path("id") id: String,
        @Body bookParameter: BookParameter,
    ): Response<ActionsDto>

}