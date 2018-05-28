package com.arequa.festival2018.allevents

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.arequa.commons.BR
import com.arequa.commons.BaseListFragment
import com.arequa.commons.DataBindingRecyclerAdapter
import com.arequa.festival2018.AllEvent
import com.arequa.festival2018.R

/**
 * Created by FCD on 22/05/2018.
 */
class AllEventsFragment : BaseListFragment() {

    override fun getAdapter(): RecyclerView.Adapter<*> {
        return DataBindingRecyclerAdapter<AllEvent>(BR.allevent, R.layout.item_allevent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (listAdapter as DataBindingRecyclerAdapter<AllEvent>).items.addAll(getDummyItems())
        listAdapter.notifyDataSetChanged()
    }

    fun getDummyItems() : ArrayList<AllEvent>{
        val event = AllEvent("title 1",
                144342352,
                "Valve", 80, 9.90F, 1, "https://i.blogs" +
                ".es/7b014b/coco-disney/450_1000.jpg");
        val list = arrayListOf(event)
        val event2 = event.copy()
        event2.title = "title 2"
        event2.thumb = "https://okdiario.com/img/2017/07/13/mejores-peliculas-superheroes-3.jpg"
        list.add(event2)

        val event3 = event.copy()
        event3.title = "title 3"
        event3.thumb = "http://sm.ign.com/ign_latam/news/g/guardians-director-james-gunn-addresses-awards-sho/guardians-director-james-gunn-addresses-awards-sho_npme.jpg"
        list.add(event3)

        list.add(event)
        list.add(event2)
        list.add(event3)

        return list
    }

    /*override fun getLayoutResId(): Int {
        return R.layout.fragments_allevents
    }*/
}
