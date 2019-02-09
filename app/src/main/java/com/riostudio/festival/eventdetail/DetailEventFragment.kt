package com.riostudio.festival.eventdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riostudio.festival.EventCategory
import com.riostudio.festival.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_event.*
import kotlinx.android.synthetic.main.header_event.*


class DetailEventFragment : Fragment() {

    companion object {
        fun newInstance(id: String) : DetailEventFragment {
            val instance = DetailEventFragment()

            val args = Bundle()
            args.putString("key_id", id)
            instance.arguments = args

            return instance
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_detail_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments!!.getString("key_id")
        val eventDetail = EventsRepo.findCharacterById(id)

        eventDetail?.let {
            with(eventDetail) {
                labelName.text = name
                labelTitle.text = title
                labelBorn.text = born
                labelActor.text = actor
                labelQuote.text = quote
                labelParents.text = "$father & $mother"
                labelSpouse.text = spouse

                val overlayColor = EventCategory.getOverlayColor(eventDetail.category.name)
                imgOverlay.background = context?.let { it1 -> ContextCompat.getDrawable(it1, overlayColor) }

                val baseColor = EventCategory.getBaseColor(eventDetail.category.name)
                btnHouse.backgroundTintList = context?.let { it1 -> ContextCompat.getColorStateList(it1, baseColor) }

                val idDrawable = EventCategory.getIcon(eventDetail.category.name)
                val drawable = context?.let { it1 -> ContextCompat.getDrawable(it1, idDrawable) }
                btnHouse.setImageDrawable(drawable)

                Picasso.get()
                        .load(img)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(imgCharacter)
            }
        }

        btnHouse.setOnClickListener {
            if (eventDetail != null)
                showDialog(eventDetail.category)
        }


    }

    private fun showDialog(category: EventCategory) {
        val dialog = EventDialog.newInstance(category)
        dialog.show(childFragmentManager, "key_dialog")
    }
}