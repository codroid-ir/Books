package ir.codroid.books.presentation.book_list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.codroid.books.R
import ir.codroid.books.domin.model.Book
import ir.codroid.books.navigation.Screen
import ir.codroid.books.presentation.book_list.component.BookListAppBar
import ir.codroid.books.presentation.book_list.component.BookListContent
import ir.codroid.books.presentation.ui.component.DisplayAlertDialog


@ExperimentalMaterial3Api
@Composable
fun BookListRoute(
    viewModel: BookListViewModel = hiltViewModel(),
    navController: NavHostController
) {


    BookListScreen(
        state = viewModel.state,
        onDelete = { bookId ->
            viewModel.event(BookListContract.Event.OnDelete(bookId))
        },
        onBookClicked = {bookId ->
            navController.navigate(route = Screen.BookDetail.route + "?bookId=$bookId")
        },
        onSearch = { viewModel.event(BookListContract.Event.onSearch) },
        onCloseClicked = { viewModel.event(BookListContract.Event.onClose) },
        onDialogDismiss = { viewModel.setShowDialogValue(it) },
        onTextChanged = { searchText ->
            viewModel.event(BookListContract.Event.OnSearchTextChanged(searchText))
        }
    )
}

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookListScreen(
    state: BookListContract.State,
    onDelete: (String) -> Unit,
    onBookClicked: (String) -> Unit,
    onSearch: () -> Unit,
    onDialogDismiss: (Boolean) -> Unit,
    onCloseClicked: () -> Unit,
    onTextChanged: (String) -> Unit,
) {

    DisplayAlertDialog(
        title = stringResource(R.string.delete),
        message = state.deleteMessage ?: "",
        confirmText = "Ok",
        dismissText = null,
        openDialog = state.showDialog,
        onDismiss = { onDialogDismiss(false) },
        onConfirm = { onDialogDismiss(false) },

        )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BookListAppBar(
                state = state,
                onSearch = onSearch,
                onCloseClicked = onCloseClicked,
                onTextChanged = onTextChanged
            )
        },
    ) {
        BookListContent(
            state = state,
            onDelete = { bookId ->
                onDelete(bookId)
            }
        ) { bookId ->
            onBookClicked(bookId)
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun BookListScreenPreview() {
    BookListScreen(
        state = BookListContract.State(
            books = listOf(
                Book(
                    title = "EL LLANTO DE LOS MARES",
                    author = "Marixa Andino",
                    id = "PyJNziFPUreLuhoGW",
                    genre = "Drama",
                    yearPublished = 1985,
                ), Book(
                    title = "EL LLANTO DE LOS MARES",
                    author = "Marixa Andino",
                    id = "PyJNziFPULuhoGW",
                    genre = "Drama",
                    yearPublished = 1985,
                ), Book(
                    title = "EL LLANTO DE LOS MARES",
                    author = "Marixa Andino",
                    id = "PyJNziFPUereLuhoGW",
                    genre = "Drama",
                    yearPublished = 1985,
                ), Book(
                    title = "EL LLANTO DE LOS MARES",
                    author = "Marixa Andino",
                    id = "PyJNziFPwreewULuhoGW",
                    genre = "Drama",
                    yearPublished = 1985,
                )
            ),
            isLoading = false,
            error = null,
        ),
        {},
        {},
        {},
        {},
        {}
    ) {

    }
}