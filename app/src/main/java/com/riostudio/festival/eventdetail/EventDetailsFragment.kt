package com.riostudio.festival.eventdetail

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riostudio.festival.EventDetail
import com.riostudio.festival.R
import kotlinx.android.synthetic.main.fragment_characters.*

class EventDetailsFragment : Fragment() {

    interface OnItemClickListener {
        fun onItemClicked(eventDetail: EventDetail)
    }

    companion object {
        fun newInstance(id: Int) : EventDetailsFragment {
            val instance = EventDetailsFragment()

            val args = Bundle()
            args.putInt("key_id", id)
            instance.arguments = args

            return instance
        }

    }

    val list: RecyclerView by lazy {
        val list : RecyclerView = view!!.findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(context)
        list
    }

    private val mAdapter: EventDetailsAdapter by lazy {
        val adapter = EventDetailsAdapter{ item, _ ->
            itemClickListener.onItemClicked(item)
        }
        adapter
    }

    lateinit var itemClickListener: OnItemClickListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnItemClickListener)
            itemClickListener = context
        else
            throw IllegalArgumentException("Attached activity doesn't implement CharacterFragment.OnItemClickListener")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    var eventsId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventsId = arguments?.getInt("key_id") ?: 0
        requestData()
        list.adapter = mAdapter
        btnRetry.setOnClickListener {
            retry()
        }
    }

    private fun retry () {
        layoutError.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

        requestData()
    }

    private fun requestData() {
        context?.let {
            EventsRepo.requestEventDetails(it, eventsId,
                { characters ->
                    view?.let {
                        progressBar.visibility = View.INVISIBLE
                        list.visibility = View.VISIBLE
                        mAdapter.setCharacters(characters)
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

}