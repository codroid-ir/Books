package ir.codroid.books.presentation.book_detail.component


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import ir.codroid.books.R
import ir.codroid.books.presentation.ui.theme.LARGE_PADDING
import ir.codroid.books.presentation.ui.theme.MEDIUM_PADDING
import ir.codroid.books.presentation.ui.theme.TOP_PADDING


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailContent(
    bookName: String,
    onBookNameChanged: (String) -> Unit,
    author: String,
    onAuthorChanged: (String) -> Unit,
    genre: String,
    onGenreChanged: (String) -> Unit,
    yearPublished: String,
    onYearPublishedChanged: (String) -> Unit,
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                start = LARGE_PADDING,
                end = LARGE_PADDING,
                bottom = LARGE_PADDING,
                top = TOP_PADDING + LARGE_PADDING
            )
    ) {


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = bookName,
            onValueChange = { onBookNameChanged(it) },
            label = { Text(text = stringResource(id = R.string.book_name)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true
        )

        Divider(
            modifier =
            Modifier.height(MEDIUM_PADDING), color = MaterialTheme.colorScheme.background
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = author,
            onValueChange = { onAuthorChanged(it) },
            label = { Text(text = stringResource(id = R.string.author)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true
        )
        Divider(
            modifier =
            Modifier.height(MEDIUM_PADDING), color = MaterialTheme.colorScheme.background
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = genre,
            onValueChange = { onGenreChanged(it) },
            label = { Text(text = stringResource(id = R.string.genre)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true
        )
        Divider(
            modifier =
            Modifier.height(MEDIUM_PADDING), color = MaterialTheme.colorScheme.background
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = yearPublished,
            onValueChange = { onYearPublishedChanged(it) },
            label = { Text(text = stringResource(id = R.string.year_published)) },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
@Preview
fun PreviewTaskContent() {
    BookDetailContent(
        "", {}, "", {}, "", {}, "", {},
    )
}