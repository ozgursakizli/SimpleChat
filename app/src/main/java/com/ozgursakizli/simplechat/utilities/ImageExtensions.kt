package com.ozgursakizli.simplechat.utilities

import android.widget.ImageView

fun ImageView.displayCircularImage(imagePath: String?, placeHolder: Int) {
    GlideApp.with(this)
        .load(imagePath)
        .circleCrop()
        .override(120, 120)
        .error(placeHolder)
        .placeholder(placeHolder)
        .into(this)
}