package ir.codroid.books.domin.use_case

import ir.codroid.books.data.remote.BookDto
import ir.codroid.books.data.util.NetworkResult
import ir.codroid.books.domin.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository,
) {
    operator fun invoke(): Flow<NetworkResult<List<BookDto>>> = flow {
        emit(NetworkResult.Loading())
        val books = repository.getBooks()
        emit(books)
    }
}