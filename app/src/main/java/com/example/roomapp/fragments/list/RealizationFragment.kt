package com.example.roomapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.R
import com.example.roomapp.fragments.adapter.RealizationFragmentAdapter
import com.example.roomapp.viewmodel.RealizationViewModel
import kotlinx.android.synthetic.main.fragment_cafe.view.*
import kotlinx.android.synthetic.main.fragment_realization.view.*

class RealizationFragment : Fragment() {

    private lateinit var mRealizationViewModel: RealizationViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_realization, container, false)

        // Recyclerview
        val adapter = RealizationFragmentAdapter()
        val recyclerView = view.realizationRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mRealizationViewModel = ViewModelProvider(this).get(RealizationViewModel::class.java)
        mRealizationViewModel.readAllRealization.observe(viewLifecycleOwner, Observer { realization ->
            adapter.setAssortmentData(realization)
        })


        view.floatingRealizationActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_realizationFragment_to_addRealizationFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_realization_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.realization_menu_delete) {
            deleteAllCafe()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllCafe() {
        AlertDialog.Builder(requireContext())
                .setPositiveButton("Yes") { _, _ ->
                    mRealizationViewModel.deleteAllRealization()
                    Toast.makeText(
                            requireContext(),
                            "Successfully removed everything",
                            Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No") { _, _ -> }
                .setTitle("Delete everything?")
                .setMessage("Are you sure you want to delete everything?")
                .create().show()
    }


}