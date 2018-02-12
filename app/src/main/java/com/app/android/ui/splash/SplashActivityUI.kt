package com.app.android.ui.splash

import android.support.v4.content.ContextCompat
import com.app.android.R
import org.jetbrains.anko.*

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/11/18.
 */
class SplashActivityUI : AnkoComponent<SplashActivity> {
    override fun createView(ui: AnkoContext<SplashActivity>) = with(ui) {
        relativeLayout {
            backgroundColor = ContextCompat.getColor(ctx, android.R.color.white)
            textView(R.string.app_name).lparams {
                centerInParent()
            }
        }
    }
}
