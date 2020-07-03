package com.armandosantos.seccion_07_drawer_recycler_card.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.alejandrolora.mylibrary.ToolbarActivity
import com.armandosantos.seccion_07_drawer_recycler_card.R
import com.armandosantos.seccion_07_drawer_recycler_card.fragments.ArrivalsFragment
import com.armandosantos.seccion_07_drawer_recycler_card.fragments.DeparturesFragment
import com.armandosantos.seccion_07_drawer_recycler_card.fragments.HomeFragment
import com.armandosantos.seccion_07_drawer_recycler_card.toast
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolbarActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarToLoad(toolbar as Toolbar?)

        setNavDrawer()
        serUserHeaderInformation()
        if(savedInstanceState == null){
            toast("IS NULL");
            fragmentTransaction(HomeFragment())
            navView.menu.getItem(0).isChecked = true
        }else
            toast("NOT NULL")

    }

    private fun setNavDrawer() {
        val toogle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            _toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )

        toogle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    private fun fragmentTransaction(fragmen: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragmen).commit()
    }


    private fun loadFragmentById(id: Int) {
        when (id) {
            R.id.nav_home -> fragmentTransaction(HomeFragment())
            R.id.nav_departures -> fragmentTransaction(DeparturesFragment())
            R.id.nav_arrivals -> fragmentTransaction(ArrivalsFragment())
        }
    }

    private fun showMessageNavItemSelectedById(id: Int) {
        when (id) {
             R.id.nav_profile -> toast("Profile")
             R.id.nav_settings -> toast("Settings")
        }
    }

    private fun serUserHeaderInformation() {
        val name = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewName)
        val email = navView.getHeaderView(0).findViewById<TextView>(R.id.textViewEmail)

        name?.let { name.text = getString(R.string.user_name) }
        email?.let { email.text = getString(R.string.user_email) }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        showMessageNavItemSelectedById(item.itemId)
        loadFragmentById(item.itemId)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

}
