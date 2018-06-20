package com.arequa.festival2018.eventdetail

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import com.arequa.festival2018.EventCategory
import com.arequa.festival2018.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_category.view.*
import java.io.Serializable

class EventDialog : DialogFragment() {

    companion object {
        fun newInstance(house : EventCategory) : EventDialog{
            val arguments = Bundle()
            arguments.putSerializable("key_dialog", house as Serializable)

            val dialog = EventDialog()
            dialog.arguments = arguments
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_category, null, false)
        val house  = arguments?.getSerializable("key_dialog") as EventCategory

        with(house){
            dialogView.labelName.text = name
            dialogView.labelRegion.text = region
            dialogView.labelWords.text = words
            dialogView.layoutDialog.background = context?.let { ContextCompat.getDrawable(it, EventCategory.getBaseColor(name)) }

            Picasso.get()
                    .load(img)
                    .into(dialogView.imgHouse)

        }

        return AlertDialog.Builder(context)
                .setView(dialogView)
                .setPositiveButton(R.string.label_accept, {_,_ -> dismiss()})
                .create()

    }
}