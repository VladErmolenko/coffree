package com.example.roomapp.fragments.add

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.model.Cafe
import com.example.roomapp.model.User
import com.example.roomapp.viewmodel.CafeViewModel
import com.example.roomapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    private lateinit var mCafeViewModel: CafeViewModel

    private var cafeList = emptyList<Cafe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)
        mCafeViewModel.readAllCafeData.observe(viewLifecycleOwner, Observer { cafe ->
            cafeList=cafe
        })

        view.add_user_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val email = addEmail_et.text.toString()
        val password = addPassword_et.text.toString()
        val cafeId = addCafeID.text.toString()
        val firstName = addFirstName_et.text.toString()
        val lastName = addLastName_et.text.toString()
        val phoneNumber = addPhoneNumber_et.text.toString()
        val role = addRole_et.text.toString() != "0"

        var check=false

        if(cafeId.isNotEmpty()) {
            check = cafeList.any { cafeId.toInt() == it.cafeId }
        }


        if(inputCheck(firstName, lastName, phoneNumber)&&check){
            // Create User Object
            val user = User(
                0,
                cafeId.toInt(),
                email,
                password,
                firstName,
                lastName,
                phoneNumber,
                true,
                role
            )
            // Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
            //activity?.supportFragmentManager?.popBackStack()
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, phoneNumber: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(phoneNumber))
    }

}