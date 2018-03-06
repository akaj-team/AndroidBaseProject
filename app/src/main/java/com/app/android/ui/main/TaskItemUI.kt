package com.app.android.ui.main

import android.graphics.Color
import android.view.ViewGroup
import android.widget.TextView
import com.app.android.R
import com.app.android.data.model.Task
import com.app.android.ui.edittask.TaskDetailActivity.Companion.DONE
import org.jetbrains.anko.*

/**
 * Copyright © 2018 AsianTech inc.
 * Created by trung.nguyen on 2/28/18.
 */
class TaskItemUI : AnkoComponent<ViewGroup> {

    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvStatus: TextView

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        verticalLayout {
            lparams(wrapContent, wrapContent)
            padding = dimen(R.dimen.itemTaskPadding)

            tvTitle = textView {
                id = R.id.itemTaskTitle
                maxLines = 1
                textColor = Color.BLACK
                textSize = px2dip(dimen(R.dimen.itemTaskTvTitleTextSize))
            }

            tvDescription = textView {
                id = R.id.itemTaskDescription
                maxLines = 1
                textColor = Color.GRAY
                textSize = px2dip(dimen(R.dimen.itemTaskTvDescriptionTextSize))
            }

            tvStatus = textView {
                id = R.id.itemTaskStatus
                textSize = px2dip(dimen(R.dimen.itemTaskTvStatusTextSize))
            }
        }
    }

    internal fun updateTaskItem(task: Task) {
        with(task) {
            tvTitle.text = title
            tvDescription.text = description
            tvStatus.run {
                if (isDone == DONE) {
                    textResource = R.string.taskDone
                    textColor = Color.GREEN
                } else {
                    textResource = R.string.taskDoing
                    textColor = Color.RED
                }
            }
        }
    }
}
