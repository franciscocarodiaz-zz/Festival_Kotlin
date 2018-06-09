package com.arequa.festival2018

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.arequa.festival2018.eventdetail.DetailActivity
import com.arequa.festival2018.eventdetail.DetailEventFragment
import com.arequa.festival2018.eventdetail.EventDetailsFragment
import com.arequa.festival2018.map.MapFragment
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), EventDetailsFragment.OnItemClickListener {

    companion object {
        const val DEFAULT_OPTION = R.id.action_menu_middle
        const val OPTION_GROUP_LEFT = 0
        const val OPTION_GROUP_MIDDLE = 1
    }

    val fragments: HashMap<Int, Fragment> = hashMapOf(
            Pair(R.id.action_menu_left, EventDetailsFragment.newInstance(OPTION_GROUP_LEFT)),
            Pair(R.id.action_menu_middle, EventDetailsFragment.newInstance(OPTION_GROUP_MIDDLE)),
            Pair(R.id.action_menu_right, MapFragment())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            initView()
        }

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
        if (!fragment.isAdded) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
        }
    }

    fun initView() {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, fragments[DEFAULT_OPTION])
                .commit()
    }

    override fun onItemClicked(eventId: EventDetail) {
        if (isDetailViewAvalaible())
            showFragmentDetail(eventId.id)
        else
            launchEventDetailsActivity(eventId.id)
    }

    private fun isDetailViewAvalaible() = detailContainer != null

    private fun showFragmentDetail(id: String) {
        val detailEventFragment = DetailEventFragment.newInstance(id)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.detailContainer, detailEventFragment)
                .commit()
    }

    private fun launchEventDetailsActivity(id: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("key_id", id)
        startActivity(intent)
    }
}
