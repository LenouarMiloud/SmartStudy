package com.fsociety.studysmart.presentation.dashbord.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AddSubjectDialog(
    isOpen: Boolean,
    title: String = "Add/Update Subject",
    onDismissRequest: ()->Unit,
    onConfirmButtonClick: ()->Unit
){
    if(isOpen){
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = title) },
            text = {
                Text(text = "Dialog Body")
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = "Dismiss")
                }
            },
            confirmButton = {
                TextButton(onClick = onConfirmButtonClick) {
                    Text(text = "Save")
                }
            }
        )
    }

}