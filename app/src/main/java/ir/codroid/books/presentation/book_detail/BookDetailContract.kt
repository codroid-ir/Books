package ir.codroid.books.presentation.book_detail

import ir.codroid.books.domin.model.Book

interface BookDetailContract {

    data class State(
        val book: Book? = null,
        val isLoading: Boolean = false,
        val error: String? = null,
        val showDialog: Boolean = false,
        val actionMessage: String? = null,
        val actionTitle: String? = null,
        val bookName: String = "",
        val author: String = "",
        val genre: String = "",
        val yearPublished : String = "",
    )

    sealed class Event {
        data class onBookNameChanged(val bookName: String) : Event()
        data class onAuthorChanged(val author: String) : Event()
        data class onGenreChanged(val genre: String) : Event()
        data class onYearPublishedChanged(val yearPublished: String) : Event()
        data class OnDelete(val bookId: String) : Event()
        data class OnAdd(val book: Book) : Event()
        data class OnUpdate(val book: Book) : Event()
        data class OnGetBook(val bookId: String) : Event()
    }
}