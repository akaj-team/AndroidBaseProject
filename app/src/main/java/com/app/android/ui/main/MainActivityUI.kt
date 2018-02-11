package com.app.android.ui.main

import com.app.android.pref.Pref
import org.jetbrains.anko.*

/**
 *
 * @author at-vinhhuynh
 */
class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
            textView {
                text = "Token:" + Pref.accessToken
            }.lparams {
                centerInParent()
            }
        }
    }
}
