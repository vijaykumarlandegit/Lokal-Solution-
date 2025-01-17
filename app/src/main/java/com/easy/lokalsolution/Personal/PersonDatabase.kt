package com.easy.lokalsolution.Personal



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonEntity::class], version = 1, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun userDao(): PersonDao

    companion object {//only one instance
        @Volatile
        private var INSTANCE: PersonDatabase? = null

        fun getDatabase(context: Context) :PersonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonDatabase::class.java,
                    "user_table"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
