package ir.codroid.books.presentation.book_detail.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ir.codroid.books.R
import ir.codroid.books.domin.model.Book


@Composable
fun BookDetailAppbar(
    book: Book?,
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
) {
    if (book == null)
        NewBookAppbar(onBackClick = onBackClick, onAddClick = onAddClick)
    else
        ExistingTaskAppbar(
            book!!, onCloseClick = onBackClick,
            onDeleteClick = onDeleteClick, onUpdateClick = onUpdateClick
        )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewBookAppbar(
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.add_book))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        actions = {
            IconButton(onClick = { onAddClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(id = R.string.add_book)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_arrow)
                )
            }
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppbar(
    selectedBook: Book,
    onCloseClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = selectedBook.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        actions = {
            ExitingBookAppBarAction(
                onDeleteClick = onDeleteClick,
                onUpdateClick = onUpdateClick
            )
        },
        navigationIcon = {
            IconButton(onClick = { onCloseClick() }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = stringResource(id = R.string.close)
                )
            }
        }

    )
}


@Composable
fun ExitingBookAppBarAction(
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
) {
//    DisplayAlertDialog(
//        title = stringResource(id = R.string.ad_delete_title, selectedBook.title),
//        message = stringResource(id = R.string.ad_delete_message, selectedBook.title),
//        confirmText =stringResource(id = R.string.yes) ,
//        dismissText = stringResource(id = R.string.no),
//        openDialog = openDialog.value,
//        onDismiss = {
//            onDismissClick()
//        }) {
//        onConfirmClick()
//    }
    IconButton(onClick = { onDeleteClick() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete)
        )
    }
    IconButton(onClick = { onUpdateClick() }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update)
        )
    }
}


@Composable
@Preview
fun PreviewTaskAppbar() {
    ExistingTaskAppbar(
        Book(
            "Amir Book",
            "Codroid-ir",
            "some random id",
            "some random genre",
            2002
        ), {}, {}, {}
    )
}

@Preview
@Composable
fun NewBookAppbarPreview() {
    NewBookAppbar({}, {})
}