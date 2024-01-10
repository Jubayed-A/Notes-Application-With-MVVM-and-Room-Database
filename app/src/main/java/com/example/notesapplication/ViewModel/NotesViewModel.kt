package com.example.notesapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapplication.Database.NotesDatabase
import com.example.notesapplication.Model.Notes
import com.example.notesapplication.Repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository:NotesRepository

    init {
        // Initialize the repository with the DAO instance from the database
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    // Add a note to the repository
    fun addNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    // Retrieve all notes from the repository
    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    // Delete a note from the repository by its ID
    fun deleteNotes(id:Int) {
        repository.deleteNotes(id)
    }

    // Update an existing note in the repository
    fun updateNotes(notes: Notes) {
        repository.updateNote(notes)
    }

    // Retrieve notes with low priority from the repository
    fun getLowNotes() : LiveData<List<Notes>> = repository.getLowNotes()

    // Retrieve notes with medium priority from the repository
    fun getMediumNotes() : LiveData<List<Notes>> = repository.getMediumNotes()

    // Retrieve notes with high priority from the repository
    fun getHighNotes() : LiveData<List<Notes>> = repository.getHighNotes()


}