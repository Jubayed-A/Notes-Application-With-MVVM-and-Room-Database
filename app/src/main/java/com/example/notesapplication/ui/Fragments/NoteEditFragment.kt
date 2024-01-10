package com.example.notesapplication.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapplication.Model.Notes
import com.example.notesapplication.R
import com.example.notesapplication.ViewModel.NotesViewModel
import com.example.notesapplication.databinding.FragmentNoteEditBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date


class NoteEditFragment : Fragment() {

    private lateinit var binding: FragmentNoteEditBinding
    private val oldNotes by navArgs<NoteEditFragmentArgs>()
    private var priority: String = "1"
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteEditBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        // Set up toolbar back button
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        // Set initial values based on the received note
        binding.etTitle.setText(oldNotes.data.title)
        binding.etSubTitle.setText(oldNotes.data.subTitle)
        binding.etNote.setText(oldNotes.data.notes)

        // Handle priority selection
        oldNotes.data.priority?.let { setPrioritySelection(it) }

        // Handle priority selection clicks
        binding.pGreen.setOnClickListener { setPriority("1") }
        binding.pYellow.setOnClickListener { setPriority("2") }
        binding.pRed.setOnClickListener { setPriority("3") }

        // Update the note when Save button is clicked
        binding.btnSaveNote.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    // Set the priority icons based on the note's priority
    private fun setPrioritySelection(priority: String) {
        val priorityViews =
            mapOf("1" to binding.pGreen, "2" to binding.pYellow, "3" to binding.pRed)
        priorityViews.forEach { (key, view) ->
            if (key == priority) {
                view.setImageResource(R.drawable.done_icon)
            } else {
                view.setImageResource(0)
            }
        }
        this.priority = priority
    }

    // Update priority based on the clicked icon
    private fun setPriority(priority: String) {
        setPrioritySelection(priority)
        this.priority = priority
    }

    private fun updateNotes(it: View?) {
        // Get updated note details
        val title = binding.etTitle.text.toString()
        val subTitle = binding.etSubTitle.text.toString()
        val notes = binding.etNote.text.toString()

        val d = Date()
        val date: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        // Create updated note
        val data = Notes(
            oldNotes.data.id,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = date.toString(),
            priority = priority
        )
        // Update the note in the ViewModel
        viewModel.updateNotes(data)

        // Show success message and navigate to HomeFragment
        Toast.makeText(context, "Note Update Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_noteEditFragment_to_homeFragment)

    }

    // Inflate delete_menu for delete option in toolbar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed() // Handle back button press
                true
            }

            R.id.menu_delete_note -> {
                showDeleteConfirmationDialog() // Show delete confirmation dialog
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    // Show the delete confirmation dialog
    private fun showDeleteConfirmationDialog() {
        val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(R.layout.delete_dialog)

        bottomSheet.findViewById<TextView>(R.id.dialogYes)?.setOnClickListener {
            deleteNoteAndNavigateToHome() // Delete the note and navigate to HomeFragment
            bottomSheet.dismiss()
        }

        bottomSheet.findViewById<TextView>(R.id.dialogNo)?.setOnClickListener {
            bottomSheet.dismiss() // Dismiss the dialog
        }

        bottomSheet.show()
    }

    // Delete the note and navigate to HomeFragment
    private fun deleteNoteAndNavigateToHome() {
        oldNotes.data.id?.let { noteId ->
            viewModel.deleteNotes(noteId)
        }
        navigateToHomeFragment()
    }

    // Navigate to HomeFragment
    private fun navigateToHomeFragment() {
        val action = NoteEditFragmentDirections.actionNoteEditFragmentToHomeFragment()
        findNavController().navigate(action)
    }

}