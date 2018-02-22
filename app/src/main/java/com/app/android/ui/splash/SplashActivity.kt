package com.app.android.ui.splash

import android.os.Bundle
import com.app.android.pref.Pref
import com.app.android.ui.login.LoginActivity
import com.app.android.ui.main.MainActivity
import com.uniqlo.circle.ui.base.BaseActivity
import io.reactivex.Maybe
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit


/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/11/18.
 */
class SplashActivity : BaseActivity() {

    private val ui = SplashActivityUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
    }

    override fun onBindViewModel() {
        Maybe.empty<Unit>()
                .delay(3, TimeUnit.SECONDS)
                .doOnComplete {
                    if (Pref.accessToken.isBlank()) {
                        startActivity<LoginActivity>()
                    } else {
                        startActivity<MainActivity>()
                    }
                    finish()
                }
                .subscribe()
    }
}
