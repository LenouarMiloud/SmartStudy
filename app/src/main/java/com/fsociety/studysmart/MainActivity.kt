package com.fsociety.studysmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fsociety.studysmart.domain.model.Session
import com.fsociety.studysmart.domain.model.Subject
import com.fsociety.studysmart.domain.model.Task
import com.fsociety.studysmart.presentation.dashbord.DashbordScreen
import com.fsociety.studysmart.presentation.theme.StudySmartTheme
import com.fsociety.studysmart.presentation.subject.ScreenSubject
import com.fsociety.studysmart.presentation.task.TaskScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudySmartTheme {
                //DashbordScreen()
                //ScreenSubject()
                TaskScreen()
            }
        }
    }
}

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
    ),
    Task(
        title = "Sport",
        description = "",
        dueData = 0L,
        priority = 0,
        relatedToSubject = "",
        isComplete = true,
        taskSubjectId = 0,
        taskId = 1
    ),
    Task(
        title = "programming",
        description = "",
        dueData = 0L,
        priority = 0,
        relatedToSubject = "",
        isComplete = true,
        taskSubjectId = 0,
        taskId = 1
    ),
    Task(
        title = "game",
        description = "",
        dueData = 0L,
        priority = 0,
        relatedToSubject = "",
        isComplete = true,
        taskSubjectId = 0,
        taskId = 1
    )
)

val sessions= listOf(
    Session(
        sessionSubjectId = 0,
        relatedToSubject = "ARABIC",
        date = 0L,
        duration = 4,
        sessionId = 0
    ),
    Session(
        sessionSubjectId = 0,
        relatedToSubject = "Computer Science",
        date = 0L,
        duration = 4,
        sessionId = 0
    ),
    Session(
        sessionSubjectId = 0,
        relatedToSubject = "MATH",
        date = 0L,
        duration = 4,
        sessionId = 0
    )

)
