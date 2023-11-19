package ir.codroid.books.presentation.book_detail

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import ir.codroid.books.R
import ir.codroid.books.domin.model.Book
import ir.codroid.books.navigation.Screen
import ir.codroid.books.presentation.book_detail.component.BookDetailAppbar
import ir.codroid.books.presentation.book_detail.component.BookDetailContent
import ir.codroid.books.presentation.ui.component.DisplayAlertDialog


@Composable
fun BookDetailRoute(
    viewModel: BookDetailViewModel = hiltViewModel(),
    navController: NavHostController,
    bookId: String,
) {
    val context = LocalContext.current
    if (bookId.isNotEmpty())
        LaunchedEffect(key1 = bookId) {
            viewModel.event(BookDetailContract.Event.OnGetBook(bookId = bookId))
        }
    BookDetailScreen(state = viewModel.state,
        onBackClick = {
            navController.navigate(route = Screen.BookList.route){
                popUpTo(Screen.BookList.route){
                    inclusive = true
                }
            }
        },
        onAddClick = {
            if (viewModel.state.bookName.isEmpty() ||
                viewModel.state.author.isEmpty() ||
                viewModel.state.genre.isEmpty() ||
                viewModel.state.yearPublished.isEmpty()
            ) {
                Toast.makeText(context, "Property can't be empty", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.event(
                    BookDetailContract.Event.OnAdd(
                        Book(
                            id = "",
                            title = viewModel.state.bookName,
                            author = viewModel.state.author,
                            genre = viewModel.state.genre,
                            yearPublished = viewModel.state.yearPublished.toInt(),
                        )
                    )
                )
            }
        },
        onDeleteClick = {
            viewModel.event(BookDetailContract.Event.OnDelete(viewModel.state.book!!.id))
        },
        onUpdateClick = {
            if (viewModel.state.bookName.isEmpty() ||
                viewModel.state.author.isEmpty() ||
                viewModel.state.genre.isEmpty() ||
                viewModel.state.yearPublished.isEmpty()
            ) {
                Toast.makeText(context, "Property can't be empty", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.event(
                    BookDetailContract.Event.OnUpdate(
                        Book(
                            id = viewModel.state.book!!.id,
                            title = viewModel.state.bookName,
                            author = viewModel.state.author,
                            genre = viewModel.state.genre,
                            yearPublished = viewModel.state.yearPublished.toInt(),
                        )
                    )
                )
            }

        },
        onBookNameChanged = {
            viewModel.event(BookDetailContract.Event.onBookNameChanged(it))
        },
        onAuthorChanged = {
            viewModel.event(BookDetailContract.Event.onAuthorChanged(it))
        },
        onGenreChanged = {
            viewModel.event(BookDetailContract.Event.onGenreChanged(it))
        },
        onYearPublishedChanged = {
            viewModel.event(BookDetailContract.Event.onYearPublishedChanged(it))
        }, onConfirm = {
            viewModel.setShowDialogValue(false)
        })
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookDetailScreen(
    state: BookDetailContract.State,
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onBookNameChanged: (String) -> Unit,
    onAuthorChanged: (String) -> Unit,
    onGenreChanged: (String) -> Unit,
    onYearPublishedChanged: (String) -> Unit,
    onConfirm: () -> Unit,
) {

    DisplayAlertDialog(
        title = state.actionTitle ?: "",
        message = state.actionMessage ?: "",
        confirmText = stringResource(R.string.ok),
        dismissText = null,
        openDialog = state.showDialog,
        onDismiss = {},
        onConfirm = onConfirm
    )

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            BookDetailAppbar(
                book = state.book,
                onBackClick = onBackClick,
                onAddClick = onAddClick,
                onDeleteClick = onDeleteClick,
                onUpdateClick = onUpdateClick
            )
        }) {
        BookDetailContent(
            bookName = state.bookName,
            onBookNameChanged = onBookNameChanged,
            author = state.author,
            onAuthorChanged = onAuthorChanged,
            genre = state.genre,
            onGenreChanged = onGenreChanged,
            yearPublished = state.yearPublished,
            onYearPublishedChanged = onYearPublishedChanged
        )
    }
}

@Preview
@Composable
fun BookDetailScreenPreview() {
    BookDetailScreen(state = BookDetailContract.State(
        book = Book(
            "Amir Book",
            "Codroid-ir",
            "some random id",
            "some random genre",
            2002
        ),
        isLoading = false,
        error = null,
        showDialog = false,
        actionMessage = null,
        actionTitle = null,
        bookName = "Amir Book",

        author = "",
        genre = "",
        yearPublished = ""
    ),
        onBackClick = {},
        onAddClick = {},
        onDeleteClick = {},
        onUpdateClick = {},
        onBookNameChanged = {},
        onAuthorChanged = {},
        onGenreChanged = {},
        onYearPublishedChanged = {},
        onConfirm = {})
}