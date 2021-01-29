package com.example.roomapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomapp.R
//import com.example.roomapp.UpdateCafeFragmentArgs
import com.example.roomapp.model.Cafe
import com.example.roomapp.viewmodel.CafeViewModel
import kotlinx.android.synthetic.main.fragment_update_cafe.*
import kotlinx.android.synthetic.main.fragment_update_cafe.view.*


class UpdateCafeFragment : Fragment() {


    private val args by navArgs<UpdateCafeFragmentArgs>()

    private lateinit var mCafeViewModel: CafeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update_cafe, container, false)

        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)

        view.updateCafeName_et.setText(args.currentCafe.name)
        view.updateCafeAddress_et.setText(args.currentCafe.address)
        view.updateCafeProfit.setText(args.currentCafe.profit)



        view.update_btn.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {

        val name = updateCafeName_et.text.toString()
        val address = updateCafeAddress_et.text.toString()
        val profit = updateCafeProfit.text.toString()

        if (inputCheck(address, name, profit)) {
            // Create User Object
            val updatedCafe = Cafe(args.currentCafe.cafeId,address,name, profit)
            // Update Current User
            mCafeViewModel.updateCafe(updatedCafe)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateCafeFragment2_to_cafeFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }
    private fun inputCheck(address: String, name: String, profit: String): Boolean{
        return !(TextUtils.isEmpty(address) && TextUtils.isEmpty(name) && TextUtils.isEmpty(profit))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_cafe_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.cafe_menu_delete) {
            deleteCafe()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteCafe() {
        AlertDialog.Builder(requireContext())
        .setPositiveButton("Yes") { _, _ ->
            mCafeViewModel.deleteCafe(args.currentCafe)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentCafe.name}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateCafeFragment2_to_cafeFragment)
        }
        .setNegativeButton("No") { _, _ -> }
        .setTitle("Delete ${args.currentCafe.name}?")
        .setMessage("Are you sure you want to delete ${args.currentCafe.name}?")
        .create().show()
    }

}

/*

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
 */