package com.example.covidvaccine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.covidvaccine.R.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val navController = this.findNavController(id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(id.myNavHostFragment)
        return navController.navigateUp()
    }



}
