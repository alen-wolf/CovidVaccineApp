package com.example.covidvaccine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.covidvaccine.databinding.FragmentPitanje3Binding

class Pitanje3Fragment : Fragment(R.layout.fragment_pitanje3) {

    private var fragmentPitanje3Binding: FragmentPitanje3Binding?=null
    private val args: Pitanje3FragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPitanje3Binding.bind(view)
        fragmentPitanje3Binding=binding

        binding.radioButtonMale.setOnClickListener{
            val gender=binding.radioButtonMale.text.toString()
            goNext(gender)
        }
        binding.radioButtonFemale.setOnClickListener {
            val gender = binding.radioButtonFemale.text.toString()
            goNext(gender)
        }

    }

    fun goNext(gender :String){
        val user=args.username
        val pass=args.password
        val name=args.name
        val sur=args.surname
        val age =args.age
        val date=args.date
        val gend=gender

        val action =Pitanje3FragmentDirections.actionPitanje3FragmentToPitanje4Fragment(user,pass,name,sur,age,date,gend)
        findNavController().navigate(action)
    }

}