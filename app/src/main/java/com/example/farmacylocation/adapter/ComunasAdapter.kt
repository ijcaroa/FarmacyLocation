package com.example.farmacylocation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.farmacylocation.databinding.FarmacyListBinding
import com.example.farmacylocation.local.FarmacyEntity

class ComunasAdapter : RecyclerView.Adapter<ComunasAdapter.FarmacyLocalVH>() {

    private var listNames = listOf<FarmacyEntity>()
    private val selectedNames = MutableLiveData<FarmacyEntity>()

    fun selectedNamesItem() : LiveData<FarmacyEntity> = selectedNames

    fun update (list: List<FarmacyEntity>){
        listNames = list
        notifyDataSetChanged()
    }


    inner class FarmacyLocalVH (private val binding: FarmacyListBinding) :
            RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(comunasFarmacy : FarmacyEntity){
            binding.tVFarmacy.text = comunasFarmacy.name
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedNames.value = listNames[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmacyLocalVH {
        return FarmacyLocalVH(FarmacyListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FarmacyLocalVH, position: Int) {
        val comunasFarmacy = listNames[position]
        holder.bind(comunasFarmacy)
    }

    override fun getItemCount() = listNames.size
}