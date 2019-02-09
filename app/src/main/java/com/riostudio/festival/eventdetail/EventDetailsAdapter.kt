package com.riostudio.festival.eventdetail

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riostudio.festival.EventCategory
import com.riostudio.festival.EventDetail
import com.riostudio.festival.R
import kotlinx.android.synthetic.main.item_event_detail.view.*

class EventDetailsAdapter: RecyclerView.Adapter<EventDetailsAdapter.EventDetailViewHolder> {

    constructor(): super() {
        itemClickListener = null
    }

    constructor(itemClickListener: ((EventDetail, Int) -> Unit)) : super(){
        this.itemClickListener = itemClickListener
    }

    private val items = mutableListOf<EventDetail>()

    private val itemClickListener: ((EventDetail, Int) -> Unit)?

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event_detail, parent,
                false)
        return EventDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventDetailViewHolder, position: Int) {
        val item = items[position]
        holder.eventDetail = item
    }

    fun setCharacters(characters: MutableList<EventDetail>) {
        items.clear()
        items.addAll(characters)

        notifyDataSetChanged()
    }

    inner class EventDetailViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var eventDetail: EventDetail? = null
            set(value) {

                value?.let {
                    val overlayColor = EventCategory.getOverlayColor(value.category.name)
                    itemView.imgOverlay.background = ContextCompat.getDrawable(itemView.context, overlayColor)

                    /*Picasso.get()
                            .load(value.img)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(itemView.imgCharacter)*/
                }
                field = value
            }

        init {
            itemView.setOnClickListener{
                eventDetail?.let {
                    itemClickListener?.invoke(eventDetail as EventDetail, adapterPosition)
                }
            }
        }


    }
}