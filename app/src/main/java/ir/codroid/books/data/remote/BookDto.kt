package ir.codroid.books.data.remote

import com.google.gson.annotations.SerializedName
import ir.codroid.books.domin.model.Book

data class BookDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("yearPublished")
    val yearPublished: Int,
    @SerializedName("checkedOut")
    val checkedOut: String,
    @SerializedName("createdAt")
    val createdAt: String,
)

fun BookDto.toBook(): Book = Book(
    title = title,
    author = author,
    id = id,
    genre = genre,
    yearPublished = yearPublished
)