package com.fsociety.studysmart.presentation.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun TaskScreen(){
    Scaffold(
        topBar = {

        }
    ) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskScreenTopBar(){
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigation Back"
                )
            }
        },
        title = { Text(text="Task")},
        actions = {

        }
    )
}