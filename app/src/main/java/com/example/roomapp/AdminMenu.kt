package com.example.roomapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_admin_menu.*
import kotlinx.android.synthetic.main.fragment_admin_menu.view.*

class AdminMenu : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin_menu, container, false)

        view.go_cafes_btn.setOnClickListener {
            findNavController().navigate(R.id.action_adminMenu_to_cafeFragment)
        }

        view.go_to_employees_btn.setOnClickListener {
            findNavController().navigate(R.id.action_adminMenu_to_listFragment)
        }

        view.go_to_assortment_btn.setOnClickListener {

            findNavController().navigate(R.id.action_adminMenu_to_assortment)

        }

        view.go_to_realization_btn.setOnClickListener {

            findNavController().navigate(R.id.action_adminMenu_to_realizationFragment)

        }

        return view
    }

}