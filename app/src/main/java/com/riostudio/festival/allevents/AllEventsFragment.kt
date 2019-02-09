package com.riostudio.festival.allevents

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.View
import com.riostudio.commons.BaseListFragment
import com.riostudio.commons.DataBindingRecyclerAdapter
import com.riostudio.festival.AllEvent
import com.riostudio.festival.BR
import com.riostudio.festival.R
import com.riostudio.festival.data.EventsDataSource

/**
 * Created by FCD on 22/05/2018.
 */
class AllEventsFragment : BaseListFragment() {

    override fun onResume() {
        super.onResume()
        showData()
    }

    private fun showData() {
        EventsDataSource
                .getAllEvents()
                .subscribe({ list ->
                    replaceItems(list)
                }, { error ->
                    showMessage(error)
                })
    }

    private fun showMessage(error: Throwable?) {
        error!!.printStackTrace()
        view?.let {
            Snackbar.make(view as View, R.string.error_request, Snackbar.LENGTH_LONG)
                    .setAction(R.string.label_retry, { _ -> showData()})
                    .show()
        }
    }

    private fun replaceItems(list: ArrayList<com.riostudio.festival.AllEvent>) {
        with(listAdapter as DataBindingRecyclerAdapter<com.riostudio.festival.AllEvent>) {
            items.clear()
            items.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun getAdapter(): RecyclerView.Adapter<*> {
        return DataBindingRecyclerAdapter<com.riostudio.festival.AllEvent>(BR.item, R.layout
                .item_allevent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (listAdapter as DataBindingRecyclerAdapter<com.riostudio.festival.AllEvent>).items.addAll(getDummyItems())
        listAdapter.notifyDataSetChanged()
    }

    fun getDummyItems() : ArrayList<com.riostudio.festival.AllEvent>{
        val event = AllEvent("title 1",
                144342352,
                80,
                "Valve", 9.90F, 1, "https://i.blogs" +
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
