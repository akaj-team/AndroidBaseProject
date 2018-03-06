package com.app.android.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 3/1/18.
 */
fun Date.getTimestamp(): String {
    val format = SimpleDateFormat("YYYY-MM-dd hh:mm:ss", Locale.JAPANESE)
    return format.format(this)
}
