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
import com.example.roomapp.model.Assortment
import com.example.roomapp.viewmodel.AssortmentViewModel
import kotlinx.android.synthetic.main.assortment_row.*
import kotlinx.android.synthetic.main.fragment_add_assortment.*
import kotlinx.android.synthetic.main.fragment_add_assortment.view.*

class AddAssortmentFragment : Fragment() {

    private lateinit var mAssortmentViewModel: AssortmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_assortment, container, false)

        mAssortmentViewModel = ViewModelProvider(this).get(AssortmentViewModel::class.java)

        view.add_assortment_btn.setOnClickListener {
            insertDataToDatabase()
        }


        return view
    }

    private fun insertDataToDatabase() {
        val name = add_name_et.text.toString()
        val price = add_price_et.text.toString()
        val description = add_description_et.text.toString()


        if (inputCheck(name, price)) {
            // Create User Object
            val assortment = Assortment(
                0,
                name,
                price,
                description
            )
            // Add Data to Database
            mAssortmentViewModel.addAssortment(assortment)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addAssortmentFragment_to_assortmentFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(name: String, price: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(price))
    }
}