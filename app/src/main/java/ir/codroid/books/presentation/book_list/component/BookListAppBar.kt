package ir.codroid.books.presentation.book_list.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import ir.codroid.books.R
import ir.codroid.books.presentation.book_list.BookListContract
import ir.codroid.books.presentation.ui.theme.DISABLE_ALPHA
import ir.codroid.books.presentation.ui.theme.MEDIUM_ALPHA

@ExperimentalMaterial3Api
@Composable
fun BookListAppBar(
    state: BookListContract.State,
    onSearch: () -> Unit,
    onCloseClicked: () -> Unit,
    onTextChanged: (String) -> Unit,
) {
    TopAppBar(
        title = {
            if (state.showSearch) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.searchText,
                    onValueChange = {
                        onTextChanged(it)
                    }, colors = TextFieldDefaults.textFieldColors(
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.search),
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.alpha(MEDIUM_ALPHA)
                        )
                    },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.alpha(DISABLE_ALPHA)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            if (state.searchText.isNotEmpty())
                                onTextChanged("")
                            else
                                onCloseClicked()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onTextChanged(state.searchText)
                        }
                    ))
            } else {
                Text(
                    text = stringResource(R.string.app_name),
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        actions = {
            if (!state.showSearch)
            IconButton(
                onClick = onSearch,
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        }
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun BookListAppbarPreview() {
    BookListAppBar(
        state = BookListContract.State(
            showSearch = false
        ),
        onSearch = {} ,
        onCloseClicked = {} ,
        onTextChanged = {}
    )
}



