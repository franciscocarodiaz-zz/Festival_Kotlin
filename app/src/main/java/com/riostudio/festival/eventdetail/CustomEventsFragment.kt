package com.riostudio.festival.eventdetail

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.riostudio.commons.BaseListFragment
import com.riostudio.commons.DataBindingRecyclerAdapter
import com.riostudio.festival.BR
import com.riostudio.festival.EventCategory
import com.riostudio.festival.EventDetail
import com.riostudio.festival.R
import kotlinx.android.synthetic.main.fragment_characters.*
import java.util.*

/**
 * Created by FCD on 22/05/2018.
 */
class CustomEventsFragment : BaseListFragment() {

    companion object {
        fun newInstance(id: Int) : CustomEventsFragment {
            val instance = CustomEventsFragment()

            val args = Bundle()
            args.putInt("key_id", id)
            instance.arguments = args

            return instance
        }

    }

    override fun getAdapter(): RecyclerView.Adapter<*> {
        return DataBindingRecyclerAdapter<EventDetail>(BR.item, R.layout.item_event_detail)
    }

    var eventsId: Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventsId = arguments?.getInt("key_id") ?: 0
        requestData()
    }

    private fun requestData() {
        context?.let {
            EventsRepo.requestEventDetails(it, eventsId,
                    { events ->
                        view?.let {
                            progressBar.visibility = View.INVISIBLE
                            list.visibility = View.VISIBLE
                            (listAdapter as DataBindingRecyclerAdapter<EventDetail>).items.addAll(events)
                            listAdapter.notifyDataSetChanged()
                        }

                    },
                    {
                        view?.let {
                            progressBar.visibility = View.INVISIBLE
                            layoutError.visibility = View.VISIBLE
                        }

                    })
        }
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
