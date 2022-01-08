package com.example.covidvaccine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.covidvaccine.databinding.FragmentPitanje2Binding
import java.util.*

class Pitanje2Fragment : Fragment(R.layout.fragment_pitanje2) {

    private var fragmentPitanje2Binding: FragmentPitanje2Binding?=null
    private val args:Pitanje2FragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPitanje2Binding.bind(view)
        fragmentPitanje2Binding=binding

        val user=args.username
        val pass=args.password
        val name=args.name
        val sur=args.surname


        val datePicker=binding.calendarView

        binding.buttonDateQ.setOnClickListener {
            val dan=datePicker.dayOfMonth.toString()
            val mjes=datePicker.month.toString()
            val god=datePicker.year.toString()
            val god2=datePicker.year.toInt()
            val datum=dan+"/"+mjes+"/"+god
            val age = godiste(god2)

            val action =Pitanje2FragmentDirections.actionPitanje2FragmentToPitanje3Fragment(user,pass,name,sur,age,datum)
            findNavController().navigate(action)

        }
    }


    fun godiste(god :Int): Int{
        val year = Calendar.getInstance().get(Calendar.YEAR).toInt()
        val age= year-god
        return age
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}