package com.example.farmacylocation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.farmacylocation.model.FarmacyViewModel
import com.example.farmacylocation.R
import com.example.farmacylocation.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel : FarmacyViewModel by activityViewModels()
    private var nombre : String = ""
    private var comuna : String = ""
    private var direccion : String =""
    private var telefono : String = ""
    private var latitud : String = ""
    private var longitud : String = ""
    private var idFarmacy : String = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nombre = it.getString("nombre", "")
            comuna = it.getString("comuna","")
            direccion = it.getString("direccion", "")
            telefono = it.getString("telefono", "")
            latitud = it.getString("latitud", "")
            longitud = it.getString("longitud","")
            idFarmacy = it.getString("id", "")
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.buttonUbication.setOnClickListener {
            val bundle = Bundle()
            bundle.putString ("id", idFarmacy)
            bundle.putString("nombre", nombre)

            findNavController().navigate(R.id.action_SecondFragment_to_mapsFragment,bundle)

        }

                binding.textVName.text = nombre
                binding.textVComuna.text = comuna
                binding.textVAddress.text = direccion
                binding.textVTelefono.text = telefono

                binding.buttonSecond.setOnClickListener {
                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                    

        }

    }
}