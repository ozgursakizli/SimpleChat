package com.ozgursakizli.simplechat.utilities

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class SimpleChatGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        if (Utils.isDebugMode()) {
            builder.setLogLevel(Log.VERBOSE)
        }
        super.applyOptions(context, builder)
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}