package com.example.roomapp.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.model.Assortment
import com.example.roomapp.model.Cafe
import com.example.roomapp.model.Realization
import com.example.roomapp.model.User
import com.example.roomapp.viewmodel.AssortmentViewModel
import com.example.roomapp.viewmodel.CafeViewModel
import com.example.roomapp.viewmodel.RealizationViewModel
import com.example.roomapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_realization.*
import kotlinx.android.synthetic.main.fragment_add_realization.view.*


class AddRealizationFragment : Fragment() {

    private lateinit var mRealizationViewModel: RealizationViewModel

    private lateinit var mAssortmentViewModel: AssortmentViewModel

    private lateinit var mCafeViewModel: CafeViewModel

    private lateinit var mUserViewModel: UserViewModel

    private var assortmentList = emptyList<Assortment>()

    private var cafeList = emptyList<Cafe>()

    private var userList = emptyList<User>()

    private var assortmentIndex = 0


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_realization, container, false)

        mRealizationViewModel = ViewModelProvider(this).get(RealizationViewModel::class.java)

        mAssortmentViewModel = ViewModelProvider(this).get(AssortmentViewModel::class.java)
        mAssortmentViewModel.readAllAssorment.observe(viewLifecycleOwner, Observer { assortment ->
            assortmentList = assortment
        })

        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)
        mCafeViewModel.readAllCafeData.observe(viewLifecycleOwner, Observer { cafe ->
            cafeList = cafe
        })

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java).apply {
            readAllData.observe(viewLifecycleOwner, Observer { user ->
                userList = user
            })
        }

        view.add_realization_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val date = addRealizationDate.text.toString()
        val note = addRealizationNotes.text.toString()
        val assortmentId = addAssortmentId.text.toString()
        val employeeId = addEmployeeId.text.toString()
        val cafeId = addCafeId.text.toString()


        if (inputCheck(date) && assortmentCheck(assortmentId) && cafeCheck(cafeId, assortmentIndex)
                && employeeCheck(employeeId)) {

            val realization = Realization(
                    0,
                    assortmentId.toInt(),
                    employeeId.toInt(),
                    cafeId.toInt(),
                    date,
                    note
            )


            mRealizationViewModel.addRealization(realization)



            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addRealizationFragment_to_realizationFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                    .show()
        }
    }

    private fun assortmentCheck(assortmentId: String): Boolean {
        // use find
        for (i in assortmentList.indices) {
            if (assortmentId.toInt() == assortmentList[i].assortmentId) {
                assortmentIndex = i
                return true
            }
        }
        return false
    }

    private fun cafeCheck(cafeId: String, assortmentIndex: Int): Boolean {




        for (i in cafeList.indices) {
            if (cafeId.toInt() == cafeList[i].cafeId) {
                val newProfit = cafeList[i].profit.toInt() + assortmentList[assortmentIndex].price.toInt()
                val updatedCafe = Cafe(
                        cafeList[i].cafeId,
                        cafeList[i].address,
                        cafeList[i].name,
                        newProfit.toString()
                )

                mCafeViewModel.updateCafe(updatedCafe)

                return true
            } else
                continue
        }
        return false
    }

    private fun employeeCheck(employeeId: String): Boolean {
        // use any
        for (i in userList.indices) {
            if (employeeId.toInt() == userList[i].id)
                return true
            else
                continue
        }
        return false
    }

    private fun inputCheck(date: String): Boolean {
        return true//Implement
    }

}