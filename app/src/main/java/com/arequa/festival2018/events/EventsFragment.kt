package com.arequa.festival2018.events

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.arequa.commons.BR
import com.arequa.commons.BaseListFragment
import com.arequa.commons.DataBindingRecyclerAdapter
import com.arequa.festival2018.Event
import com.arequa.festival2018.R

/**
 * Created by FCD on 22/05/2018.
 */
class EventsFragment : BaseListFragment() {
    override fun getAdapter(): RecyclerView.Adapter<*> {
        return DataBindingRecyclerAdapter<Event>(BR.item, R.layout.item_event)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (listAdapter as DataBindingRecyclerAdapter<Event>).items.addAll(getDummyItems())
    }

    fun getDummyItems() : ArrayList<Event>{
        return arrayListOf(Event("title", 0.99F, 9.99F, 80, 80, "https://i.blogs.es/7b014b/coco-disney/450_1000.jpg"))
    }
    /*override fun getLayoutResId(): Int {
        return R.layout.fragments_events
    }*/

}
