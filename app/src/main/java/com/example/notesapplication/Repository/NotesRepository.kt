package com.example.notesapplication.Repository

import androidx.lifecycle.LiveData
import com.example.notesapplication.Dao.NotesDao
import com.example.notesapplication.Model.Notes

class NotesRepository( val dao: NotesDao) {

    fun getAllNotes() : LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }
    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }
    fun updateNote(notes: Notes){
        dao.updateNotes(notes)
    }

}