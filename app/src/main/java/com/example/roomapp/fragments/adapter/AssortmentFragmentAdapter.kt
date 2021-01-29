package com.example.roomapp.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.fragments.list.AssortmentFragmentDirections
import com.example.roomapp.model.Assortment
import kotlinx.android.synthetic.main.assortment_row.view.*

class AssortmentFragmentAdapter: RecyclerView.Adapter<AssortmentFragmentAdapter.MyAssortmentViewHolder>() {

    private var assortmentList = emptyList<Assortment>()

    class MyAssortmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAssortmentViewHolder {
        return MyAssortmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.assortment_row, parent, false)
        )
    }

    override fun getItemCount() = assortmentList.size

    override fun onBindViewHolder(holder: MyAssortmentViewHolder, position: Int) {
        val currentItem = assortmentList[position]

        holder.itemView.apply {
            assortment_id_txt.text = currentItem.assortmentId.toString()
            name_txt.text = currentItem.name
            price_txt.text = currentItem.price
        }

        holder.itemView.rowLayout.setOnClickListener {
            val action =
                AssortmentFragmentDirections.actionAssortmentFragmentToUpdateAssortmentFragment(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)
        }
    }
    fun setAssortmentData(assortment: List<Assortment>){
        this.assortmentList = assortment
        notifyDataSetChanged()
    }

}
