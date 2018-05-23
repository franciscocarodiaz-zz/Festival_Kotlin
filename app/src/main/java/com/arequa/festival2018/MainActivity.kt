package com.arequa.festival2018

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.arequa.festival2018.allevents.AllEventsFragment
import com.arequa.festival2018.events.EventsFragment
import com.arequa.festival2018.map.MapFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val DEFAULT_OPTION = R.id.action_menu_middle
    }

    val fragments : HashMap<Int, Fragment> = hashMapOf(
            Pair(R.id.action_menu_left, EventsFragment()),
            Pair(R.id.action_menu_middle, AllEventsFragment()),
            Pair(R.id.action_menu_right, MapFragment())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        navigationView.selectedItemId = DEFAULT_OPTION
        navigationView.setOnNavigationItemSelectedListener { item ->
            val fragment = fragments[item.itemId]

            if (fragment != null) {
                replaceFragment(fragment)
            }

            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if(!fragment.isAdded) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
        }
    }

    fun initView() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (currentFragment == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, fragments[DEFAULT_OPTION])
                    .commit()
        }
    }
}
