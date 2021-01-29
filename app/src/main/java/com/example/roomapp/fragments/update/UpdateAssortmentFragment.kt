package com.example.roomapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
import com.example.roomapp.model.Assortment
import com.example.roomapp.viewmodel.AssortmentViewModel
import kotlinx.android.synthetic.main.fragment_update_assortment.*
import kotlinx.android.synthetic.main.fragment_update_assortment.view.*

class UpdateAssortmentFragment : Fragment() {

    private val args by navArgs<UpdateAssortmentFragmentArgs>()

    private lateinit var mAssortmentViewModel: AssortmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_assortment, container, false)

        mAssortmentViewModel = ViewModelProvider(this).get(AssortmentViewModel::class.java)

        view.updateName.setText(args.currentAssortment.name)
        view.updatePrice.setText(args.currentAssortment.price)
        view.updateDescription.setText(args.currentAssortment.description)

        view.update_btn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)



        return view
    }

    private fun updateItem() {

        val name = updateName.text.toString()
        val price = updatePrice.text.toString()
        val description = updateDescription.text.toString()

        if (inputCheck(name, price)) {
            // Create User Object
            val updatedAssortment =
                Assortment(args.currentAssortment.assortmentId, name, price, description)
            // Update Current User
            mAssortmentViewModel.updateAssortment(updatedAssortment)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateAssortmentFragment_to_assortmentFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(name: String, price: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(price))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_assortment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.assortment_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        AlertDialog.Builder(requireContext())
        .setPositiveButton("Yes") { _, _ ->
            mAssortmentViewModel.deleteAssortment(args.currentAssortment)
            Toast.makeText(
                requireContext(),
                "Successfully removed assortment with id: ${args.currentAssortment.assortmentId}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateAssortmentFragment_to_assortmentFragment)
        }
        .setNegativeButton("No") { _, _ -> }
        .setTitle("Delete assortment with id: ${args.currentAssortment.assortmentId}?")
        .setMessage("Are you sure you want to delete assortment ${args.currentAssortment.assortmentId}?")
        .create().show()
    }

}

/*
class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateEmail_et.setText(args.currentUser.email)
        view.updatePassword_et.setText(args.currentUser.password)
        view.updateFirstName_et.setText(args.currentUser.firstName)
        view.updateLastName_et.setText(args.currentUser.lastName)
        view.updatePhoneNumber_et.setText(args.currentUser.phoneNumber)

        view.update_btn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {

        val email = updateEmail_et.text.toString()
        val password = updatePassword_et.text.toString()
        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val phoneNumber = updatePhoneNumber_et.text.toString()

        if (inputCheck(firstName, lastName, phoneNumber)) {
            // Create User Object
            val updatedUser = User(args.currentUser.id,email,password, firstName, lastName, phoneNumber)
            // Update Current User
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, phoneNumber: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(phoneNumber))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}
 */