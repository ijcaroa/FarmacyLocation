package com.example.farmacylocation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmacylocation.adapter.FarmacyAdapter
import com.example.farmacylocation.model.FarmacyViewModel
import com.example.farmacylocation.R
import com.example.farmacylocation.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding : FragmentFirstBinding
    private val viewModel: FarmacyViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val adapter = FarmacyAdapter()
        binding.FarmacyListRV.adapter = adapter
        binding.FarmacyListRV.layoutManager = LinearLayoutManager(context)
        binding.FarmacyListRV.addItemDecoration(DividerItemDecoration
            (context,DividerItemDecoration.VERTICAL))

    viewModel.getFarmacyList().observe(viewLifecycleOwner, Observer {
         it?.let {
             adapter.update(it)
         }
     })

   adapter.selectedFarmacyList().observe(viewLifecycleOwner, Observer {
        it?.let {
            viewModel.getNameBycomunaFromInternet(it.id,it.region,it.name,it.commune,
                    it.address,it.tel,it.lat,it.longi)
            findNavController().navigate(R.id.action_FirstFragment_to_comunasFragment)
        }
    })
    }
}