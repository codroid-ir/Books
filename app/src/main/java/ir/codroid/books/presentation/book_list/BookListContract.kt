package ir.codroid.books.presentation.book_list

import ir.codroid.books.domin.model.Book


interface BookListContract {

    data class State(
        val refreshing: Boolean = false,
        val books: List<Book>? = null,
        val isLoading: Boolean = false,
        val error: String? = null,
        val showDialog: Boolean = false,
        val deleteMessage: String? = null,
        val deletedBookId: String? = null,
        val showSearch :Boolean = false,
        val searchText: String = ""
    )

    sealed class Event {
        data class OnDelete(val bookId: String) : Event()
        data class OnSearchTextChanged(val searchText: String) : Event()
        data object OnGetBookList : Event()
        data object OnRefresh : Event()
        data object onSearch : Event()
        data object onClose : Event()
    }
}