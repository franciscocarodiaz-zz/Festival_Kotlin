package com.arequa.commons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by FCD on 23/05/2018.
 */
fun ViewGroup.inflate(layoutResId: Int, attachToRoot: Boolean) : View {
    val inflater = LayoutInflater.from(context)
    return inflater.inflate(layoutResId, this, attachToRoot);
}