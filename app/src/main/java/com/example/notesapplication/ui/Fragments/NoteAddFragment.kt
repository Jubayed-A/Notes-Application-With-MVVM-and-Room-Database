package com.example.notesapplication.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notesapplication.R
import com.example.notesapplication.databinding.FragmentNoteAddBinding


class NoteAddFragment : Fragment() {

    private lateinit var binding: FragmentNoteAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteAddBinding.inflate(layoutInflater, container, false)




        return binding.root
    }


}