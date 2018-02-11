package com.app.android.ui.splash

import android.os.Bundle
import android.os.Handler
import com.app.android.pref.Pref
import com.app.android.ui.login.LoginActivity
import com.app.android.ui.main.MainActivity
import com.uniqlo.circle.ui.base.BaseActivity
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/11/18.
 */
class SplashActivity : BaseActivity() {

    companion object {
        val DELAY = 3000L
    }

    private val ui = SplashActivityUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
        val handler = Handler()
        handler.postDelayed({
            if (Pref.accessToken.isBlank()) {
                startActivity<LoginActivity>()
            } else {
                startActivity<MainActivity>()
            }
        }, DELAY)
    }

    override fun onBindViewModel() {
    }
}
