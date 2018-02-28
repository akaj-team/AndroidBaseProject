package com.app.android.ui.main

import android.graphics.Color
import android.view.ViewGroup
import com.app.android.R
import org.jetbrains.anko.*

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/28/18.
 */
class TaskItemUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        val verticalLayout = verticalLayout {
            lparams(wrapContent, wrapContent)
            padding = dimen(R.dimen.itemTaskPadding)

            textView {
                id = R.id.itemTaskTitle
                maxLines = 1
                textColor = Color.BLACK
                textSize = px2dip(dimen(R.dimen.itemTaskTvTitleTextSize))
            }

            textView {
                id = R.id.itemTaskDescription
                maxLines = 1
                textColor = Color.GRAY
                textSize = px2dip(dimen(R.dimen.itemTaskTvDescriptionTextSize))
            }

            textView {
                id = R.id.itemTaskStatus
                textSize = px2dip(dimen(R.dimen.itemTaskTvStatusTextSize))
            }
        }
        verticalLayout
    }
}
