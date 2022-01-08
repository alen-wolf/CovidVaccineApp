package com.example.covidvaccine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class CantTakeVaccine : Fragment(R.layout.fragment_cant_take_vaccine) {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menuu, menu)
    }

    private fun shareSuccess() {
        val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
        val name=pref?.getString("NAME","Mujo")
        val sur=pref?.getString("SURNAME","Mujkic")
        val mesg="Ja "+name+" "+sur+" vas obavjestavam da nazalost nisam ispunio sve uslove za vakcinisanje!"
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, mesg)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home1-> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }


}