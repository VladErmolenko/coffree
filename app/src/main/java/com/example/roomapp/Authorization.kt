package com.example.roomapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.model.Cafe
import com.example.roomapp.model.User
import com.example.roomapp.viewmodel.CafeViewModel
import com.example.roomapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_authorization.*
import kotlinx.android.synthetic.main.fragment_authorization.view.*

class Authorization : Fragment() {

    private var userList = emptyList<User>()

    private lateinit var allUsers: UserViewModel

    private lateinit var editEmail: EditText

    private lateinit var editPassword: EditText

    private lateinit var mUserViewModel: UserViewModel

    private lateinit var mCafeViewModel: CafeViewModel

    private var users: List<User> = emptyList()

    private var cafes: List<Cafe> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_authorization, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            users = user
        })

        view.login_btn.setOnClickListener {
            if (login() == 2)
                findNavController().navigate(R.id.action_authorization_to_adminMenu)
            else if (login() == 1)
                findNavController().navigate(R.id.action_authorization_to_userMenu)
        }

        view.register_btn.setOnClickListener {
            findNavController().navigate(R.id.action_authorization_to_registrationFragment)
        }

        return view
    }

    fun login(): Int {
        val email = enter_email.text.toString()
        val password = enter_password.text.toString()

        for (i in users.indices) {
            if (users[i].email == email && users[i].password == password) {
                if (users[i].access) {
                    Toast.makeText(requireContext(), "Access granted", Toast.LENGTH_LONG)
                        .show()
                    return if (users[i].role)
                        2
                    else
                        1
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Admin didn't prove your account",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    return 0
                }
            }
        }
        Toast.makeText(requireContext(), "Unknown email of incorrect password", Toast.LENGTH_LONG)
            .show()
        return 0

    }
}