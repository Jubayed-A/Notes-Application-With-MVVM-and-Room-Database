package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private var repository : NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes_Entity) {
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes_Entity>> = repository.getAllNotes()

    fun deleteNotes(id: Int) {
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes_Entity) {
        repository.updateNote(notes)
    }


}