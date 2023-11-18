package ir.codroid.books.domin.use_case

import ir.codroid.books.data.util.NetworkResult
import ir.codroid.books.domin.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddBookUseCase @Inject constructor(
    private val repository: BookRepository,
) {
    operator fun invoke(
        title: String,
        author: String,
        genre: String,
        yearPublished: Int,
    ): Flow<NetworkResult<String>> = flow {
        emit(NetworkResult.Loading())
        val message = repository.addBook(
            title = title,
            author = author,
            genre = genre,
            yearPublished = yearPublished,
        )
        emit(message)
    }
}