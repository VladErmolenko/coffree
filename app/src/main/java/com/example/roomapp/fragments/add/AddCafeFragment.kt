package com.example.roomapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.model.Cafe
import com.example.roomapp.viewmodel.CafeViewModel
import kotlinx.android.synthetic.main.fragment_add_cafe.*
import kotlinx.android.synthetic.main.fragment_add_cafe.view.*

class AddCafeFragment : Fragment() {

    private lateinit var mCafeViewModel: CafeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_cafe, container, false)

        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)

        view.add_cafe_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val address = addAddress.text.toString()
        val name = addCafeName.text.toString()
        val profit = addProfit.text.toString()

        if(inputCheck(address, name, profit)){
            // Create User Object
            val cafe = Cafe(
                0,
                address,
                name,
                profit
            )
            // Add Data to Database
            mCafeViewModel.addCafe(cafe)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addCafeFragment_to_cafeFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(address: String, name: String, profit: String): Boolean {
        return !(TextUtils.isEmpty(address) && TextUtils.isEmpty(name) && TextUtils.isEmpty(profit))
    }




}
