package com.easy.lokalsolution.Student

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM student_database WHERE id = :id")
    suspend fun fetchStudentById(id: Int): Student?

    @Query("SELECT * FROM student_database")
    fun getAllStudents(): LiveData<List<Student>>

}
