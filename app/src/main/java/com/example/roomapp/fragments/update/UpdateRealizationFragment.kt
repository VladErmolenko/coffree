package com.example.roomapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
import com.example.roomapp.model.Realization
import com.example.roomapp.viewmodel.RealizationViewModel
import kotlinx.android.synthetic.main.fragment_update_realization.*
import kotlinx.android.synthetic.main.fragment_update_realization.view.*


class UpdateRealizationFragment : Fragment() {

    private val args by navArgs<UpdateRealizationFragmentArgs>()

    private lateinit var mRealizationViewModel: RealizationViewModel


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_realization, container, false)

        mRealizationViewModel = ViewModelProvider(this).get(
                RealizationViewModel::
                class.java
        )

        view.updateRealizationDate_et.setText(args.currentRealization.date)
        view.updateRealizationNotes_et.setText(args.currentRealization.notes)

        view.update_realization_btn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {

        val date = updateRealizationDate_et.text.toString()
        val note = updateRealizationNotes_et.text.toString()

        if (inputCheck()) {
            // Create User Object
            val updatedRealization =
                    Realization(
                            args.currentRealization.realizationId,
                            args.currentRealization.realizationAssortmentId,
                            args.currentRealization.realizationEmployeeId,
                            args.currentRealization.realizationCafeId,
                            date,
                            note
                    )
            // Update Current User
            mRealizationViewModel.updateRealization(updatedRealization)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateRealizationFragment_to_realizationFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun inputCheck(): Boolean {
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_realization_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.realization_menu_delete) {
            deleteRealization()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteRealization() {
        AlertDialog.Builder(requireContext())
                .setPositiveButton("Yes") { _, _ ->
                    mRealizationViewModel.deleteRealization(args.currentRealization)
                    Toast.makeText(
                            requireContext(),
                            "Successfully removed realization from: ${args.currentRealization.date}",
                            Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_updateRealizationFragment_to_realizationFragment)
                }
                .setNegativeButton("No") { _, _ -> }
                .setTitle("Delete ${args.currentRealization.date}?")
                .setMessage("Are you sure you want to delete realization for ${args.currentRealization.date}?")
                .create().show()

    }

}
