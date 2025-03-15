package com.fsociety.studysmart.presentation.dashbord.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fsociety.studysmart.R

@Composable
fun CardSubject(){
    Box (
        modifier = Modifier.size(150.dp)
    ){
        Column (
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(R.drawable.books),
                contentDescription ="",
                modifier = Modifier.size(90.dp)
            )
            Text(
                text ="",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
        }
    }
}