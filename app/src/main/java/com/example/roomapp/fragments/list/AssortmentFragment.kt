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
import com.example.roomapp.fragments.adapter.AssortmentFragmentAdapter
import com.example.roomapp.viewmodel.AssortmentViewModel
import kotlinx.android.synthetic.main.fragment_assortment.view.*
import kotlinx.android.synthetic.main.fragment_assortment.view.recyclerview

class AssortmentFragment : Fragment() {

    private lateinit var mAssortmentViewModel: AssortmentViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_assortment, container, false)

        val adapter = AssortmentFragmentAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mAssortmentViewModel = ViewModelProvider(this).get(AssortmentViewModel::class.java)
        mAssortmentViewModel.readAllAssorment.observe(viewLifecycleOwner, Observer { assortment ->
            adapter.setAssortmentData(assortment)
        })

        view.floatingAssortmentActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_assortmentFragment_to_addAssortmentFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_assortment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.assortment_delete) {
            deleteAllAssortment()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllAssortment() {
        AlertDialog.Builder(requireContext())
                .setPositiveButton("Yes") { _, _ ->
                    mAssortmentViewModel.deleteAllAssortment()
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