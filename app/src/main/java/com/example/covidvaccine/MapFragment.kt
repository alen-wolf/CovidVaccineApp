package com.example.covidvaccine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.covidvaccine.databinding.FragmentMapBinding


class MapFragment : Fragment(R.layout.fragment_map) {

    private lateinit var fragmentMapBinding: FragmentMapBinding
    private val infoSarajevo: GradData = GradData("Lokacija 1: Vrazova\nRadno vrijeme od 9 do 13\n \nLokacija 2: Vojna Bolnica\n Radno vrijeme od 8 do 11\n \nLokacija 3: KUM\n Radno vrijeme od 9 do 20")
    private val infoMostar: GradData = GradData("Lokacija 1: SKB Mostar\nRadno vrijeme od 8 do 21\n \nLokacija 2: MCentar 2\nRadno vrijeme od 8 do 14\n \nLokacija 3: CPIL\nRadno vrijeme od 9 do 20")
    private val infoBanjaluka: GradData = GradData("Lokacija 1: UKCRS\nRadno vrijeme od 8 do 22\n \nLokacija 2: Aleja-Centar\nRadno vrijeme od 7 do 14")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentMapBinding.bind(view)
        fragmentMapBinding=binding

        binding.spinnerTown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val sel = adapterView?.getItemAtPosition(position).toString()
                if (sel.equals("Sarajevo")) {
                    binding.mapContainer.setBackgroundResource(R.drawable.sarajevo_lokacija)
                    binding.lok=infoSarajevo

                } else if (sel.equals("Mostar")) {
                    binding.mapContainer.setBackgroundResource(R.drawable.mostar_lokacija)
                    binding.lok=infoMostar

                } else if (sel.equals("Banja Luka")) {
                    binding.mapContainer.setBackgroundResource(R.drawable.banjaluka_lokacija)
                    binding.lok=infoBanjaluka

                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Empty
            }
        }


    }

}