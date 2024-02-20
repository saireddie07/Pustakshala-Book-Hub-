package com.example.bookinfohub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toolBar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawToggle:ActionBarDrawerToggle
    lateinit var navigationview:NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBar = findViewById(R.id.toolBarMainActionBar)
        drawerLayout = findViewById(R.id.drawer_Laayout)
        navigationview=findViewById(R.id.navigationView)

        setSupportActionBar(toolBar)
        supportActionBar?.title = "Pusthakshala"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        actionBarDrawToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawToggle)
        actionBarDrawToggle.syncState()

        openDashboard()

        navigationview.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.dashBoard -> {
                    openDashboard()
                    drawerLayout.closeDrawers()
                }
                R.id.favorites -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame,FavoritesFragment()).commit()
                    supportActionBar?.title = "Favorites"
                    drawerLayout.closeDrawers()
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame,ProfileFragment()).commit()
                    supportActionBar?.title = "Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.info -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame,InfoFragment()).commit()
                    supportActionBar?.title = "Info"
                    drawerLayout.closeDrawers()
                }
            }
            return@setNavigationItemSelectedListener true
        }


    }

     fun openDashboard() {
         supportFragmentManager.beginTransaction().replace(R.id.frame,DashboardFragment()).commit()
         navigationview.setCheckedItem(R.id.dashBoard)
         supportActionBar?.title = "Dashboard"
    }

    override fun onBackPressed() {
        val fra=supportFragmentManager.findFragmentById(R.id.frame)
        when(fra){
            !is DashboardFragment -> openDashboard()
            else -> super.onBackPressed()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        if(id == android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
}
