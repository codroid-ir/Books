package ir.codroid.books.presentation.book_list.component

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ir.codroid.books.domin.model.Book
import ir.codroid.books.presentation.book_list.BookListContract
import ir.codroid.books.presentation.ui.component.DefaultError
import ir.codroid.books.presentation.ui.component.DefaultLoading
import ir.codroid.books.presentation.ui.theme.TOP_APP_BAR_HEIGHT

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterial3Api
@Composable
fun BookListContent(
    state: BookListContract.State,
    modifier: Modifier = Modifier,
    onDelete: (String) -> Unit,
    onBookClicked: (String) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = TOP_APP_BAR_HEIGHT),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        state.error?.let {
            DefaultError(it)
        }
        DefaultLoading(isSystemInDarkTheme(), state.isLoading)
    }
    state.books?.let { books ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(top = TOP_APP_BAR_HEIGHT)
        ) {
            items(books, key = {
                it.id
            }) { book ->


                var itemAppeared by remember { mutableStateOf(false) }

                LaunchedEffect(key1 = true) {
                    itemAppeared = true
                }

                AnimatedVisibility(
                    visible = itemAppeared,
                    enter = expandVertically(
                        animationSpec = tween(
                            durationMillis =
                            500
                        )
                    ),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    )
                ) {
                    BookListItem(book, onDelete = { onDelete(it) }) {
                        onBookClicked(it)
                    }
                }
            }
        }
    }
}


@ExperimentalMaterial3Api
@Preview
@Composable
fun BookListContentPreview() {
    BookListContent(
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
        onDelete = {}
    ) {

    }
}

