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
import com.example.farmacylocation.adapter.ComunasAdapter
import com.example.farmacylocation.model.FarmacyViewModel
import com.example.farmacylocation.R
import com.example.farmacylocation.databinding.FragmentComunasBinding

class ComunasFragment : Fragment() {

    private lateinit var binding: FragmentComunasBinding
    private val viewModel : FarmacyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentComunasBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val adapter = ComunasAdapter()
        binding.comunasRV.adapter = adapter
        binding.comunasRV.layoutManager = LinearLayoutManager(context)
        binding.comunasRV.addItemDecoration(DividerItemDecoration
            (context,DividerItemDecoration.VERTICAL))

        viewModel.getNames().observe(viewLifecycleOwner, Observer {
            it?.let {
            adapter.update(it)
            }
        })


        adapter.selectedNamesItem().observe(viewLifecycleOwner, Observer {
            it?.let {


                val bundle = Bundle()
                bundle.putString("nombre", it.name)
                bundle.putString("comuna",it.commune)
                bundle.putString("direccion", it.address)
                bundle.putString("telefono", it.tel)

                findNavController().navigate(R.id.action_comunasFragment_to_SecondFragment,bundle)
            }
        })
    }
}