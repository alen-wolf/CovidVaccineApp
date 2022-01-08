package com.example.covidvaccine
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.system.Os.bind
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.FitWindowsFrameLayout
import androidx.databinding.DataBindingUtil.bind
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController

import com.example.covidvaccine.R.layout
import com.example.covidvaccine.databinding.FragmentMainBinding
import kotlin.math.log


class MainFragment : Fragment(R.layout.fragment_main) {

    private var fragmentMainBinding: FragmentMainBinding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding =FragmentMainBinding.bind(view)
        fragmentMainBinding=binding

        val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
        val logged = pref?.getBoolean("logovan",false)

        binding.button.setOnClickListener{
            if (logged == true){
                val action = MainFragmentDirections.actionMainFragmentToLogIn2()
                findNavController().navigate(action)
            }else{
               val action = MainFragmentDirections.actionMainFragmentToLogIn()
               findNavController().navigate(action)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}


