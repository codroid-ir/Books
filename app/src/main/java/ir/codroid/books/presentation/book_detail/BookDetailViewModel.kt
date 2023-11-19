package ir.codroid.books.presentation.book_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.codroid.books.data.remote.model.toBook
import ir.codroid.books.data.util.NetworkResult
import ir.codroid.books.domin.model.Book
import ir.codroid.books.domin.use_case.AddBookUseCase
import ir.codroid.books.domin.use_case.DeleteBookUseCase
import ir.codroid.books.domin.use_case.GetBookUseCase
import ir.codroid.books.domin.use_case.UpdateBookUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBooksUseCase: GetBookUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
    private val updateBookUseCase: UpdateBookUseCase,
    private val addBookUseCase: AddBookUseCase,
) : ViewModel() {

    var state by mutableStateOf(BookDetailContract.State())
        private set


    fun event(event: BookDetailContract.Event) {
        when (event) {
            is BookDetailContract.Event.OnAdd -> {
                addBook(event.book)
            }

            is BookDetailContract.Event.OnDelete -> {
                deleteBookById(event.bookId)
            }

            is BookDetailContract.Event.OnGetBook -> {
                getBookDetail(event.bookId)
            }

            is BookDetailContract.Event.OnUpdate -> {
                updateBook(event.book)
            }

            is BookDetailContract.Event.onAuthorChanged -> {
                state = state.copy(author = event.author)
            }

            is BookDetailContract.Event.onBookNameChanged -> {
                state = state.copy(bookName = event.bookName)
            }

            is BookDetailContract.Event.onGenreChanged -> {
                state = state.copy(genre = event.genre)
            }

            is BookDetailContract.Event.onYearPublishedChanged -> {
                state = state.copy(yearPublished = event.yearPublished)
            }
        }
    }

    private fun getBookDetail(bookId: String) {
        viewModelScope.launch {
            getBooksUseCase.invoke(bookId).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        state =
                            state.copy(error = result.message, isLoading = false, book = null)
                    }

                    is NetworkResult.Loading -> {
                        state = state.copy(isLoading = true, error = null, book = null)
                    }

                    is NetworkResult.Success -> {
                        state =
                            state.copy(
                                book = result.data?.toBook(),
                                isLoading = false,
                                error = null,
                                bookName = result.data?.toBook()?.title ?: "",
                                author = result.data?.toBook()?.author ?: "",
                                genre = result.data?.toBook()?.genre ?: "",
                                yearPublished = result.data?.toBook()?.yearPublished.toString()
                            )
                    }
                }
            }
        }
    }

    private fun deleteBookById(id: String) {
        viewModelScope.launch {
            deleteBookUseCase.invoke(id).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        state = state.copy(
                            showDialog = true,
                            actionTitle = "Delete",
                            actionMessage = result.message
                        )
                    }

                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        state = state.copy(
                            showDialog = true,
                            actionMessage = result.data!!.message,
                            actionTitle = "Delete",
                        )
                    }
                }
            }
        }
    }

    private fun updateBook(book: Book) {
        viewModelScope.launch {
            updateBookUseCase.invoke(
                id = book.id,
                title = book.title,
                author = book.author,
                genre = book.genre,
                yearPublished = book.yearPublished

            ).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        state = state.copy(
                            showDialog = true,
                            actionTitle = "Update",
                            actionMessage = result.message
                        )
                    }

                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        state = state.copy(
                            showDialog = true,
                            actionMessage = result.data!!.message,
                            actionTitle = "Update",
                        )
                    }
                }
            }
        }
    }

    private fun addBook(book: Book) {
        viewModelScope.launch {
            addBookUseCase.invoke(
                title = book.title,
                author = book.author,
                genre = book.genre,
                yearPublished = book.yearPublished

            ).collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        state = state.copy(
                            showDialog = true,
                            actionTitle = "Add",
                            actionMessage = result.message
                        )
                    }

                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        state = state.copy(
                            showDialog = true,
                            actionMessage = result.data!!.message,
                            actionTitle = "Add",
                        )
                    }
                }
            }
        }
    }

    fun setShowDialogValue(boolean: Boolean) {
        state = state.copy(
            showDialog = boolean,
            actionMessage = "",
            actionTitle = ""
        )
    }
}