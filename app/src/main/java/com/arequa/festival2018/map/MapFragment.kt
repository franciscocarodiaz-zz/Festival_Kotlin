package com.arequa.festival2018.map

import com.arequa.commons.MockResponseInterceptor
import com.arequa.festival2018.R

/**
 * Created by FCD on 22/05/2018.
 */
class MapFragment : MockResponseInterceptor() {
    override fun getLayoutResId(): Int {
        return R.layout.fragments_map
    }
}
