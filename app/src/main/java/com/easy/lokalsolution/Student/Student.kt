package com.easy.lokalsolution.Student

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_database")
data class Student(
    @PrimaryKey val id: Int,
    val name: String,
    val age: Int
)
