package com.example.covidvaccine

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.covidvaccine.databinding.FragmentBlankCovidBinding


class BlankCovid : Fragment(R.layout.fragment_blank_covid) {

    private lateinit var fragmentBlankCovidBinding: FragmentBlankCovidBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentBlankCovidBinding.bind(view)
        fragmentBlankCovidBinding=binding
        val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
        val editor= pref?.edit()

        binding.button4.setOnClickListener {
            val selected=binding.spinnerVac.selectedItem.toString()
            editor?.putString("VACCINE",selected)
            editor?.putBoolean("CHECKED",true)
            editor?.apply()

            val action=BlankCovidDirections.actionBlankCovidToCanTakeVaccine()
            findNavController().navigate(action)
        }
    }
}