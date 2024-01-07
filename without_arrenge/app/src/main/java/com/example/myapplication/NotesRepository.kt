package com.example.myapplication

import androidx.lifecycle.LiveData

class NotesRepository(private val dao: NotesDao) {

    fun getAllNotes() : LiveData<List<Notes_Entity>> {
        return dao.getNotes()
    }

    fun insertNotes(notes: Notes_Entity){
        dao.insertNotes(notes)
    }
    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }
    fun updateNote(notes: Notes_Entity){
        dao.updateNotes(notes)
    }

}