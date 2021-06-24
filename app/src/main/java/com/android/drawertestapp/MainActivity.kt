package com.android.drawertestapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat.START
import androidx.fragment.app.Fragment
import com.android.drawertestapp.databinding.ActivityMainBinding
import com.android.drawertestapp.fragment.NavigationFragment
import com.android.drawertestapp.fragment.NewsFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.toolbar)
        binding.toolbar.toolbarIvNavigation.setOnClickListener {
                openCloseDrawer()
        }
        binding.toolbar.toolbar.navigationIcon = null
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.toggle_open,
            R.string.toggle_close
        )
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)

        replaceFragment(0);
        replaceNavigationFragment();
    }

    private fun openCloseDrawer() {
        if (binding.drawerLayout.isDrawerOpen(START)) {
            binding.drawerLayout.closeDrawer(START)
        } else binding.drawerLayout.openDrawer(START);
    }


    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        actionBarDrawerToggle.syncState()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(START)) {
            binding.drawerLayout.closeDrawer(START)
        } else {
            super.onBackPressed()
        }
    }


    fun replaceNavigationFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flContainerNavigationMenu, NavigationFragment.newInstance(), "Navigation")
            .commit()
    }

    fun replaceFragment(position: Int) {
        var fragment: Fragment? = null
        var tag: String? = null
        when (position) {
            0 -> {
                fragment = NewsFragment()
                tag = "News"
            }
        }
        replaceFragment(fragment, tag)
    }

    private fun replaceFragment(fragment: Fragment?, tag: String?) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.flContainerFragment.id, fragment!!, tag)
            .commit()
        setToolbarTitle(tag)
        closeNavigationDrawer()
    }


    private fun setToolbarTitle(title: String?) {
        binding.toolbar.toolbar.title = title
    }

    private fun closeNavigationDrawer() {
        if (binding.drawerLayout.isDrawerOpen(START)) {
            binding.drawerLayout.closeDrawer(START)
        }
    }


}
