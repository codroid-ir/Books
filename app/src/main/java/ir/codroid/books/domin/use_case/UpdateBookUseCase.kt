package ir.codroid.books.domin.use_case

import ir.codroid.books.data.remote.model.ActionsDto
import ir.codroid.books.data.util.NetworkResult
import ir.codroid.books.domin.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateBookUseCase @Inject constructor(
    private val repository: BookRepository,
) {
    operator fun invoke(
        id: String,
        title: String ,
        author: String ,
        genre: String ,
        yearPublished: Int ,
    ): Flow<NetworkResult<ActionsDto>> = flow {
        emit(NetworkResult.Loading())
        val message = repository.updateBookById(
            id = id,
            title = title,
            author = author,
            genre = genre,
            yearPublished = yearPublished,
        )
        emit(message)
    }
}