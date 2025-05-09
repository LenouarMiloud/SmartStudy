package com.fsociety.studysmart.presentation.dashbord

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fsociety.studysmart.R
import com.fsociety.studysmart.domain.model.Subject
import com.fsociety.studysmart.presentation.component.AddSubjectDialog
import com.fsociety.studysmart.presentation.component.CardCount
import com.fsociety.studysmart.presentation.component.CardSubject
import com.fsociety.studysmart.presentation.component.DeleteDialog
import com.fsociety.studysmart.presentation.component.listTask
import com.fsociety.studysmart.presentation.component.sessionStudylist
import com.fsociety.studysmart.sessions
import com.fsociety.studysmart.tasks
import com.fsociety.studysmart.subjects


@Composable
fun DashbordScreen(){

    var isAddSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSessionDialogOpen by rememberSaveable { mutableStateOf(false) }
    var subjectName by remember { mutableStateOf("") }
    var goalHour by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Subject.subjectCardColors.random()) }

    AddSubjectDialog(
        isOpen = isAddSubjectDialogOpen,
        subjectName = subjectName,
        goalHours = goalHour,
        onSubjectNameChange = { subjectName = it },
        onGoalHourChange = {goalHour = it },
        selectedColor = selectedColor,
        onColorChange = { selectedColor = it },
        onDismissRequest = { isAddSubjectDialogOpen = false },
        onConfirmButtonClick = {
            isAddSubjectDialogOpen = false
        }
    )
    DeleteDialog(
        isOpen = isDeleteSessionDialogOpen,
        title = "Delete Session",
        bodyText = "Are you sure , you want to delete this session?, Your studies hours will be reduced" +
                "by this session time. this action can not be undone.",
        onDismissRequest = {isDeleteSessionDialogOpen = false},
        onConfirmButtonClick = {isDeleteSessionDialogOpen = false}
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    subjectCount = 5,
                    studiedHours = "10",
                    goalHours = "15",
                )
            }
            item {
                CardSubjectSection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = subjects,
                    onAddIconClicked = {
                        isAddSubjectDialogOpen = true
                    }
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
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            sessionStudylist(
                sectionTitle = "RECENT STUDY SESSIONS ",
                emptyListTask = "You don't have any recent study sessions.\n" +
                        "Start a study session to begin recording your progress.",
                sessions = sessions,
                onDeleteIconClick = {isDeleteSessionDialogOpen = true}
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
    emptyListText: String = "You don't have any subjects.\n Click the + button to add new subject.",
    onAddIconClicked: ()->Unit
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
            IconButton(onClick = onAddIconClicked) {
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















