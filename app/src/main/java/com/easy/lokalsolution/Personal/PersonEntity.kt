package com.easy.lokalsolution.Personal



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String="",
    val age: Int=0
)
