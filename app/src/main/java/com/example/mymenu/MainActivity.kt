package com.example.mymenu

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }
    private fun initialize(){
        val toolbar: MaterialToolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.main_drawer)
        val drawerToogle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.open_drawer,R.string.close_drawer)

        drawer.addDrawerListener(drawerToogle)

        drawerToogle.syncState()

        val navView: NavigationView = findViewById(R.id.navigationView)
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_calcular_grasa -> openGrasaCorporalActivity()
            R.id.item_mis_calculos -> openMisCalculosActivity()
            else -> Toast.makeText(this,"Seleccione una opcion valida", Toast.LENGTH_LONG).show()
        }
        drawer.closeDrawer(GravityCompat.START)

        return true
    }

    private fun openGrasaCorporalActivity(){
        val intent = Intent(this,GrasaCorporalActivity::class.java)
        startActivity(intent)
    }

    private fun openMisCalculosActivity(){
        val intent = Intent(this,MisCalculosActivity::class.java)
        startActivity(intent)
    }
}