package com.arequa.festival2018.eventdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arequa.festival2018.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("key_id")

        if (savedInstanceState == null) {
            val fragment = DetailEventFragment.newInstance(id)

            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.detailContainer, fragment)
                    .commit()
        }
    }
}
