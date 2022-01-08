package com.example.covidvaccine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.covidvaccine.databinding.FragmentPitanje6Binding

class Pitanje6Fragment : Fragment(R.layout.fragment_pitanje6) {

    private var framentPitanje6Binding: FragmentPitanje6Binding?=null
    private val args: Pitanje6FragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPitanje6Binding.bind(view)
        framentPitanje6Binding=binding

        binding.daAnswer2.setOnClickListener {
            val da="Oslabljen imunitet"
            goNext(da)
        }
        binding.neAnswer2.setOnClickListener {
            val ne="Jak imunitet"
            goNext(ne)
        }
    }

    fun goNext(immu :String){
        val user=args.username
        val pass=args.password
        val name=args.name
        val sur=args.surname
        val age =args.age
        val date=args.date
        val gend=args.gender
        val prio=args.priority
        val hiv=args.HIV

        val action =Pitanje6FragmentDirections.actionPitanje6FragmentToPitanje7Fragment(user,pass,name,sur,age,date,gend,prio,hiv,immu)
        findNavController().navigate(action)
    }

}