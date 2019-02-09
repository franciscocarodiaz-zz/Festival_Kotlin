package com.riostudio.commons

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by FCD on 28/05/2018.
 */

@BindingAdapter("imgUrl")
fun setImageUrl(img: ImageView, url: String) {
    Glide.with(img).load(url).into(img)
}