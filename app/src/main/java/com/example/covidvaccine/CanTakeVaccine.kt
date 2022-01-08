package com.example.covidvaccine

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import android.util.Log
import android.view.*
import android.view.Gravity.apply
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.Person.fromBundle
import androidx.core.view.GravityCompat.apply
import androidx.fragment.app.Fragment
import androidx.media.AudioAttributesCompat.fromBundle
import androidx.navigation.fragment.findNavController
import com.example.covidvaccine.databinding.FragmentBlankCovidBinding
import com.example.covidvaccine.databinding.FragmentCanTakeVaccineBinding
import timber.log.Timber

const val KEY_SPASENO="spaseno_key"

class CanTakeVaccine : Fragment(R.layout.fragment_can_take_vaccine) {


    private lateinit var vaccineTimer: VaccineTimer
    private var fragmentCanTakeVaccineBinding: FragmentCanTakeVaccineBinding?=null
    private var tren=0
    private var saved="1337"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        Timber.i("Fragment created")

        vaccineTimer= VaccineTimer(this.lifecycle)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentCanTakeVaccineBinding.bind(view)
        fragmentCanTakeVaccineBinding=binding
        Timber.i("View infalted")

        val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
        val vactext=pref?.getString("VACCINE","Astra Zeneca")
        val texter="Vasa vakcina je: "+vactext
        binding.textView6.setText(texter)

        if(savedInstanceState!=null){
            saved=savedInstanceState.getString(KEY_SPASENO,"")
        }else{
            saved= pref?.getString(KEY_SPASENO,"1337").toString()
        }
        binding.regUser.setText(saved)

        binding.vacinaEdit.setOnClickListener {
            showListAlertDialogVaccine()
        }

        binding.refreshButton.setOnClickListener {
            val time=vaccineTimer.secondsCount
            val proslo=time/15
            if (proslo>tren){
                var get=binding.regUser.text.toString().toInt()
                val raz=proslo-tren
                get+=raz
                binding.regUser.setText(get.toString())
                tren=proslo
                saved=get.toString()
            }
        }

    }

    fun showListAlertDialogVaccine(){
        val builder= AlertDialog.Builder(this.requireActivity())
        builder.setTitle("Izaberi Vakcinu")
        val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
        val editor= pref?.edit()

        val vaccineList =arrayOf("Astra Zeneca","Phizer","Moderna","BioNTech","Sputnik")
        builder.setItems(vaccineList) { _, which->
            editor?.putString("VACCINE",vaccineList[which])
            editor?.apply()
            val texter="Vasa vakcina je: "+vaccineList[which]
            fragmentCanTakeVaccineBinding?.textView6?.setText(texter)
        }

        val dialog=builder.create()
        dialog.show()
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menuu, menu)
    }

    private fun shareSuccess() {
        val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
        val name=pref?.getString("NAME","Mujo")
        val sur=pref?.getString("SURNAME","Mujkic")
        val vac=pref?.getString("VACCINE","Astra Zneca")
        val mesg="Ja "+name+" "+sur+" vas obavjestavam da sam ispunio sve uslove za vakcinisanje vakcinom "+vac
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




    override fun onResume() {
        super.onResume()
        Timber.i("Fragment Resumed")
    }


    override fun onPause(){
        super.onPause()
        Timber.i("Fragment paused")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("Fragment onSaveInstanceCalled")
        outState.putString(KEY_SPASENO,saved)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("Fragment destroyed")
        val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
        val editor= pref?.edit()
        editor?.putString(KEY_SPASENO,saved)
        editor?.apply()
    }
}

