package com.app.android.ui.newtask

import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import com.app.android.data.model.Task
import org.jetbrains.anko.*

/**
 *
 * @author at-vinhhuynh
 */
class NewTaskActivityUI(tasks: MutableList<Task>) : AnkoComponent<NewTaskActivity> {

    internal lateinit var progressBar: ProgressBar

    override fun createView(ui: AnkoContext<NewTaskActivity>) = with(ui) {
        relativeLayout {
            lparams(matchParent, matchParent)
            backgroundColor = Color.WHITE


            progressBar = progressBar {
                visibility = View.GONE
            }.lparams {
                centerInParent()
            }
        }
    }
}
