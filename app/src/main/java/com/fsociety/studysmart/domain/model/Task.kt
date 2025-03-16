package com.fsociety.studysmart.domain.model

data class Task(
    val title: String,
    val description: String,
    val dueData: Long,
    val priority: Int,
    val relatedToSubject: String,
    val isComplete: String
)
