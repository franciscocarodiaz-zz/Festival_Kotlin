package com.arequa.festival2018.eventdetail

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.arequa.commons.BaseListFragment
import com.arequa.commons.DataBindingRecyclerAdapter
import com.arequa.festival2018.BR
import com.arequa.festival2018.EventCategory
import com.arequa.festival2018.EventDetail
import com.arequa.festival2018.R
import java.util.*

/**
 * Created by FCD on 22/05/2018.
 */
class CustomEventsFragment : BaseListFragment() {
    override fun getAdapter(): RecyclerView.Adapter<*> {
        return DataBindingRecyclerAdapter<EventDetail>(BR.item, R.layout.item_event_detail)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (listAdapter as DataBindingRecyclerAdapter<EventDetail>).items.addAll(getDummyItems())
        listAdapter.notifyDataSetChanged()
    }

    fun getDummyItems() : ArrayList<EventDetail>{
        return arrayListOf(EventDetail("0","name","born", "title", "actor", "quote",
                "father", "mother", "spouse", "https://i.blogs.es/7b014b/coco-disney/450_1000" +
                ".jpg", EventCategory("cat_name","cat_region","cat_words","cat_url")))
    }

    /*override fun getLayoutResId(): Int {
        return R.layout.fragments_events
    }*/

}
