package ir.codroid.books.domin.use_case

import ir.codroid.books.data.remote.ActionsDto
import ir.codroid.books.data.util.NetworkResult
import ir.codroid.books.domin.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteBookUseCase @Inject constructor(
    private val repository: BookRepository,
) {
    operator fun invoke(id: String): Flow<NetworkResult<ActionsDto>> = flow {
        emit(NetworkResult.Loading())
        val message = repository.deleteBookById(id)
        emit(message)
    }
}