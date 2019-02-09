package com.riostudio.festival.eventdetail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.riostudio.festival.EventDetail
import com.riostudio.festival.R
import kotlinx.android.synthetic.main.activity_event_detail.*


class EventDetailsActivity : AppCompatActivity(), EventDetailsFragment.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        if (savedInstanceState == null) {
            val charactersFragment = EventDetailsFragment()

            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.listContainer, charactersFragment)
                    .commit()
        }
    }

    override fun onItemClicked(eventDetail: EventDetail) {
        if (isDetailViewAvalaible())
            showFragmentDetail(eventDetail.id)
        else
            launchDetailActivity(eventDetail.id)
    }

    private fun isDetailViewAvalaible() = detailContainer != null

    private fun showFragmentDetail(id: String) {
        val detailCharacterFragment = DetailEventFragment.newInstance(id)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.detailContainer, detailCharacterFragment)
                .commit()
    }

    private fun launchDetailActivity(id: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("key_id", id)
        startActivity(intent)
    }

}