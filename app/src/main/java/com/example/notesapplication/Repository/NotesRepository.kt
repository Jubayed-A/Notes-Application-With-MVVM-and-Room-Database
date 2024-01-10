package com.example.notesapplication.Repository

import androidx.lifecycle.LiveData
import com.example.notesapplication.Dao.NotesDao
import com.example.notesapplication.Model.Notes

// Repository class to abstract data access from various data sources
class NotesRepository(private val dao: NotesDao) {

    // Retrieve all notes
    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getNotes()
    }

    // Retrieve low priority notes
    fun getLowNotes(): LiveData<List<Notes>> = dao.getLowNotes()

    // Retrieve medium priority notes
    fun getMediumNotes(): LiveData<List<Notes>> = dao.getMediumNotes()

    // Retrieve high priority notes
    fun getHighNotes(): LiveData<List<Notes>> = dao.getHighNotes()

    // Insert a new note
    fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }

    // Delete a note by its ID
    fun deleteNotes(id: Int) {
        dao.deleteNotes(id)
    }

    // Update an existing note
    fun updateNote(notes: Notes) {
        dao.updateNotes(notes)
    }

}