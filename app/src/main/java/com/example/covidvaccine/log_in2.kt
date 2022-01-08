package com.example.covidvaccine

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.covidvaccine.databinding.FragmentLogIn2Binding

class log_in2 : Fragment(R.layout.fragment_log_in2) {

    private var fragmentLogIn2Binding: FragmentLogIn2Binding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentLogIn2Binding.bind(view)
        fragmentLogIn2Binding=binding

        val pref= this.activity?.getPreferences(Context.MODE_PRIVATE)
        val user1=pref?.getString("USERNAME","")
        val pass1=pref?.getString("PASSWORD","")

        binding.button2.setOnClickListener {
            hideKeypad()
            val username=binding.userLog.text.toString()
            val password=binding.passLog.text.toString()

            if(username.equals(user1) && password.equals(pass1)){
                val action=log_in2Directions.actionLogIn2ToFinalInfoUser2()
                findNavController().navigate(action)
            }else{
                Toast.makeText(this.activity,"Pogršno korisničko Ime ili Password!", Toast.LENGTH_LONG).show()
            }
        }

        binding.deleteUser.setOnClickListener{
            val editor=pref?.edit()
            editor?.clear()
            editor?.apply()
            val action=log_in2Directions.actionLogIn2ToMainFragment()
            findNavController().navigate(action)
        }

    }

    fun hideKeypad(){
        val inputManager= activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken,0)
    }

}