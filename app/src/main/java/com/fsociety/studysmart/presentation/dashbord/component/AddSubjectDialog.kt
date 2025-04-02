package com.fsociety.studysmart.presentation.dashbord.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.fsociety.studysmart.domain.model.Subject

@Composable
fun AddSubjectDialog(
    isOpen: Boolean,
    title: String = "Add/Update Subject",
    selectedColor: List<Color>,
    subjectName: String,
    goalHours: String,
    onSubjectNameChange: (String)->Unit,
    onGoalHourChange: (String)->Unit,
    onColorChange: (List<Color>)->Unit,
    onDismissRequest: ()->Unit,
    onConfirmButtonClick: ()->Unit
){
    var subjectNameError by rememberSaveable { mutableStateOf<String?>(value = null) }
    var goalhourError by rememberSaveable { mutableStateOf<String?>(value = null) }

    subjectNameError = when{
        subjectName.isBlank() -> "Please enter Subject Name"
        subjectName.length <2 -> "Subject Name is too short"
        subjectName.length > 20 -> "Subject name is too long"
        else -> null
    }
    goalhourError = when{
        goalHours.isBlank() -> "Please enter Goal hours"
        goalHours.toFloatOrNull() == null -> "Invalid Number"
        goalHours.toFloat() < 1f -> "Please set at least 1 hour."
        goalHours.toFloat() > 1000f -> "Please set a maximum of 1000 hours."
        else -> null
    }

    if(isOpen){
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = title) },
            text = {
                Column {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth().padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.Absolute.SpaceAround
                    ){
                        Subject.subjectCardColors.forEach{colors->
                            Box (
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = if (colors == selectedColor) Color.Black
                                        else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .background(brush = Brush.verticalGradient(colors))
                                    .clickable { onColorChange(colors) }
                            )
                        }
                    }
                    OutlinedTextField(
                        value = subjectName,
                        onValueChange =onSubjectNameChange,
                        label = { Text(text = "Subject Name") },
                        singleLine = true,
                        isError = subjectNameError != null && subjectName.isNotBlank(),
                        supportingText = { Text(text = subjectNameError.orEmpty()) }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = goalHours,
                        onValueChange =onGoalHourChange,
                        label = { Text(text = "Goal Study Hours") },
                        singleLine = true,
                        isError = goalhourError != null && goalHours.isNotBlank(),
                        supportingText = { Text(text = goalhourError.orEmpty()) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = "Dismiss")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirmButtonClick,
                    enabled = subjectNameError == null && goalhourError == null
                ) {
                    Text(text = "Save")
                }
            }
        )
    }

}