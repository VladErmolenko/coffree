package com.example.roomapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.R
import com.example.roomapp.fragments.adapter.CafeFragmentAdapter
import com.example.roomapp.viewmodel.CafeViewModel
import kotlinx.android.synthetic.main.fragment_cafe.view.*
import kotlinx.android.synthetic.main.fragment_list.view.recyclerview


class CafeFragment : Fragment() {

    private lateinit var mCafeViewModel: CafeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view =  inflater.inflate(R.layout.fragment_cafe, container, false)

        // Recyclerview
        val adapter = CafeFragmentAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        mCafeViewModel = ViewModelProvider(this).get(CafeViewModel::class.java)
        mCafeViewModel.readAllCafeData.observe(viewLifecycleOwner, Observer { cafe ->
            adapter.setCafeData(cafe)
        })

        view.floatingCafeActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_cafeFragment_to_addCafeFragment)
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_cafe_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.cafe_menu_delete){
            deleteAllCafe()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllCafe() {
        AlertDialog.Builder(requireContext())
                .setPositiveButton("Yes") { _, _ ->
            mCafeViewModel.deleteAllCafe()
            Toast.makeText(
                requireContext(),
                "Successfully removed everything",
                Toast.LENGTH_SHORT).show()
        }
        .setNegativeButton("No") { _, _ -> }
        .setTitle("Delete everything?")
        .setMessage("Are you sure you want to delete everything?")
        .create().show()
    }



}
