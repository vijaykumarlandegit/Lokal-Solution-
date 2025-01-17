package com.easy.lokalsolution.Personal


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Insert
    suspend fun insert(user: PersonEntity)

    @Query("SELECT * FROM user_table")
     fun getAllUsers():LiveData< List<PersonEntity>>
}
