package com.app.android.ui.splash

import android.os.Bundle
import com.app.android.extension.observeOnUiThread
import com.app.android.pref.Pref
import com.app.android.ui.login.LoginActivity
import com.app.android.ui.main.MainActivity
import com.uniqlo.circle.ui.base.BaseActivity
import io.reactivex.Observable
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit


/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/11/18.
 */
class SplashActivity : BaseActivity() {

    companion object {
        const val DELAY = 3000L
    }

    private val ui = SplashActivityUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
    }

    override fun onBindViewModel() {
        Observable.just(true).delay(DELAY, TimeUnit.MILLISECONDS)
                .observeOnUiThread()
                .doOnNext {
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
