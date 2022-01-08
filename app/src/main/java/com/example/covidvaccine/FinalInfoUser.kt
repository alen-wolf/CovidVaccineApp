package com.example.covidvaccine

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.covidvaccine.databinding.FragmentFinalInfoUserBinding
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController


class FinalInfoUser : Fragment(R.layout.fragment_final_info_user) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private var fragmentFinalInfoUserBinding: FragmentFinalInfoUserBinding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentFinalInfoUserBinding.bind(view)
        fragmentFinalInfoUserBinding=binding
        userInfo()

        binding.imeButton.setOnClickListener {
            val vred=binding.imeButton.contentDescription.toString()
            val key=binding.textView4.contentDescription.toString()
            editDialogText(vred,key)
        }

        binding.prezimeButton.setOnClickListener{
            val vred=binding.prezimeButton.contentDescription.toString()
            val key=binding.textView11.contentDescription.toString()
            editDialogText(vred, key)
        }

        binding.spolButton.setOnClickListener{
            val vred=binding.spolButton.contentDescription.toString()
            val key=binding.textView12.contentDescription.toString()
            showListAlertDialog(vred,key)
        }

        binding.prioButton.setOnClickListener {
            val vred=binding.prioButton.contentDescription.toString()
            val key=binding.textView16.contentDescription.toString()
            showListAlertDialog(vred,key)
        }

        binding.hivButton.setOnClickListener {
            val vred=binding.hivButton.contentDescription.toString()
            val key=binding.textView19.contentDescription.toString()
            showListAlertDialog(vred,key)
        }

        binding.immuButton.setOnClickListener {
            val vred=binding.immuButton.contentDescription.toString()
            val key=binding.textView18.contentDescription.toString()
            showListAlertDialog(vred, key)
        }

        binding.covidButton.setOnClickListener {
            val vred=binding.covidButton.contentDescription.toString()
            val key=binding.textView20.contentDescription.toString()
            showListAlertDialog(vred, key)
        }

        binding.testButton.setOnClickListener {
            testCovid()
        }
    }


    fun showListAlertDialog(vred :String,key :String){
        val builder=AlertDialog.Builder(this.requireActivity())
        builder.setTitle(vred)
        val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
        val editor= pref?.edit()

        if (key.equals("1")){
            val genderList =arrayOf("Musko","Zensko")
            builder.setItems(genderList) { _, which->
                editor?.putString("GENDER",genderList[which])
                editor?.apply()
                userInfo()
            }
        }else if (key.equals("2")){
            val prioList= arrayOf("Medicinski radnik","Kardiovaskularni bolesnik","Vojno lice","Nista od navedenog")
            builder.setItems(prioList){_,which->
                val ln=prioList[which]
                if (ln.equals("Nista od navedenog")){
                    editor?.putString("PRIORITY_GROUP","Niska prioritetska skupina")
                    editor?.apply()
                    userInfo()
                }else{
                    editor?.putString("PRIORITY_GROUP",ln)
                    editor?.apply()
                    userInfo()
                }
            }
        }else if(key.equals("3")){
            val hivList= arrayOf("Da","Ne")
            builder.setItems(hivList){_,which->
                editor?.putString("HIV",hivList[which])
                editor?.apply()
                userInfo()
            }
        }else if(key.equals("4")){
            val immuList= arrayOf("Da","Ne")
            builder.setItems(immuList){_,which->
                val ln=immuList[which]
                if (ln.equals("Da")){
                    editor?.putString("IMMUNE_SYS","Jak imunitet")
                    editor?.apply()
                    userInfo()
                }else if(ln.equals("Ne")){
                    editor?.putString("IMMUNE_SYS","Oslabljen imunitet")
                    editor?.apply()
                    userInfo()
                }
            }
        }else if(key.equals("5")){
            val covidList= arrayOf("Da imam trenutno","Prebolio sam koronu prije MANJE od 3 mjeseca","Prebolio sam koronu prije VISE od 3 mjeseca","Nisam nikad imao Covid-19")
            builder.setItems(covidList){_,which->
                val ln=covidList[which]
                if (ln.equals("Da imam trenutno")){
                    editor?.putInt("OVER",3)
                    editor?.apply()
                    userInfo()
                }else if(ln.equals("Prebolio sam koronu prije MANJE od 3 mjeseca")){
                    editor?.putInt("OVER",1)
                    editor?.apply()
                    userInfo()
                }else if(ln.equals("Prebolio sam koronu prije VISE od 3 mjeseca")){
                    editor?.putInt("OVER",2)
                    editor?.apply()
                    userInfo()
                }else if(ln.equals("Nisam nikad imao Covid-19")){
                    editor?.putInt("OVER",4)
                    editor?.apply()
                    userInfo()
                }
            }
        }

        val dialog=builder.create()
        dialog.show()
    }


    fun editDialogText(vred:String,key: String){
        val mDialogView=LayoutInflater.from(this.requireActivity()).inflate(R.layout.edit_text_layout,null)
        val builder= AlertDialog.Builder(this.requireActivity())
            .setView(mDialogView)
            .setTitle(vred)
        val mAlertDialog=builder.show()

        mDialogView.findViewById<Button>(R.id.dialog_edit).setOnClickListener {
            hideKeypad()
            mAlertDialog.dismiss()
            val newInput=mDialogView.findViewById<EditText>(R.id.edit_text_dialog).text.toString()
            val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
            val editor= pref?.edit()
            editor?.putString(key,newInput)
            editor?.apply()
            userInfo()
        }

        mDialogView.findViewById<Button>(R.id.dialog_edit2).setOnClickListener {
            hideKeypad()
            mAlertDialog.dismiss()
        }
    }


    fun userInfo(){
        val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
        fragmentFinalInfoUserBinding?.textView4?.text = pref?.getString("NAME","")
        fragmentFinalInfoUserBinding?.textView11?.text = pref?.getString("SURNAME","")
        fragmentFinalInfoUserBinding?.textView12?.text = pref?.getString("GENDER","")
        val god=pref?.getInt("AGE",0)
        val date=pref?.getString("DATE","")
        val god1=god.toString()
        val full=god1+"g"+" "+date
        fragmentFinalInfoUserBinding?.textView13?.text = full

        val prio=pref?.getString("PRIORITY_GROUP","")
        if (prio.equals("Nista od navedenog")) {
            fragmentFinalInfoUserBinding?.textView16?.text = "Niska prioritetska skupina"
        }else{
            fragmentFinalInfoUserBinding?.textView16?.text = prio
        }

        val hiv=pref?.getString("HIV","")
        if(hiv.equals("Da")) {
            fragmentFinalInfoUserBinding?.textView19?.text = "HIV POSITIVE"
        } else if (hiv.equals("Ne")) {
            fragmentFinalInfoUserBinding?.textView19?.text = "HIV NEGATIVE"
        }

        fragmentFinalInfoUserBinding?.textView18?.text = pref?.getString("IMMUNE_SYS","")
        val kor=pref?.getInt("OVER",0)
        if (kor==1){
            val cov="Prebolio koronu/manje od 3 mjeseca"
            fragmentFinalInfoUserBinding?.textView20?.text = cov
        }else if(kor==2){
            val cov="Prebolio koronu/vise od 3 mjeseca"
            fragmentFinalInfoUserBinding?.textView20?.text = cov
        }else if(kor==3){
            val cov="Covid-19 POSITIVE"
            fragmentFinalInfoUserBinding?.textView20?.text = cov
        }else if(kor==4){
            val cov="Covid-19 NEGATIVE"
            fragmentFinalInfoUserBinding?.textView20?.text = cov
        }else{
            val cov="ERROR"
            fragmentFinalInfoUserBinding?.textView20?.text = cov
        }

    }

    fun testCovid(){
        val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
        val god=pref?.getInt("AGE",0)
        val prio=fragmentFinalInfoUserBinding?.textView16?.text.toString()
        val immu=fragmentFinalInfoUserBinding?.textView18?.text.toString()
        val hiv=fragmentFinalInfoUserBinding?.textView19?.text.toString()
        val cov=fragmentFinalInfoUserBinding?.textView20?.text.toString()
        val chek=pref?.getBoolean("CHECKED",false)

        if ((prio.equals("Niska prioritetska skupina") && god!! < 70 ) || immu.equals("Oslabljen imunitet") || hiv.equals("HIV POSITIVE") || cov.equals("Covid-19 POSITIVE") || cov.equals("Prebolio koronu/manje od 3 mjeseca"))
            goNextneg()
        else if (chek==false)
            goNextpoz()
        else if (chek==true)
            goNextend()
    }

    fun goNextend(){
        val action=FinalInfoUserDirections.actionFinalInfoUser2ToCanTakeVaccine()
        findNavController().navigate(action)
    }

    fun goNextpoz(){
        val action=FinalInfoUserDirections.actionFinalInfoUser2ToBlankCovid()
        findNavController().navigate(action)
    }

    fun goNextneg(){
        val action=FinalInfoUserDirections.actionFinalInfoUser2ToCantTakeVaccine()
        findNavController().navigate(action)
    }


    fun hideKeypad(){
        val inputManager= activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken,0)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.mymenu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_home -> {
                view?.let { Navigation.findNavController(it).navigate(R.id.action_finalInfoUser2_to_info2) }
                true
            }
            R.id.nav_gallery -> {
                // save profile changes
                view?.let { Navigation.findNavController(it).navigate(R.id.action_finalInfoUser2_to_log_in2) }
                true
            }
            R.id.nav_location->{
                view?.let { Navigation.findNavController(it).navigate(R.id.action_finalInfoUser2_to_mapFragment) }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}