package com.example.roomapp

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
import com.example.roomapp.model.Cafe
import com.example.roomapp.model.User
import com.example.roomapp.viewmodel.CafeViewModel
import com.example.roomapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*

class RegistrationFragment : Fragment() {

    private  lateinit var mUserViewModel:UserViewModel

    private lateinit var mCafeViewModel:CafeViewModel

    private var cafeList = emptyList<Cafe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)
        mCafeViewModel.readAllCafeData.observe(viewLifecycleOwner, Observer { cafe ->
            cafeList=cafe
        })

        view.reg_user_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val email = addEmailReg_et.text.toString()
        val password = addPasswordReg_et.text.toString()
        val cafeId = addCafeIDReg.text.toString()
        val firstName = addFirstNameReg_et.text.toString()
        val lastName = addLastNameReg_et.text.toString()
        val phoneNumber = addPhoneNumberReg_et.text.toString()
        var role = false
        role = addRoleReg_et.text.toString() != "0"

        var check=false

        for(i in cafeList.indices)
        {
            if(cafeId.toInt()==cafeList[i].cafeId)
                check=true
            else
                continue
        }

        if(inputCheck(email, password, firstName, lastName)&&cafeList.isEmpty()){

            val genericCafe = Cafe(
                0,
                "some address",
                "some name",
                "0"
            )

            mCafeViewModel.addCafe(genericCafe)

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
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_registrationFragment_to_authorization)


        }
        if(inputCheck(email,password,firstName, lastName)&&check){
            // Create User Object
            val user = User(
                0,
                cafeId.toInt(),
                email,
                password,
                firstName,
                lastName,
                phoneNumber,
                false,
                role
            )
            // Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_registrationFragment_to_authorization)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(email: String, password: String, firstName: String,lastName:String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(email)&&TextUtils.isEmpty(password))
    }

}