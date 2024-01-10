package com.example.notesapplication.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesapplication.Model.Notes

// DAO (Data Access Object) interface for Notes
@Dao
interface NotesDao {

    // Retrieve all notes
    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>

    // Retrieve high priority notes
    @Query("SELECT * FROM Notes WHERE priority = 3")
    fun getHighNotes(): LiveData<List<Notes>>

    // Retrieve medium priority notes
    @Query("SELECT * FROM Notes WHERE priority = 2")
    fun getMediumNotes(): LiveData<List<Notes>>

    // Retrieve low priority notes
    @Query("SELECT * FROM Notes WHERE priority = 1")
    fun getLowNotes(): LiveData<List<Notes>>

    // Insert new note or replace existing on conflict
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    // Delete a note by its ID
    @Query("DELETE FROM Notes WHERE id= :id")
    fun deleteNotes(id: Int)

    // Update an existing note
    @Update
    fun updateNotes(notes: Notes)

}