package com.example.roomapp.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.fragments.list.RealizationFragmentDirections
import com.example.roomapp.model.Realization
import kotlinx.android.synthetic.main.realization_row.view.*

class RealizationFragmentAdapter: RecyclerView.Adapter<RealizationFragmentAdapter.MyRealizationViewHolder>()  {

    private var realizationList = emptyList<Realization>()

    class MyRealizationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRealizationViewHolder {
        return MyRealizationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.realization_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return realizationList.size
    }

    override fun onBindViewHolder(holder: MyRealizationViewHolder, position: Int) {
        val currentItem = realizationList[position]
        holder.itemView.apply {
            realization_id_txt.text = currentItem.realizationId.toString()
            realization_date_txt.text = currentItem.date
            realization_note_txt.text = currentItem.notes
            realization_employee.text = currentItem.realizationEmployeeId.toString()
            realization_cafe.text = currentItem.realizationCafeId.toString()
            realization_assortment.text = currentItem.realizationAssortmentId.toString()
        }


        holder.itemView.rowLayout.setOnClickListener {
            val action =
                RealizationFragmentDirections.actionRealizationFragmentToUpdateRealizationFragment(
                    currentItem
                )
            holder.itemView.findNavController().navigate(action)
        }


    }
    fun setAssortmentData(realization: List<Realization>){
        this.realizationList = realization
        notifyDataSetChanged()
    }



}

/*
class AssortmentFragmentAdapter: RecyclerView.Adapter<AssortmentFragmentAdapter.MyAssortmentViewHolder>() {

    private var assortmentList = emptyList<Assortment>()

    class MyAssortmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAssortmentViewHolder {
        return MyAssortmentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.assortment_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return assortmentList.size
    }

    override fun onBindViewHolder(holder: MyAssortmentViewHolder, position: Int) {
        val currentItem = assortmentList[position]
        holder.itemView.assortment_id_txt.text = currentItem.assortmentId.toString()
        holder.itemView.coffee_pockets_txt.text = currentItem.coffeePockets.toString()
        holder.itemView.chocolate_bars_txt.text = currentItem.chocolateBars.toString()

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
 */