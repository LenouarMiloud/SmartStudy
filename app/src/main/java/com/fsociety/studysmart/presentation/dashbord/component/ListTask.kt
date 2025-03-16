package com.fsociety.studysmart.presentation.dashbord.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fsociety.studysmart.R
import com.fsociety.studysmart.domain.model.Task

fun LazyListScope.listTask(
    sectionTitle: String,
    emptyListTask: String,
    tasks: List<Task>
    ){
    item {
        Text(
            text = "",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(12.dp)
        )
    }
    if (tasks.isEmpty()) {
        item {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    modifier = Modifier.size(140.dp),
                    painter = painterResource(R.drawable.task),
                    contentDescription = emptyListTask
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = emptyListTask,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
@Composable
fun CardTask(
    modifier: Modifier,
    task:Task
){
    ElevatedCard (
        modifier = modifier.clickable {  }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (){
                Text(
                    text = task.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if(task.isComplete){
                        TextDecoration.LineThrough
                    }else TextDecoration.None
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "${task.dueData}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}













