package com.example.notesapplication.ui.Adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapplication.Model.Notes
import com.example.notesapplication.R
import com.example.notesapplication.databinding.ItemNoteBinding
import com.example.notesapplication.ui.Fragments.HomeFragment
import com.example.notesapplication.ui.Fragments.HomeFragmentDirections
import java.util.Locale

class NotesAdapter(val context: Context, var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    // ViewHolder for holding the item views
    class notesViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    // Function to update the adapter's data with a filtered list
    fun filtering(newFilteredList: ArrayList<Notes>) {
        notesList = newFilteredList
        notifyDataSetChanged() // Notifies the adapter that the data has changed
    }

    // Creating view holder instances
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.notesViewHolder {
        return notesViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // Binding data to the views
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        // Setting data to the respective views in the item layout
        holder.binding.noteTitle.text = data.title
        holder.binding.noteSubTitle.text = data.subTitle
        holder.binding.noteDate.text = data.date

        // Setting priority dot based on priority value
        when (data.priority) {
            "1" -> {
                holder.binding.notePriority.setBackgroundResource(R.drawable.green_dot)
            }

            "2" -> holder.binding.notePriority.setBackgroundResource(R.drawable.yellow_dot)


            "3" -> holder.binding.notePriority.setBackgroundResource(R.drawable.red_dot)
        }

        // Setting click listener to navigate to NoteEditFragment with the note data
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNoteEditFragment(data)
            Navigation.findNavController(it).navigate(action)
        }

    }

    // Returning the number of items in the list
    override fun getItemCount(): Int {
        return notesList.size
    }

}
