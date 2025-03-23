package com.fsociety.studysmart.presentation.dashbord

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fsociety.studysmart.R
import com.fsociety.studysmart.domain.model.Subject
import com.fsociety.studysmart.domain.model.Task
import com.fsociety.studysmart.presentation.dashbord.component.CardCount
import com.fsociety.studysmart.presentation.dashbord.component.CardSubject
import com.fsociety.studysmart.presentation.dashbord.component.listTask


@Composable
fun DashbordScreen(){

    val subjects = listOf(
        Subject(name = "English", goalHours = 10f, colors = Subject.subjectCardColors[0], subjectId = 0),
        Subject(name = "Math", goalHours = 10f, colors = Subject.subjectCardColors[1], subjectId = 0),
        Subject(name = "Physic", goalHours = 10f, colors = Subject.subjectCardColors[2], subjectId = 0),
        Subject(name = "Arab", goalHours = 10f, colors = Subject.subjectCardColors[3], subjectId = 0),
        Subject(name = "Info", goalHours = 10f, colors = Subject.subjectCardColors[4], subjectId = 0),
    )

    val tasks = listOf(
        Task(
            title = "Prepare Notes",
            description = "",
            dueData = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = false,
            taskSubjectId = 0,
            taskId = 1
        ),
        Task(
            title = "do Homework",
            description = "",
            dueData = 0L,
            priority = 0,
            relatedToSubject = "",
            isComplete = true,
            taskSubjectId = 0,
            taskId = 1
        ),
        Task(
            title = "Go Coatching",
            description = "",
            dueData = 0L,
            priority = 2,
            relatedToSubject = "",
            isComplete = false,
            taskSubjectId = 0,
            taskId = 1
        )


    )


    Scaffold(
        topBar = {DashbordScreenTopBar()}
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CardCountSection(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    subjectCount = 5,
                    studiedHours = "10",
                    goalHours = "15",
                )
            }
            item {
                CardSubjectSection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = subjects
                )
            }
            item {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp)
                ) {
                    Text(text = "Start Study Session")
                }
            }
            listTask(
                sectionTitle = "UPCOMING TASK",
                emptyListTask = "You don't have any upcoming task.\n" +
                        "Click the + button in subject screen to add new task.",
                tasks = tasks,
                onCheckBoxClick = {},
                onCardTaskClick = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashbordScreenTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Study Smart",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}

@Composable
private fun CardCountSection(
    modifier:Modifier,
    subjectCount: Int,
    studiedHours: String,
    goalHours: String
){
    Row (modifier = modifier){
        CardCount(
            modifier = Modifier.weight(1f),
            headingText = "Subject Count",
            count = "$subjectCount"
        )
        Spacer(Modifier.width(10.dp))
        CardCount(
            modifier = Modifier.weight(1f),
            headingText = "Studied Hours",
            count = studiedHours
        )
        Spacer(Modifier.width(10.dp))
        CardCount(
            modifier = Modifier.weight(1f),
            headingText = "Goal Study hours",
            count = goalHours
        )
    }
}

@Composable
fun CardSubjectSection(
    modifier: Modifier ,
    subjectList: List<Subject>,
    emptyListText: String = "You don't have any subjects.\n Click the + button to add new subject."
){
    Column(modifier = modifier) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "SUBJECTS",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp)
            )
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add Subject"
                )
            }
        }
        if (subjectList.isEmpty()) {
            Image(
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.books),
                contentDescription = emptyListText
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = emptyListText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ){
            items(subjectList){subject ->
                CardSubject(
                    modifier = modifier,
                    subjectName = subject.name,
                    gradientColor = subject.colors,
                    onClick = {}
                )

            }
        }
    }

}















