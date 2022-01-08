package com.example.covidvaccine
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.system.Os.bind
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
import androidx.navigation.fragment.navArgs

import com.example.covidvaccine.R.layout
import com.example.covidvaccine.databinding.FragmentAboutCovidBinding
import com.example.covidvaccine.databinding.FragmentMainBinding
import com.example.covidvaccine.databinding.FragmentPitanje1Binding


class Pitanje1Fragment : Fragment(R.layout.fragment_pitanje1) {

    private var fragmentPitanje1Binding: FragmentPitanje1Binding?=null
    private val args:Pitanje1FragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPitanje1Binding.bind(view)
        fragmentPitanje1Binding=binding

        val user=args.username
        val pass=args.password

        binding.buttonStartQ.setOnClickListener {
            hideKeypad()
            val moze=popunjeno()
            if (!moze){
                val name=binding.EditQ1Ime.text.toString()
                val surname=binding.EditQ2Prezime.text.toString()
                val action=Pitanje1FragmentDirections.actionPitanje1FragmentToPitanje2Fragment(user,pass,name,surname)
                findNavController().navigate(action)
            }else{
                Toast.makeText(this.activity,"Molimo popunite sva polja!!",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun popunjeno(): Boolean {
        return !(fragmentPitanje1Binding?.EditQ1Ime?.text.toString().trim().length>0 && (fragmentPitanje1Binding?.EditQ2Prezime?.text.toString().trim().length>0))
    }

    fun hideKeypad(){
        val inputManager= activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken,0)
    }

}


