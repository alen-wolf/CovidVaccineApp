package com.example.covidvaccine

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.covidvaccine.databinding.FragmentAboutCovidBinding
import com.example.covidvaccine.databinding.FragmentMainBinding


class AboutCovid : Fragment(R.layout.fragment_about_covid) {

    private var fragmentAboutCovidBinding: FragmentAboutCovidBinding?=null
    private  val args: AboutCovidArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding =FragmentAboutCovidBinding.bind(view)
        fragmentAboutCovidBinding=binding


        val user=args.username
        val pass=args.password

        binding.next.setOnClickListener {
            val action = AboutCovidDirections.actionAboutCovidToPitanje1Fragment(user,pass)
            findNavController().navigate(action)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.switchmenu, menu)
    }


    fun engText(){
        fragmentAboutCovidBinding?.aboutCoronaText?.setText(resources.getString(R.string.aboutCovidEng))
        fragmentAboutCovidBinding?.zelText?.setText(resources.getString(R.string.testcontrolEng))
        fragmentAboutCovidBinding?.next?.setText(resources.getString(R.string.naprijedEng))
    }

    fun baText(){
        fragmentAboutCovidBinding?.aboutCoronaText?.setText(resources.getString(R.string.aboutCovid))
        fragmentAboutCovidBinding?.zelText?.setText(resources.getString(R.string.testcontrol))
        fragmentAboutCovidBinding?.next?.setText(resources.getString(R.string.naprijed))
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.engleski-> engText()
            R.id.bosanski->baText()
        }
        return super.onOptionsItemSelected(item)
    }





}