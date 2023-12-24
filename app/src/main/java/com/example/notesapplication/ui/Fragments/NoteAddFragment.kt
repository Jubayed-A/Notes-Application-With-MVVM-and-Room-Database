package com.example.notesapplication.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notesapplication.Model.Notes
import com.example.notesapplication.R
import com.example.notesapplication.ViewModel.NotesViewModel
import com.example.notesapplication.databinding.FragmentNoteAddBinding
import java.util.Date


class NoteAddFragment : Fragment() {

    lateinit var binding: FragmentNoteAddBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteAddBinding.inflate(layoutInflater, container, false)

        // set default priority color
        binding.pGreen.setImageResource(R.drawable.done_icon)

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.done_icon)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.done_icon)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.done_icon)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.btnSaveNote.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    fun createNotes(it: View?) {

        /*// view model declare
        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)*/

        val title = binding.etTitle.text.toString()
        val subTitle = binding.etSubTitle.text.toString()
        val notes = binding.etNote.text.toString()

        val d = Date()
        val date: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)
        //        Log.e("hello", "createNotes : $date") // to check in logcat date working or not

        val data = Notes(
            null,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = date.toString(),
            priority
        )
        viewModel.addNotes(data)

        Toast.makeText(context, "Note Create Successfully", Toast.LENGTH_SHORT).show()


    }


}

