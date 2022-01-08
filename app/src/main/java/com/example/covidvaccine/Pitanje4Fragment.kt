package com.example.covidvaccine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.covidvaccine.databinding.FragmentPitanje3Binding
import com.example.covidvaccine.databinding.FragmentPitanje4Binding


class Pitanje4Fragment : Fragment(R.layout.fragment_pitanje4) {

    private var fragmentPitanje4Binding: FragmentPitanje4Binding?=null
    private val args: Pitanje4FragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentPitanje4Binding.bind(view)
        fragmentPitanje4Binding=binding

        binding.nista.setOnClickListener {
            val prio="Niska prioritetska skupina"
            goNext(prio)
        }
        binding.radioMedical.setOnClickListener {
            val prio =binding.radioMedical.text.toString()
            goNext(prio)
        }
        binding.radioButtonVojno.setOnClickListener{
            val prio="Kardiovaskularni bolesnik"
            goNext(prio)
        }
        binding.radioKardio.setOnClickListener{
            val prio=binding.radioKardio.text.toString()
            goNext(prio)
        }

    }

    fun goNext(prio :String){
        val user=args.username
        val pass=args.password
        val name=args.name
        val sur=args.surname
        val age =args.age
        val date=args.date
        val gend=args.gender

        val action =Pitanje4FragmentDirections.actionPitanje4FragmentToPitanje5Fragment(user,pass,name,sur,age,date,gend,prio)
        findNavController().navigate(action)
    }

}