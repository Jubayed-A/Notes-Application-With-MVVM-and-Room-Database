package com.example.notesapplication.ui.Fragments

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.notesapplication.R
import com.example.notesapplication.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.btnAddNote.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_noteAddFragment)
        }

        return binding.root
    }


}