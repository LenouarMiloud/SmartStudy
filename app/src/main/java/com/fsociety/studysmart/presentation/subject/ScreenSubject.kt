package com.fsociety.studysmart.presentation.subject


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ScreenSubject() {
    Scaffold (
        topBar = {
            ScreenSubjectTopBar(
                title = "Computer Science",
                onBackButtonClick = {},
                onDeleteButtonClick = {},
                onEditButtonClick = {}
            )
        }
    ){paddingValue->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ){

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenSubjectTopBar(
    title: String,
    onBackButtonClick: ()-> Unit,
    onDeleteButtonClick: ()-> Unit,
    onEditButtonClick: ()-> Unit,
){
    LargeTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigate Back"
                )
            }
        },
        title = {Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineSmall
        )},
        actions = {
            IconButton(onClick = onDeleteButtonClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Subject"
                )
            }
            IconButton(onClick = onEditButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Edit Subject"
                )
            }
        }
    )
}