package com.sagarannaldas.googleonetap.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DisplayAlertDialog(
    title: String = "Delete your account?",
    message: String = "Are you sure you want to delete your account?",
    openDialog: Boolean,
    onYesClicked: () -> Unit,
    onDialogClosed: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    fontStyle = MaterialTheme.typography.titleSmall.fontStyle,
                    fontWeight = FontWeight.Normal
                )
            },
            onDismissRequest = {
                onDialogClosed()
            },
            confirmButton = {
                OutlinedButton(onClick = {
                    onYesClicked()
                    onDialogClosed()
                }) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                Button(onClick = {
                    onDialogClosed()
                }) {
                    Text(text = "No")
                }
            }
        )
    }
}