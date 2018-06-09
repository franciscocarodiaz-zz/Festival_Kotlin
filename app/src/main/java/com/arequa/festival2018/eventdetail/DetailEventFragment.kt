package com.arequa.festival2018.eventdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arequa.festival2018.EventCategory
import com.arequa.festival2018.R
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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments.getString("key_id")
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
                imgOverlay.background = ContextCompat.getDrawable(context, overlayColor)

                val baseColor = EventCategory.getBaseColor(eventDetail.category.name)
                btnHouse.backgroundTintList = ContextCompat.getColorStateList(context, baseColor)

                val idDrawable = EventCategory.getIcon(eventDetail.category.name)
                val drawable = ContextCompat.getDrawable(context, idDrawable)
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