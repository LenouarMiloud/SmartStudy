package com.fsociety.studysmart.presentation.subject


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fsociety.studysmart.presentation.dashbord.component.CardCount
import com.fsociety.studysmart.presentation.dashbord.component.listTask
import com.fsociety.studysmart.presentation.dashbord.component.sessionStudylist
import com.fsociety.studysmart.sessions
import com.fsociety.studysmart.tasks

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
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {},
                icon = { Icon(imageVector = Icons.Default.Add,contentDescription = "Add") },
                text = { Text(text = "Add Task") }
            )
        }
    ){paddingValue->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ){
            item {
                SubjectOverviewSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    studiesHours = "10",
                    goalHours = "15",
                    progress = 0.75f
                )
            }
            listTask(
                sectionTitle = "UPCOMING TASK",
                emptyListTask = "You don't have any upcoming task.\n" +
                        "Click the + button to add new task.",
                tasks = tasks,
                onCheckBoxClick = {},
                onCardTaskClick = {}
            )
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            listTask(
                sectionTitle = "COMPLETED TASK",
                emptyListTask = "You don't have any completed task.\n" +
                        "Click the check box on completion of task.",
                tasks = tasks,
                onCheckBoxClick = {},
                onCardTaskClick = {}
            )
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            sessionStudylist(
                sectionTitle = "RECENT STUDY SESSIONS ",
                emptyListTask = "You don't have any recent study sessions.\n" +
                        "Start a study session to begin recording your progress.",
                sessions = sessions,
                onDeleteIconClick = {}
            )
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
@Composable
private fun SubjectOverviewSection(
    modifier: Modifier,
    studiesHours: String,
    goalHours: String,
    progress: Float
){
    val progressPercentage = remember (progress){
        (progress * 100).toInt().coerceIn(0,100)
    }

    Row (
        modifier= modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){
        CardCount(
            modifier = Modifier.weight(1f),
            headingText = "Goal Study Hours",
            count = goalHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        CardCount(
            modifier = Modifier.weight(1f),
            headingText = "Study Hours",
            count = studiesHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier.size(75.dp),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = progress,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = progress,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            Text(text="$progressPercentage%")
        }
    }
}