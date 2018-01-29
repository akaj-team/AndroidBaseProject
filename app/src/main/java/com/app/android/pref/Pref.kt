package com.app.android.pref

import com.chibatching.kotpref.KotprefModel

/**
 * SharePreference KotPref instance
 */
object Pref : KotprefModel() {

    var accessToken by stringPref()
}
