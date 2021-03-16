package com.ozgursakizli.simplechat.utilities

import com.ozgursakizli.simplechat.BuildConfig
import java.util.*

object Utils {

    fun isDebugMode(): Boolean = BuildConfig.DEBUG

    fun generateUserId() = UUID.randomUUID().toString()

    fun generateRandomId(): String {
        val allowedChars = "0123456789qwertyuiopasdfghjklzxcvbnm"
        val random = Random()
        val sb = StringBuilder(20)
        for (i in 0 until 20)
            sb.append(allowedChars[random.nextInt(allowedChars.length)])
        return sb.toString()
    }

}