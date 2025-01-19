package com.easy.lokalsolution.Student

import androidx.lifecycle.LiveData

class StudentRepository(private val studentDao: StudentDao) {

    suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend fun updateStudent(student: Student) {
        studentDao.updateStudent(student)
    }

    suspend fun deleteStudent(student: Student) {
        studentDao.deleteStudent(student)
    }

    suspend fun getStudentById(studentId: Int): Student? {
        return studentDao.fetchStudentById(studentId)
    }
    fun getAllStudents(): LiveData<List<Student>> {
        return studentDao.getAllStudents()
    }
}
