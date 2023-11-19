package ir.codroid.books.presentation.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    confirmText: String,
    dismissText: String?,
    openDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton =
            {

                Button(onClick = {
                    onConfirm()
                    onDismiss()
                }) {
                    Text(
                        text = confirmText,
                    )
                }
            },
            dismissButton = {
                dismissText?.let {

                    OutlinedButton(onClick = {
                        onDismiss()
                    }) {
                        Text(
                            text = it,
                        )
                    }
                }
            },
            onDismissRequest = {
                onDismiss()
            }
        )
    }
}