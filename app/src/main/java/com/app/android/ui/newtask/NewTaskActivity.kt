package com.app.android.ui.newtask

import android.os.Bundle
import android.view.View
import com.uniqlo.circle.ui.base.BaseActivity
import org.jetbrains.anko.setContentView

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/28/18.
 */
class NewTaskActivity : BaseActivity() {

    private var ui = NewTaskActivityUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
    }

    override fun onBindViewModel() {

    }

    internal fun onHandleTextChange(title: String, description: String) {

    }

    internal fun eventOnViewClicked(view: View) {

    }
}
