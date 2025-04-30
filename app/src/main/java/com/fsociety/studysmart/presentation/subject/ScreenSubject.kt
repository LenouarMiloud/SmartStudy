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
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fsociety.studysmart.domain.model.Subject
import com.fsociety.studysmart.presentation.component.AddSubjectDialog
import com.fsociety.studysmart.presentation.component.CardCount
import com.fsociety.studysmart.presentation.component.DeleteDialog
import com.fsociety.studysmart.presentation.component.listTask
import com.fsociety.studysmart.presentation.component.sessionStudylist
import com.fsociety.studysmart.sessions
import com.fsociety.studysmart.tasks

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSubject() {

    val listState = rememberLazyListState()
    val isFABExpanded by remember {derivedStateOf { listState.firstVisibleItemIndex == 0 }}
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    var isEditSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSessionDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var subjectName by remember { mutableStateOf("") }
    var goalHour by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Subject.subjectCardColors.random()) }

    AddSubjectDialog(
        isOpen = isEditSubjectDialogOpen,
        subjectName = subjectName,
        goalHours = goalHour,
        onSubjectNameChange = { subjectName = it },
        onGoalHourChange = {goalHour = it },
        selectedColor = selectedColor,
        onColorChange = { selectedColor = it },
        onDismissRequest = { isEditSubjectDialogOpen = false },
        onConfirmButtonClick = {
            isEditSubjectDialogOpen = false
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
    DeleteDialog(
        isOpen = isDeleteSubjectDialogOpen,
        title = "Delete Subject",
        bodyText = "Are you sure , you want to delete this subject?, All related tasks and ." +
                "study session will be permanetly removed. Tis action can not be undone",
        onDismissRequest = {isDeleteSubjectDialogOpen = false},
        onConfirmButtonClick = {isDeleteSubjectDialogOpen = false}
    )

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ScreenSubjectTopBar(
                title = "Computer Science",
                onBackButtonClick = {},
                onDeleteButtonClick = {isDeleteSessionDialogOpen = true},
                onEditButtonClick = {isEditSubjectDialogOpen=true},
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { },
                icon = { Icon(imageVector = Icons.Default.Add,contentDescription = "Add") },
                text = { Text(text = "Add Task") },
                expanded = isFABExpanded
            )
        }
    ){paddingValue->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
            state = listState
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
                onDeleteIconClick = {isDeleteSessionDialogOpen = true}
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
    scrollBehavior: TopAppBarScrollBehavior
){
    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
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