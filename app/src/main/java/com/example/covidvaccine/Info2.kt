package com.example.covidvaccine

import android.app.ActionBar
import android.os.Bundle
import android.view.*
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.activity.addCallback
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.covidvaccine.databinding.FragmentInfo2Binding


class Info2 : Fragment(R.layout.fragment_info2) {

    private lateinit var fragmentInfo2Binding: FragmentInfo2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding =FragmentInfo2Binding.bind(view)
        fragmentInfo2Binding=binding
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.switchmenu, menu)

    }

    fun engText(){
       fragmentInfo2Binding.coronaText.setText(resources.getString(R.string.aboutCovidEng))
    }

    fun baText(){
        fragmentInfo2Binding.coronaText.setText(resources.getString(R.string.aboutCovid))
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.engleski-> engText()
            R.id.bosanski->baText()
        }
        return super.onOptionsItemSelected(item)
    }



}

