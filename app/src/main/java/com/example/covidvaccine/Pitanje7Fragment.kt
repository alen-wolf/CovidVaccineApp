package com.example.covidvaccine

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.covidvaccine.databinding.FragmentPitanje7Binding

class Pitanje7Fragment : Fragment(R.layout.fragment_pitanje7) {

    private var fragmentPitanje7Binding: FragmentPitanje7Binding?=null
    private val args: Pitanje7FragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentPitanje7Binding.bind(view)
        fragmentPitanje7Binding=binding

        binding.preb1.setOnClickListener {
            val preb=1
            goNext(preb)
        }
        binding.preb2.setOnClickListener {
            val preb=2
            goNext(preb)

        }
        binding.daAnswer3.setOnClickListener {
            val preb=3
            goNext(preb)

        }
        binding.nista2.setOnClickListener {
            val preb=4
            goNext(preb)
        }

    }

    fun goNext(preb :Int){
        val user=args.username
        val pass=args.password
        val name=args.name
        val sur=args.surname
        val age =args.age
        val date=args.date
        val gend=args.gender
        val prio=args.priority
        val hiv=args.HIV
        val immu=args.immuneSis

        val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
        val editor= pref?.edit()
        editor?.putBoolean("logovan",true)
        editor?.putString("USERNAME",user)
        editor?.putString("PASSWORD",pass)
        editor?.putString("NAME",name)
        editor?.putString("SURNAME",sur)
        editor?.putInt("AGE",age)
        editor?.putString("DATE",date)
        editor?.putString("GENDER",gend)
        editor?.putString("PRIORITY_GROUP",prio)
        editor?.putString("HIV",hiv)
        editor?.putString("IMMUNE_SYS",immu)
        editor?.putInt("OVER",preb)
        editor?.apply()

        val action =Pitanje7FragmentDirections.actionPitanje7FragmentToFinalInfoUser2()
        findNavController().navigate(action)
    }

}