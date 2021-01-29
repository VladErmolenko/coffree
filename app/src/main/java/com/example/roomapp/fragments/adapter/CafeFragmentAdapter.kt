package com.example.roomapp.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.fragments.list.CafeFragmentDirections
import com.example.roomapp.model.Cafe
import kotlinx.android.synthetic.main.cafe_row.view.*
import kotlinx.android.synthetic.main.custom_row.view.rowLayout

class CafeFragmentAdapter: RecyclerView.Adapter<CafeFragmentAdapter.MyCafeViewHolder>() {

    private var cafeList = emptyList<Cafe>()

    class MyCafeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCafeViewHolder {
        return MyCafeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cafe_row, parent, false)
        )
    }


    override fun getItemCount(): Int {
        return cafeList.size
    }

    override fun onBindViewHolder(holder: MyCafeViewHolder, position: Int) {
        val currentItem = cafeList[position]
        holder.itemView.apply {
            cafe_id_txt.text = currentItem.cafeId.toString()
            cafe_name_txt.text = currentItem.name
            profit_txt.text = currentItem.profit

        }

        holder.itemView.rowLayout.setOnClickListener {
            val action =
                CafeFragmentDirections.actionCafeFragmentToUpdateCafeFragment22(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
    fun setCafeData(cafe: List<Cafe>){
        this.cafeList = cafe
        notifyDataSetChanged()
    }


}
