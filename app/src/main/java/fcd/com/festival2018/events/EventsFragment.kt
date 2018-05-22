package fcd.com.festival2018.events

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fcd.com.festival2018.R

/**
 * Created by FCD on 22/05/2018.
 */
class EventsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragments_events, container, false)
        return view

    }
}
