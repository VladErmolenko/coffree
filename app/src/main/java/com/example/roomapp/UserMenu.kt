package com.example.roomapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_user_menu.*
import kotlinx.android.synthetic.main.fragment_user_menu.view.*

class UserMenu : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_menu, container, false)

        view.go_to_assortment_user_btn.setOnClickListener {
            findNavController().navigate(R.id.action_userMenu_to_assortmentFragment)
        }

        view.go_to_realization_user_btn.setOnClickListener {
            findNavController().navigate(R.id.action_userMenu_to_realizationFragment)
        }

        return view
    }

}