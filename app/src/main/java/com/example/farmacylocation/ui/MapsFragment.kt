package com.example.farmacylocation.ui

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.farmacylocation.R
import com.example.farmacylocation.model.FarmacyViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private val viewModel : FarmacyViewModel by activityViewModels()

    private var latitud : Double = 0.0
    private var longitud: Double = 0.0
    private var idFarmacy: String = ""
    private var nombre:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            idFarmacy = it.getString("id", "")
            nombre = it.getString("nombre","")

        }
    }
    private val callback = OnMapReadyCallback { googleMap ->

        val ubicationCoordinate = LatLng(latitud,longitud)
        googleMap.addMarker(MarkerOptions().position(ubicationCoordinate).title("Farmacia $nombre "))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ubicationCoordinate))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicationCoordinate,18f),null

        )
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?


        viewModel.getFarmacyId(idFarmacy).observe(viewLifecycleOwner, Observer {
            Log.d("Maps", "$it")

            it?.let {

              latitud = it.lat.toDouble()
              longitud = it.longi.toDouble()
                Log.d("Maps", "${latitud}-${longitud}")
                mapFragment?.getMapAsync(callback)
            }
        })

    }
}