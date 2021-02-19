package com.example.farmacylocation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.farmacylocation.databinding.FarmacyListBinding
import com.example.farmacylocation.local.FarmacyEntity

class FarmacyAdapter: RecyclerView.Adapter<FarmacyAdapter.FarmacyVH>() {

    private var listFarmacy = listOf<FarmacyEntity>()
    private val selectedFarmacyList = MutableLiveData<FarmacyEntity>()


    fun update (list: List<FarmacyEntity>){
        listFarmacy = list
        notifyDataSetChanged()
    }
    fun selectedFarmacyList() : LiveData<FarmacyEntity> = selectedFarmacyList

    inner class FarmacyVH (private val binding: FarmacyListBinding):
            RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind(itemFarmacy : FarmacyEntity){
            binding.tVFarmacy.text = itemFarmacy.commune
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
           selectedFarmacyList.value = listFarmacy[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmacyVH {
        return FarmacyVH(FarmacyListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FarmacyVH, position: Int) {
        val itemFarmacy = listFarmacy[position]
        holder.bind(itemFarmacy)
    }

    override fun getItemCount() = listFarmacy.size

}