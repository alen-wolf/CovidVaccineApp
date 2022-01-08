package com.example.covidvaccine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.covidvaccine.databinding.FragmentPitanje5Binding

class Pitanje5Fragment : Fragment(R.layout.fragment_pitanje5) {

    private var fragmentPitanje5Binding: FragmentPitanje5Binding?=null
    private val args:Pitanje5FragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentPitanje5Binding.bind(view)
        fragmentPitanje5Binding=binding

        binding.daAnswer1.setOnClickListener {
            val da=binding.daAnswer1.text.toString()
            goNext(da)
        }
        binding.neAnswer1.setOnClickListener {
            val ne=binding.neAnswer1.text.toString()
            goNext(ne)
        }
    }


    fun goNext(hiv :String){
        val user=args.username
        val pass=args.password
        val name=args.name
        val sur=args.surname
        val age =args.age
        val date=args.date
        val gend=args.gender
        val prio=args.priority

        val action =Pitanje5FragmentDirections.actionPitanje5FragmentToPitanje6Fragment(user,pass,name,sur,age,date,gend,prio,hiv)
        findNavController().navigate(action)
    }

}