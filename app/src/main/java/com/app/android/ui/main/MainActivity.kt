package com.app.android.ui.main

import android.os.Bundle
import com.uniqlo.circle.ui.base.BaseActivity
import org.jetbrains.anko.setContentView

class MainActivity : BaseActivity() {

    private val ui = MainActivityUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
    }

    override fun onBindViewModel() {
    }
}
