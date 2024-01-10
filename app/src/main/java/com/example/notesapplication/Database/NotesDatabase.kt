package com.example.notesapplication.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapplication.Dao.NotesDao
import com.example.notesapplication.Model.Notes

// Define the database using Room annotations
@Database(entities = [Notes::class], version = 2, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    // Abstract function to retrieve the DAO
    abstract fun myNotesDao(): NotesDao

    // Companion object to handle database creation
    companion object {
        @Volatile
        var INSTANCE: NotesDatabase? = null

        // Create or retrieve an instance of the database
        fun getDatabaseInstance(context: Context): NotesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val roomDatabaseInstance =
                    Room.databaseBuilder(context, NotesDatabase::class.java, "Notes")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }

    }

}