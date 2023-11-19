package ir.codroid.books.presentation.book_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.codroid.books.data.remote.toBook
import ir.codroid.books.data.util.NetworkResult
import ir.codroid.books.domin.use_case.DeleteBookUseCase
import ir.codroid.books.domin.use_case.GetBooksUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val deleteBookUseCase: DeleteBookUseCase,
) : ViewModel(), BookListContract {

    var state by mutableStateOf(BookListContract.State())
        private set

    init {
        getBooksList()
    }

    fun event(event: BookListContract.Event) {
        when (event) {
            is BookListContract.Event.OnDelete -> deleteBookById(event.bookId)
            is BookListContract.Event.OnGetBookList -> getBooksList()
            BookListContract.Event.OnRefresh -> getBooksList()
            BookListContract.Event.onClose -> {
                if (state.searchText.isEmpty()) {
                    state = state.copy(searchText = "", showSearch = false)
                    getBooksList()
                } else {
                    state = state.copy(searchText = "")
                }
            }

            BookListContract.Event.onSearch -> {
                state = state.copy(showSearch = true)
            }

            is BookListContract.Event.OnSearchTextChanged -> {
             state = state.copy(searchText = event.searchText)
            getBooksList(event.searchText)
            }
        }
    }

    private fun getBooksList(searchText: String = "") {
        viewModelScope.launch {
            getBooksUseCase.invoke().collect { result ->
                when (result) {
                    is NetworkResult.Error -> {
                        state =
                            state.copy(error = result.message, isLoading = false, books = null)
                    }

                    is NetworkResult.Loading -> {
                        state = state.copy(isLoading = true)
                    }

                    is NetworkResult.Success -> {
                        state =
                            state.copy(
                                books = result.data
                                    ?.map { it.toBook() }
                                    ?.filter { it.title.contains(searchText) },
                                isLoading = false,
                                error = null
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
                            showSnakeBar = true,
                            deleteMessage = result.message
                        )
                    }

                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        state = state.copy(
                            showSnakeBar = true,
                            deleteMessage = result.data!!.message,
                            deletedBookId = id
                        )
                        getBooksList()
                    }
                }
            }
        }
    }

    fun setShowDialogValue(boolean: Boolean) {
        state = state.copy(
            showSnakeBar = boolean
        )
    }
}