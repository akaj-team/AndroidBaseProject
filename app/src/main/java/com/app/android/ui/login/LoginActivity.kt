package com.app.android.ui.login

import android.os.Bundle
import com.uniqlo.circle.ui.base.BaseActivity
import org.jetbrains.anko.setContentView

/**
 * Copyright © 2017 AsianTech inc.
 * Created by trung.nguyen on 2/9/18.
 */
class LoginActivity : BaseActivity() {

    private val ui = LoginActivityUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
    }

    override fun onBindViewModel() {

    }

}
