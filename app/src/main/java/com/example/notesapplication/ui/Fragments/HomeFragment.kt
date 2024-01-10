package com.example.notesapplication.ui.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.*
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Query
import androidx.room.util.query
import com.example.notesapplication.Model.Notes
import com.example.notesapplication.R
import com.example.notesapplication.ViewModel.NotesViewModel
import com.example.notesapplication.databinding.FragmentHomeBinding
import com.example.notesapplication.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NotesViewModel by viewModels()
    private var oldMyNotes = arrayListOf<Notes>()
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        // Set up the layout manager for RecyclerView
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = staggeredGridLayoutManager

        // Observe changes in the notes list and update the UI accordingly
        viewModel.getNotes().observe(viewLifecycleOwner) {
            oldMyNotes = it as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(), it)
            binding.recyclerView.adapter = adapter
        }

        // for filtering
        // show all item after filtering
        binding.filterAll.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) {
                oldMyNotes = it as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), it)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.smoothScrollToPosition(0) // Scrolls to the top smoothly
            }
        }
        // for high
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) {
                oldMyNotes = it as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), it)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.smoothScrollToPosition(0) // Scrolls to the top smoothly
            }
        }
        // for medium
        binding.filterMid.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) {
                oldMyNotes = it as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), it)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.smoothScrollToPosition(0) // Scrolls to the top smoothly
            }
        }
        // for low
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) {
                oldMyNotes = it as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(), it)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.smoothScrollToPosition(0) // Scrolls to the top smoothly
            }
        }

        // Click listener for the add note button
        binding.btnAddNote.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_noteAddFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.serach_menu, menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here.."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Notesfilltering(p0)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    // Filter the notes based on the search query
    private fun Notesfilltering(p0: String?) {
        val newfilltertedlist = arrayListOf<Notes>()
        for (i in oldMyNotes) {

            if (i.title!!.contains(p0!!) || i.subTitle!!.contains(p0)) {
                newfilltertedlist.add(i)
            }
        }
        adapter.filtering(newfilltertedlist)
    }

}

