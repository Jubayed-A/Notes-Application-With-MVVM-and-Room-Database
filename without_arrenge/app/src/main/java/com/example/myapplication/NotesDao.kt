package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes_Entity")
    fun getNotes(): LiveData<List<Notes_Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes_Entity)

    @Query("DELETE FROM Notes_Entity WHERE id= :id")
    fun deleteNotes(id : Int)

    @Update
    fun updateNotes(notes: Notes_Entity)

}