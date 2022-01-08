package com.example.covidvaccine

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.covidvaccine.databinding.FragmentLogInBinding


class Log_in : Fragment(R.layout.fragment_log_in) {

    private var fragmentLogInBinding: FragmentLogInBinding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLogInBinding.bind(view)
        fragmentLogInBinding=binding


        binding.button2.setOnClickListener {
            hideKeypad()
            val inputManager= activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken,0)

            val moze=popunjeno()
            if (!moze){
                val user = binding.userName.text.toString()
                val pass = binding.pass1.text.toString()
                val action = Log_inDirections.actionLogInToAboutCovid(user,pass)
                findNavController().navigate(action)
            }else{
                Toast.makeText(this.activity,"Molimo popunite sva polja i korektno ponovite vasu sifru!",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun hideKeypad(){
        val inputManager= activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken,0)
    }

    fun popunjeno(): Boolean {
        return !(fragmentLogInBinding?.userName?.text.toString().trim().length>0 && (fragmentLogInBinding?.pass1?.text.toString().trim().length>0) && (fragmentLogInBinding?.pass2?.text.toString().trim().length>0) &&(fragmentLogInBinding?.pass1?.text.toString().equals(fragmentLogInBinding?.pass2?.text.toString())))
    }
}